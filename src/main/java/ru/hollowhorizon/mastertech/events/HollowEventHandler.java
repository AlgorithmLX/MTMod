package ru.hollowhorizon.mastertech.events;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.util.SpawnHelper;

@Mod.EventBusSubscriber(modid = MasterTech.MODID)
public class HollowEventHandler {

    @SubscribeEvent
    public static void onPlayerJoining(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;

        if (!player.world.isRemote) {
            EntityPlayerMP sp = (EntityPlayerMP) player;

            int timePlayed = sp.getStatFile().readStat(StatList.PLAY_ONE_MINUTE);

            if (timePlayed == 0) {
                BlockPos pos = new BlockPos(142, 60, 54);
                SpawnHelper.teleportToDim(sp, DimensionType.NETHER.getId(), pos);

                WorldServer worldAfterChange = sp.getServerWorld();
            }
        }
    }

    private static void nullPlace(World world, BlockPos pos) {
        place(world, Blocks.AIR, pos);
    }

    private static void placeNetherrack(World world, BlockPos pos) {
        place(world, Blocks.NETHERRACK, pos);
    }

    private static void place(World world, Block block, BlockPos pos) {
        world.setBlockState(pos, block.getBlockState().getBaseState(), 3);
    }
}
