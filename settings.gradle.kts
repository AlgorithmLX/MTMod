pluginManagement {
    repositories {
        mavenCentral()
        maven("https://maven.minecraftforge.net/")
        maven("https://repo.spongepowered.org/repository/maven-public/")
    }

    val kotlinVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version "1.9.22"
    }
}