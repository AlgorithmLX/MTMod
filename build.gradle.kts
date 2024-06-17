import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

plugins {
    java
    `maven-publish`
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.9.22"

    id("com.gtnewhorizons.retrofuturagradle") version "1.4.0"
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
    mcVersion.set("1.12.2")

    injectedTags.put("VERSION", project.version)
    extraRunJvmArguments.add("-ea:${project.group}")

    extraTweakClasses.add("org.spongepowered.asm.launch.MixinTweaker")
}

repositories {
    maven("https://maven.0mods.team/releases")
    maven("https://repo.spongepowered.org/repository/maven-public/")
    maven("https://raw.github.com/TehNut/temporary-maven-thing/master/maven")
    maven("https://maven.thiakil.com")
    maven("https://dvs1.progwml6.com/files/maven")
    maven("https://www.cursemaven.com/")
    maven("https://api.modrinth.com/maven")
    maven("https://maven.cleanroommc.com")
    flatDir {
        dirs("libs/")
    }
}

dependencies {
    val coroutinesVersion: String by project
    val serializationVersion: String by project
    val handler = project.dependencies

    annotationProcessor("org.ow2.asm:asm-debug-all:5.2")
    annotationProcessor("com.google.guava:guava:32.1.2-jre")
    annotationProcessor("com.google.code.gson:gson:2.8.9")

    implementation("zone.rong:mixinbooter:9.1") { isTransitive = false }
    annotationProcessor("zone.rong:mixinbooter:9.1") { isTransitive = false }
    handler.add("annotationProcessor", "zone.rong:mixinbooter:9.1") { isTransitive = false }
    // For fixes

    compileOnly(rfg.deobf("curse.maven:thaumcraft-223628:2629023"))
    compileOnly(rfg.deobf("curse.maven:baubles-227083:2518667"))

    compileOnly(rfg.deobf("curse.maven:pneumaticcraft-repressurized-281849:2978408"))

    compileOnly(rfg.deobf("curse.maven:techreborn-233564:2966851"))
    compileOnly(rfg.deobf("curse.maven:reborncore-237903:3330308"))

    compileOnly(rfg.deobf("curse.maven:gamestage-books-296392:2735851"))

    // Fixes deps
    compileOnly(rfg.deobf("curse.maven:computercraft-67504:2478952"))

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
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")),
                )
            )
        }
    }



//    withType<JavaCompile> {
//        options.encoding = "UTF-8"
//        options.release.set(javaVersion)
//    }

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
