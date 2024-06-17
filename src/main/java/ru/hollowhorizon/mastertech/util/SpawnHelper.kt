package ru.hollowhorizon.mastertech.util

import net.minecraft.entity.player.EntityPlayerMP

object SpawnHelper {
    @JvmStatic
    fun teleportToDim(sp: EntityPlayerMP, dimension: Int) {
        val serverWorld = sp.serverWorld

        if (!serverWorld.isRemote) sp.changeDimension(
            dimension
        ) { world, entity, yaw -> }
    }
}
