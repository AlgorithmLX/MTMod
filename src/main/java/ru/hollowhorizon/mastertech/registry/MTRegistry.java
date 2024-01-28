package ru.hollowhorizon.mastertech.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import ru.hollowhorizon.mastertech.api.item.ItemBase;
import ru.hollowhorizon.mastertech.item.IchorBag;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static ru.hollowhorizon.mastertech.api.RegistryHelper.registerItem;

public class MTRegistry {
    public static final List<Item> ITEMS = new ArrayList<>();
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static final Supplier<Item> ICHOR = registerItem("ihor", ItemBase::new);
    public static final Supplier<Item> ICHOR_BAG = registerItem("ihor_bag", IchorBag::new);
    public static final Supplier<Item> MITHRITE = registerItem("mithrite", ItemBase::new);
    public static final Supplier<Item> MITHRITE_ITEM = registerItem("mithrite_ingot", ItemBase::new);
}
