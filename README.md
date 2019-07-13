[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/dorcks/lotice/blob/master/LICENSE)

# lotice
Automated License and Notice Utility

## Prerequisites
JDK 11 is required to build Lotice.

[AdoptOpenJDK](https://adoptopenjdk.net/) 11 is available at https://adoptopenjdk.net/releases.html?variant=openjdk11

## Build Configuration
Lotice uses Gradle 5.5.1, and includes `gradle-wrapper.jar`.  The checksum of `gradle-wrapper.jar` can be verified by following the steps documented at [Verifying the checksum of the Gradle Wrapper JAR](https://docs.gradle.org/current/userguide/gradle_wrapper.html#wrapper_checksum_verification)

When using an IDE, configure the project to use JDK 11.  To build from the command line, `JAVA_HOME` should be set to the path of the JDK 11 installation.