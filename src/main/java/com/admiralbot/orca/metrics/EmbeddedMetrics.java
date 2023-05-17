package com.admiralbot.orca.metrics;

import software.amazon.cloudwatchlogs.emf.exception.DimensionSetExceededException;
import software.amazon.cloudwatchlogs.emf.exception.InvalidDimensionException;
import software.amazon.cloudwatchlogs.emf.exception.InvalidMetricException;
import software.amazon.cloudwatchlogs.emf.exception.InvalidNamespaceException;
import software.amazon.cloudwatchlogs.emf.logger.MetricsLogger;
import software.amazon.cloudwatchlogs.emf.model.DimensionSet;
import software.amazon.cloudwatchlogs.emf.model.Unit;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

public final class EmbeddedMetrics {

    // private static interface

    // This is not really thread-safe, but for Lambda usage that's not a big deal.
    private static final MetricsLogger METRICS_LOGGER = new MetricsLogger();
    static {
        try {
            METRICS_LOGGER.setFlushPreserveDimensions(false);
            METRICS_LOGGER.setNamespace("Orca");
        } catch (InvalidNamespaceException e) {
            throw new RuntimeException("Failed to initialize MetricsLogger", e);
        }
    }

    private EmbeddedMetrics() {}

    public static void booleanMetric(String name, Map<String,String> dimensions, boolean value) {
        numberMetric(name, dimensions, value ? 1d : 0d);
    }

    public static void numberMetric(String name, Map<String,String> dimensions, double value) {
        numberMetric(name, dimensions, value, null);
    }

    public static void millisMetric(String name, Map<String,String> dimensions, Instant sinceInstant) {
        var millisSince = sinceInstant.until(Instant.now(), ChronoUnit.MILLIS);
        numberMetric(name, dimensions, millisSince, Unit.MILLISECONDS);
    }

    private static void numberMetric(String name, Map<String,String> dimensions, double value, Unit unit) {
        var dimensionSet = new DimensionSet();
        dimensions.forEach((k,v) -> {
            try {
                dimensionSet.addDimension(k,v);
            } catch (InvalidDimensionException | DimensionSetExceededException e) {
                throw new RuntimeException(e);
            }
        });
        METRICS_LOGGER.putDimensions(dimensionSet);
        try {
            // EMF library requires unit non-null if it is specified
            if (unit != null) {
                METRICS_LOGGER.putMetric(name, value, unit);
            } else {
                METRICS_LOGGER.putMetric(name, value);
            }
        } catch (InvalidMetricException e) {
            throw new RuntimeException(e);
        }
        METRICS_LOGGER.flush();
    }

}
