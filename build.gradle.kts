plugins {
    id("java")
    // Officially endorsed Lombok plugin for Gradle
    id("io.freefair.lombok") version "8.0.1"
}

group = "com.admiralbot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
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

dependencies {

    /* Logging */

    // https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")

    /* AWS SDK V2 */

    // https://mvnrepository.com/artifact/software.amazon.awssdk/bom
    implementation("software.amazon.awssdk:bom:2.20.56")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/dynamodb-enhanced
    implementation("software.amazon.awssdk:dynamodb-enhanced:2.20.56")
    // https://mvnrepository.com/artifact/software.amazon.awssdk/ec2
    implementation("software.amazon.awssdk:ec2:2.20.56")

    /* AWS SDK configuration */

    // https://mvnrepository.com/artifact/software.amazon.awssdk/aws-crt-client
    implementation("software.amazon.awssdk:aws-crt-client:2.20.56")

    /* Lambda interface helpers */

    // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-core
    implementation("com.amazonaws:aws-lambda-java-core:1.2.2")
    // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-events
    implementation("com.amazonaws:aws-lambda-java-events:3.11.1")
    // https://mvnrepository.com/artifact/com.amazonaws/aws-lambda-java-log4j2
    implementation("com.amazonaws:aws-lambda-java-log4j2:1.5.1")

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
