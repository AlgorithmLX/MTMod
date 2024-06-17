pluginManagement {
    repositories {
        mavenCentral()
        maven("https://nexus.gtnewhorizons.com/repository/public/")
        maven("https://maven.minecraftforge.net/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
    }

    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version "1.9.22"
    }
}

plugins {
    // Automatic toolchain provisioning
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}