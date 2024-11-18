
plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

repositories {
    mavenCentral()
}

dependencies {

}

application {
    mainClass = "com.brisson.cli.AppKt"
}
