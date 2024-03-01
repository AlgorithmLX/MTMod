package ru.hollowhorizon.mastertech.events;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.registries.IForgeRegistry;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.model.IModeled;
import ru.hollowhorizon.mastertech.registry.MTRegistry;
import ru.hollowhorizon.mastertech.util.SpawnHelper;

@Mod.EventBusSubscriber(modid = MasterTech.MODID)
public class HollowEventHandler {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void itemReg(final RegistryEvent.Register<Item> reg) {
        final IForgeRegistry<Item> fr = reg.getRegistry();

        for (Item item : MTRegistry.ITEMS) {
            fr.register(item);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void blockReg(RegistryEvent.Register<Block> reg) {
        final IForgeRegistry<Block> fr = reg.getRegistry();

        for (Block block : MTRegistry.BLOCKS.keySet()) {
            fr.register(block);
        }
    }

    @SubscribeEvent
    public static void modelRegister(ModelRegistryEvent _e) {
        for (Item item : MTRegistry.ITEMS) {
            if (item instanceof IModeled) {
                ((IModeled) item).registerModels();
            }
        }

        for (Block block : MTRegistry.BLOCKS.keySet()) {
            if (block instanceof IModeled) {
                ((IModeled) block).registerModels();
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerJoining(PlayerEvent.PlayerLoggedInEvent event) {
        EntityPlayer player = event.player;

        if (!player.world.isRemote) {
            EntityPlayerMP sp = (EntityPlayerMP) player;

            int timePlayed = sp.getStatFile().readStat(StatList.PLAY_ONE_MINUTE);
            if (timePlayed == 0) {
                SpawnHelper.teleportToDim(sp, DimensionType.NETHER.getId());
                if (player.dimension == DimensionType.NETHER.getId()) {
                    WorldServer serverWorld = sp.getServerWorld();
                    buildSafeCube(serverWorld, sp.posX, sp.posY, sp.posZ);
                    BlockPos pos = pos(sp.posX, sp.posY, sp.posZ);
                    sp.setSpawnPoint(pos, true);
                    sp.setSpawnDimension(DimensionType.NETHER.getId());
                    sp.setSpawnChunk(pos, true, DimensionType.NETHER.getId());
                }
            }
        }
    }

    @SubscribeEvent
    public static void addTooltips(ItemTooltipEvent e) {
        ItemStack stack = e.getItemStack();
        if (stack.getItem() == MTRegistry.ICHOR)
            e.getToolTip().add("msg.mastertech.ichor");
        else if (stack.getItem() == MTRegistry.ICHOR_BAG)
            e.getToolTip().add("msg.mastertech.ichor_bag");
    }

    private static void nullPlace(World world, BlockPos... pos) {
        for (BlockPos pos1 : pos) {
            place(world, Blocks.AIR, pos1);
        }
    }

    private static void placeNetherrack(World world, BlockPos... pos) {
        for (BlockPos pos1 : pos) {
            place(world, Blocks.NETHERRACK, pos1);
        }
    }
    private static void place(World world, Block block, BlockPos pos) {
        world.setBlockState(pos, block.getBlockState().getBaseState(), 3);
    }

    private static void buildSafeCube(WorldServer wld, Number x, Number y, Number z) {
        int x1 = (x.intValue()) + 1;
        int x2 = (x.intValue()) + 2;
        int x_1 = (x.intValue()) - 1;
        int x_2 = (x.intValue()) - 2;

        int y1 = (y.intValue()) + 1;
        int y2 = (y.intValue()) + 2;
        int y_1 = (y.intValue()) - 1;
        int y_2 = (y.intValue()) - 2;

        int z1 = (z.intValue()) + 1;
        int z2 = (z.intValue()) + 2;
        int z_1 = (z.intValue()) - 1;
        int z_2 = (z.intValue()) - 2;
        // Netherrack wall place
        placeNetherrack(
                wld,
                //ABOVE PLATFORM
                pos(x_2, y2, z_2), pos(x_1, y2, z_2), pos(x, y2, z_2), pos(x1, y2, z_2), pos(x2, y2, z_2),
                pos(x_2, y2, z_1), pos(x_1, y2, z_1), pos(x, y2, z_1), pos(x1, y2, z_1), pos(x2, y2, z_1),
                pos(x_2, y2, z), pos(x_1, y2, z), pos(x, y2, z), pos(x1, y2, z), pos(x2, y2, z),
                pos(x_2, y2, z1), pos(x_1, y2, z1), pos(x, y2, z1), pos(x1, y2, z1), pos(x2, y2, z1),
                pos(x_2, y2, z2), pos(x_1, y2, z2), pos(x, y2, z2), pos(x1, y2, z2), pos(x2, y2, z2),
                // Y: 1 FRAME
                pos(x_2, y1, z_2), pos(x_1, y1, z_2), pos(x, y1, z_2), pos(x1, y1, z_2), pos(x2, y1, z_2),
                pos(x_2, y1, z_1), pos(x2, y1, z_1),
                pos(x_2, y1, z), pos(x2, y1, z),
                pos(x_2, y1, z1), pos(x2, y1, z1),
                pos(x_2, y1, z2), pos(x_1, y1, z2), pos(x, y1, z2), pos(x1, y1, z2), pos(x2, y1, z2),
                // Y: 0 FRAME
                pos(x_2, y, z_2), pos(x_1, y, z_2), pos(x, y, z_2), pos(x1, y, z_2), pos(x2, y, z_2),
                pos(x_2, y, z_1), pos(x2, y, z_1),
                pos(x_2, y, z), pos(x2, y, z),
                pos(x_2, y, z1), pos(x2, y, z1),
                pos(x_2, y, z2), pos(x_1, y, z2), pos(x, y, z2), pos(x1, y, z2), pos(x2, y, z2),
                // Y: -1 FRAME
                pos(x_2, y_1, z_2), pos(x_1, y_1, z_2), pos(x, y_1, z_2), pos(x1, y_1, z_2), pos(x2, y_1, z_2),
                pos(x_2, y_1, z_1), pos(x2, y_1, z_1),
                pos(x_2, y_1, z), pos(x2, y_1, z),
                pos(x_2, y_1, z1), pos(x2, y_1, z1),
                pos(x_2, y_1, z2), pos(x_1, y_1, z2), pos(x, y_1, z2), pos(x1, y_1, z2), pos(x2, y_1, z2),
                //BELOW PLATFORM
                pos(x_2, y_2, z_2), pos(x_1, y_2, z_2), pos(x, y_2, z_2), pos(x1, y_2, z_2), pos(x2, y_2, z_2),
                pos(x_2, y_2, z_1), pos(x_1, y_2, z_1), pos(x, y_2, z_1), pos(x1, y_2, z_1), pos(x2, y_2, z_1),
                pos(x_2, y_2, z), pos(x_1, y_2, z), pos(x1, y_2, z), pos(x2, y_2, z),
                pos(x_2, y_2, z1), pos(x_1, y_2, z1), pos(x, y_2, z1), pos(x1, y_2, z1), pos(x2, y_2, z1),
                pos(x_2, y_2, z2), pos(x_1, y_2, z2), pos(x, y_2, z2), pos(x1, y_2, z2), pos(x2, y_2, z2)
        );
        // Void cube place
        nullPlace(
                wld,
                // Y: 1 CUBE
                pos(x_1, y1, z_1), pos(x, y1, z_1), pos(x1, y1, z_1),
                pos(x_1, y1, z), pos(x, y1, z), pos(x1, y1, z),
                pos(x_1, y1, z1), pos(x, y1, z1), pos(x1, y1, z1),
                // Y: 0 CUBE
                pos(x_1, y, z_1), pos(x, y, z_1), pos(x1, y, z_1),
                pos(x_1, y, z), pos(x, y, z), pos(x1, y, z),
                pos(x_1, y, z1), pos(x, y, z1), pos(x1, y, z1),
                // Y: -1 CUBE
                pos(x_1, y_1, z_1), pos(x, y_1, z_1), pos(x1, y_1, z_1),
                pos(x_1, y_1, z), pos(x, y_1, z), pos(x1, y_1, z),
                pos(x_1, y_1, z1), pos(x, y_1, z1), pos(x1, y_1, z1)
        );

        //central netherbrick block, below than player
        place(wld, Blocks.NETHER_BRICK, pos(x, y_2, z));
    }

    public static BlockPos pos(Number x, Number y, Number z) {
        return new BlockPos(x.intValue(), y.intValue(), z.intValue());
    }
}
