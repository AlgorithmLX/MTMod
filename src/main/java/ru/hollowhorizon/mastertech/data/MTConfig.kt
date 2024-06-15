package ru.hollowhorizon.mastertech.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Suppress("PROVIDED_RUNTIME_TOO_LOW")
@Serializable
data class MTConfig(
    @SerialName("mt_version") val mtVersion: String = "1.5.8",
    @SerialName("nether_spawn") val spawnInNether: Boolean = true
)
