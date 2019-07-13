import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dev.dorcks.lotice"
version = "0.0.1"

plugins {
    kotlin("jvm") version "1.3.41"
    groovy
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testCompile("org.spockframework", "spock-core", "1.3-groovy-2.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}
