import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

plugins {
    java
    `maven-publish`
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.22"

    id("net.minecraftforge.gradle") version "5.+"
    id("com.github.johnrengelman.shadow") version "7.+"
}

val javaVersion = project.properties["java_version"].toString().toInt()
val kotlinVersion: String by project

val shadow: Configuration = configurations["shadow"]

version = project.properties["mod_version"].toString()
group = "ru.hollowhorizon"

base {
    archivesName.set("MasterTech")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(javaVersion))
}

configurations {
    implementation.get().extendsFrom(this["shadow"])
}

minecraft {
    mappings("stable", "39-1.12")

    runs {
        create("client") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")

            mods {
                create("ancientmagic") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }

        create("server") {
            workingDirectory(project.file("run"))

            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")

            mods {
                create("ancientmagic") {
                    sources(the<JavaPluginExtension>().sourceSets.getByName("main"))
                }
            }
        }
    }
}

repositories {
    maven("https://maven.0mods.team/releases")
    flatDir {
        dirs("libs/")
    }
}

dependencies {
    val coroutinesVersion: String by project
    val serializationVersion: String by project

    minecraft("net.minecraftforge:forge:1.12.2-14.23.5.2860")

    shadow("team._0mods:KotlinExtras:kotlin-$kotlinVersion")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.0")
    compileOnly("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.0")
}

tasks {
    shadowJar {
        archiveClassifier.set("")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        configurations = listOf(shadow)
        exclude(
            "LICENSE.txt", "META-INF/MANIFSET.MF", "META-INF/maven/**",
            "META-INF/*.RSA", "META-INF/*.SF", "META-INF/versions/**"
        )
    }

    build.get().dependsOn("shadowJar")

    withType<Jar> {
        manifest {
            attributes(
                mapOf(
                    "Specification-Title" to "MasterTech",
                    "Specification-Vendor" to "HollowHorizon",
                    "Specification-Version" to "1",
                    "Implementation-Title" to project.name,
                    "Implementation-Version" to version,
                    "Implementation-Vendor" to "AlgorithmLX",
                    "Implementation-Timestamp" to ZonedDateTime.now()
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ"))
                )
            )
        }
        finalizedBy("reobfJar")
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(javaVersion)
    }

    compileKotlin {
        useDaemonFallbackStrategy.set(false)
        compilerOptions.freeCompilerArgs.add("-Xjvm-default=all")
    }
}
kotlin {
    jvmToolchain(javaVersion)
}

fun DependencyHandler.kotlinx(module: String, version: String? = null): Any = kotlinxModule("kotlinx-$module", version)
fun DependencyHandler.kotlinxModule(module: String, version: String? = null): Any = "org.jetbrains.kotlinx:$module${version?.let { ":$version" } ?: ""}"
