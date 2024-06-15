@file:OptIn(ExperimentalSerializationApi::class)

package ru.hollowhorizon.mastertech.api.helpers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import net.minecraft.client.Minecraft
import ru.hollowhorizon.mastertech.MasterTech.Companion.LOGGER
import java.io.File

val shouldToClientInit: MutableList<() -> Unit> = mutableListOf()
val shouldToServerInit: MutableList<() -> Unit> = mutableListOf()

fun <T> handleConfigAnnotations(obj: Class<T>) {
    val fields = obj.declaredFields
    fields.filter { it.isAnnotationPresent(Config::class.java) }.forEach {
        val config = it.getAnnotation(Config::class.java)
        val inst = it[null]
        if (inst is KSerializer<*>) {
            val dist = config.dist
            val name = config.fileName

            when(dist) {
                Config.ConfigDist.CLIENT -> {
                    shouldToClientInit.add {
                        loadConfig(inst, name)
                    }
                }

                Config.ConfigDist.SERVER -> {
                    shouldToServerInit.add {
                        loadConfig(inst, name)
                    }
                }
            }
        } else throw IllegalStateException("Failed to load or generate config, because field with name ${it.name} is not serializable!")
    }
}

inline fun <reified T> loadConfig(value: T, fileName: String): T {
    val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        prettyPrint = true
        coerceInputValues = true
    }

    val file = Minecraft.getMinecraft().gameDir.resolve("config/").resolve("$fileName.json")

    return if (file.exists()) value
    else {
        encodeCfg(json, value, file)
        value
    }
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> encodeCfg(json: Json, value: T, file: File, defaultEncode: String = "") {
    try {
        if (!file.exists()) {
            file.parentFile.mkdirs()
            file.createNewFile()
        }

        if (defaultEncode.isNotEmpty())
            json.encodeToStream(defaultEncode, file.outputStream())
        else json.encodeToStream(value, file.outputStream())
    } catch (e: FileSystemException) {
        LOGGER.error("Failed to write config to file '$file'", e)
    }
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> decodeCfg(json: Json, file: File): T = try {
    json.decodeFromStream(file.inputStream())
} catch (e: FileSystemException) {
    LOGGER.error("Failed to read config from file '$file'", e)
    throw e
}
