@file:OptIn(ExperimentalSerializationApi::class)

package ru.hollowhorizon.mastertech.api.helpers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import kotlinx.serialization.json.encodeToStream
import net.minecraft.client.Minecraft
import ru.hollowhorizon.mastertech.MasterTech.Companion.LOGGER
import java.io.File

inline fun <reified T> loadConfig(value: T, fileName: String): T {
    val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
        prettyPrint = true
        coerceInputValues = true
//        allowComment = true
    }

    val file = Minecraft.getMinecraft().gameDir.resolve("config/").resolve("$fileName.json")

    var encoded = json.encodeToString(value)

    val methods = T::class.java.declaredMethods
    methods.filter { it.isAnnotationPresent(JSONComment::class.java) }.forEach {
        val commentAnnot = it.getAnnotation(JSONComment::class.java)
        val propertyName =
            if (it.isAnnotationPresent(SerialName::class.java)) it.getAnnotation(SerialName::class.java).value
            else it.name
    }

    """
        "dassdfasdfas"
    """.trimIndent()

    return if (file.exists()) value
    else {
        encodeCfg(json, value, file, encoded)
        value
    }
}

@OptIn(ExperimentalSerializationApi::class)
inline fun <reified T> encodeCfg(json: Json, value: T, file: File, defaultEncode: String) {
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

fun main() {
    var lol = """
        {
            "нубля": "Зачем значение?"
        }
    """.trimIndent()

    println("In:\n$lol")

    lol = lol.comment("\"нубля\"", "Вот коммент")

    println("Out:\n$lol")
}

fun String.comment(key: String, vararg comment: String): String {
    val commentBuilder = StringBuilder()

    if (comment.size in 1..2) {
        commentBuilder.append("//").append(' ').append(comment[0])
    } else {
        commentBuilder.append("/*").append("\n")

        comment.forEach {
            commentBuilder.append(' ').append("*").append(' ').append(it).append("\n")
        }

        commentBuilder.append("*/")
    }

    val builded = commentBuilder.toString()

    return this.replace(Regex("(?m)^$key:"), "$builded\n$key:")
}
