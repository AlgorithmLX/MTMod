package ru.hollowhorizon.mastertech.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import ru.hollowhorizon.mastertech.api.item.ItemBase;
import ru.hollowhorizon.mastertech.item.IchorBag;

import java.util.ArrayList;
import java.util.List;

public class RegistryArray {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Item ICHOR = new ItemBase("ichor");
    public static final Item ICHOR_BAG = new IchorBag();


}
