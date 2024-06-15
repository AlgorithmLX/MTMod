package ru.hollowhorizon.mastertech.util;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;

public class SpawnHelper {
    public static void teleportToDim(EntityPlayerMP sp, int dimension) {
        WorldServer serverWorld = sp.getServerWorld();

        if (!serverWorld.isRemote)
            sp.changeDimension(
                    dimension,
                    (world, entity, yaw) -> {}
            );
    }
}
