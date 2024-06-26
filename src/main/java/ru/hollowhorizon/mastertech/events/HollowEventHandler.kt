package ru.hollowhorizon.mastertech.events

import net.minecraft.block.Block
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.init.Blocks
import net.minecraft.item.Item
import net.minecraft.stats.StatList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.DimensionType
import net.minecraft.world.World
import net.minecraft.world.WorldServer
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.fml.common.eventhandler.EventPriority
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent
import ru.hollowhorizon.mastertech.MasterTech
import ru.hollowhorizon.mastertech.api.model.IModeled
import ru.hollowhorizon.mastertech.registry.MTRegistry
import ru.hollowhorizon.mastertech.util.SpawnHelper

@Suppress("SENSELESS_COMPARISON")
@EventBusSubscriber(modid = MasterTech.MODID)
object HollowEventHandler {
    @JvmStatic
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun itemReg(reg: RegistryEvent.Register<Item>) {
        val fr = reg.registry

        for (item in MTRegistry.ITEMS) {
            fr.register(item)
        }
    }

    @JvmStatic
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun blockReg(reg: RegistryEvent.Register<Block>) {
        val fr = reg.registry

        for (block in MTRegistry.BLOCKS.keys) {
            fr.register(block)
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun modelRegister(e: ModelRegistryEvent) {
        for (item in MTRegistry.ITEMS) {
            if (item is IModeled) {
                (item as IModeled).registerModels()
            }
        }

        for (block in MTRegistry.BLOCKS.keys) {
            if (block is IModeled) {
                (block as IModeled).registerModels()
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onPlayerJoining(event: PlayerLoggedInEvent) {
        val player = event.player

        if (!player.world.isRemote) {
            val sp = player as EntityPlayerMP

            val timePlayed = sp.statFile.readStat(StatList.PLAY_ONE_MINUTE)
            if (timePlayed == 0) {
                SpawnHelper.teleportToDim(sp, DimensionType.NETHER.id)
            }
        }
    }

    @JvmStatic
    @SubscribeEvent
    fun onPlayerRespawn(e: PlayerRespawnEvent) {
        val player = e.player
        val level = player.world

        if (!level.isRemote) {
            if (player is EntityPlayerMP) {
                val bedLocation: BlockPos? =
                    if (player.dimension == DimensionType.OVERWORLD.id) player.getBedLocation(DimensionType.OVERWORLD.id)
                    else {
                        val dim = player.getBedLocation(player.dimension)
                        if (dim != null) dim
                        else player.getBedLocation(DimensionType.OVERWORLD.id)
                    }

                // Fuck MC 1.12.2.
                // Mojang and Lex, I hate you!
                // Fuck you.
                if (bedLocation == null) {
                    if (player.dimension != DimensionType.NETHER.id) {
                        SpawnHelper.teleportToDim(player, DimensionType.NETHER.id)
                    }
                }
            }
        }
    }

    @JvmStatic
    private fun nullPlace(world: World, vararg pos: BlockPos) {
        pos.forEach { place(world, Blocks.AIR, it) }
    }

    private fun placeNetherrack(world: World, vararg pos: BlockPos) {
        pos.forEach { place(world, Blocks.NETHERRACK, it) }
    }

    private fun place(world: World, block: Block, pos: BlockPos) {
        world.setBlockState(pos, block.blockState.baseState, 3)
    }

    private fun buildSafeCube(wld: WorldServer, x: Number, y: Number, z: Number) {
        val positions = mutableListOf<BlockPos>()
        val baseX = x.toInt()
        val baseY = y.toInt()
        val baseZ = z.toInt()

        // Collect positions for Netherrack
        for (dx in -2 .. 2) {
            for (dy in -2 .. 2) {
                for (dz in -2 .. 2) {
                    positions.add(pos(baseX + dx, baseY + dy, baseZ + dz))
                }
            }
        }
        placeNetherrack(wld, *positions.toTypedArray())

        // Collect positions for Air
        positions.clear()

        for (dy in -1 .. 1) {
            for (dx in -1 .. 1) {
                for (dz in -1 .. 1) {
                    positions.add(pos(baseX + dx, baseY + dy, baseZ + dz))
                }
            }
        }
        nullPlace(wld, *positions.toTypedArray())

        // Place Nether Brick
        place(wld, Blocks.NETHER_BRICK, pos(baseX, baseY - 2, baseZ))
    }

    private fun pos(x: Number, y: Number, z: Number): BlockPos {
        return BlockPos(x.toInt(), y.toInt(), z.toInt())
    }
}
