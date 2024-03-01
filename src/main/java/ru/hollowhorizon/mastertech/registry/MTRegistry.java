package ru.hollowhorizon.mastertech.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.item.ItemBase;
import ru.hollowhorizon.mastertech.item.IchorBag;
import ru.hollowhorizon.mastertech.item.MTCoin;

import java.lang.reflect.Field;
import java.util.*;

public class MTRegistry {
    public static Map<Block, ItemBlock> BLOCKS = new LinkedHashMap<>();
    public static Set<Item> ITEMS = new LinkedHashSet<>();

    public static final Item ICHOR = new ItemBase();
    public static final Item ICHOR_BAG = new IchorBag();
    public static final Item MITHRITE = new ItemBase();
    public static final Item MITHRITE_INGOT = new ItemBase();
    public static final Item ICHORIUM = new ItemBase();
    public static final Item MASTER_COIN = new MTCoin();

    public static void init() {
        MasterTech.LOGGER.info("Initializing Registry...");
        try {
            for (Field field : MTRegistry.class.getDeclaredFields()) {
                if (field.get(null) instanceof Item) {
                    Item item = (Item) field.get(null);
                    reg(item, field.getName());
                }

                if (field.get(null) instanceof Block) {
                    Block block = (Block) field.get(null);
                    reg(block, field.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static void reg(Item item, String fn) {
        ITEMS.add(item);
        String name = fn.toLowerCase(Locale.ENGLISH);
        item.setRegistryName(MasterTech.MODID, name).setTranslationKey(MasterTech.MODID + "." + name);
        MasterTech.LOGGER.debug("Item " + name + " is registered.");
    }

    private static void reg(Block block, String fn) {
        final ItemBlock blockItem = new ItemBlock(block);
        BLOCKS.put(block, blockItem);
        ITEMS.add(blockItem);
        String name = fn.toLowerCase(Locale.ENGLISH);
        block.setRegistryName(MasterTech.MODID, name).setTranslationKey(MasterTech.MODID + "." + name);
        blockItem.setRegistryName(MasterTech.MODID, name).setTranslationKey(MasterTech.MODID + "." + name);
        MasterTech.LOGGER.debug("Block " + name + " is registered.");
    }

    public static ItemBlock getMTItemByBlock(Block block) {
        return BLOCKS.get(block);
    }
}
