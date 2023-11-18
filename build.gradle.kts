plugins {
    id("java")
    id("application")
    // Officially endorsed Lombok plugin for Gradle
    id("io.freefair.lombok") version "8.4"
}

group = "com.admiralbot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

val ops = sourceSets.create("ops") {
    java.srcDir("src/ops/java")
    resources.srcDir("src/ops/resources")
    // Ops SourceSet uses main SourceSet output
    compileClasspath += sourceSets.main.get().output
    runtimeClasspath += sourceSets.main.get().output
}

// Required since we have some special characters (e.g. in Discord locale list)
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
tasks.withType<Test> {
    defaultCharacterEncoding = "UTF-8"
}
tasks.withType<Javadoc>{
    options.encoding = "UTF-8"
}

// `ops` is a special source set for admin/management tasks, e.g. updating Discord application commands.
// It extends from `main` since the plan is to make `main` do the heavy lifting for things like secrets and API clients.
val opsImplementation: Configuration by configurations.getting {
    extendsFrom(configurations.implementation.get())
}
val opsRuntimeOnly: Configuration by configurations.getting {
    extendsFrom(configurations.runtimeOnly.get())
}

dependencies {

    /* Logging */

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j2-impl
    // This is just for SDK and other libraries that depend on SLF4J
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")

    /* AWS SDK V2 */

    // https://mvnrepository.com/artifact/software.amazon.awssdk/bom
    implementation("software.amazon.awssdk:bom:2.20.65")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/dynamodb-enhanced
    implementation("software.amazon.awssdk:dynamodb-enhanced:2.20.65")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/ec2
    implementation("software.amazon.awssdk:ec2:2.20.65")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/secretsmanager
    implementation("software.amazon.awssdk:secretsmanager:2.20.65")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/sts
    implementation("software.amazon.awssdk:sts:2.20.65")

    /* AWS SDK configuration */

    // https://mvnrepository.com/artifact/software.amazon.awssdk/aws-crt-client
    implementation("software.amazon.awssdk:url-connection-client:2.20.65")

    /* Lambda interface helpers */

    // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-core
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
    // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-events
    implementation("com.amazonaws:aws-lambda-java-events:3.11.1")
    // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-log4j2
    implementation("com.amazonaws:aws-lambda-java-log4j2:1.5.1")
    // https://mvnrepository.com/artifact/software.amazon.cloudwatchlogs/aws-embedded-metrics
    implementation("software.amazon.cloudwatchlogs:aws-embedded-metrics:4.1.1")

    /* JSON */

    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.0")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.datatype/jackson-datatype-jsr310
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.0")

    /* Cryptography (signature handling) */

    // https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk18on
    implementation("org.bouncycastle:bcprov-jdk18on:1.73")

    /* Test */

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.mockito:mockito-core:5.3.1")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")
}

tasks.test {
    useJUnitPlatform()
}

val packageLambda = tasks.register<Zip>("packageLambda") {
    archiveFileName.set("admiralbot-orca-lambda.zip")
    from(tasks.compileJava)
    from(tasks.processResources)
    into("lib") {
        from(configurations.runtimeClasspath.get())
    }
}

val syncBetaGuildCommands = tasks.register<JavaExec>("syncBetaGuildCommands") {
    classpath = ops.runtimeClasspath
    mainClass.set("com.admiralbot.orca.ops.commandsync.SyncTask")
    args = listOf("681515802135", "1101898412019957770", "1107685082640171048")
}
