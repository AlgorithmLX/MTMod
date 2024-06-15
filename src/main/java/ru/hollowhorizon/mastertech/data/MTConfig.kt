@file:Suppress("PROVIDED_RUNTIME_TOO_LOW")

package ru.hollowhorizon.mastertech.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MTConfig(
    @SerialName("mt_version") val mtVersion: String = "1.5.8.2",
    @SerialName("nether_spawn") val spawnInNether: Boolean = true,
    @SerialName("experiments") val experiments: Experiments = Experiments()
)

@Serializable
data class Experiments(
    @SerialName("allow_new_coins") val allowNewCoins: Boolean = true,
    @SerialName("allow_mithrite") val allowMithrite: Boolean = true
)
