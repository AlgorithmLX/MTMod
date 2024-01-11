package ru.hollowhorizon.mastertech.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.hollowhorizon.mastertech.registry.RegistryArray;

@Mod.EventBusSubscriber
public class RegistryLoader {
    @SubscribeEvent
    public static void itemReg(RegistryEvent.Register<Item> reg) {
        Item[] ia = RegistryArray.ITEMS.toArray(new Item[0]);
        reg.getRegistry().registerAll(ia);
    }

    @SubscribeEvent
    public static void blockReg(RegistryEvent.Register<Block> reg) {
        Block[]ba = RegistryArray.BLOCKS.toArray(new Block[0]);
        reg.getRegistry().registerAll(ba);
    }
}
