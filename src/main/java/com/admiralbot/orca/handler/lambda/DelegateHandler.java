package com.admiralbot.orca.handler.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public abstract class DelegateHandler<Input, Output> implements RequestHandler<Input, Output> {

    private final RequestHandler<Input,Output> impl;

    public DelegateHandler() {
        impl = createHandlerImpl();
    }

    /**
     * IMPORTANT: This runs during superclass init so cannot access anything that would be constructed in the subclass,
     * e.g. fields initialised in subclass constructor.
     *
     * @return An instance of the handler implementation
     */
    protected abstract RequestHandler<Input,Output> createHandlerImpl();

    @Override
    public Output handleRequest(Input input, Context context) {
        return impl.handleRequest(input, context);
    }
}
