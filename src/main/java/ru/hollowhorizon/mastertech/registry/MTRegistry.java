package ru.hollowhorizon.mastertech.registry;

import net.minecraft.item.Item;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.RegistryHelper;
import ru.hollowhorizon.mastertech.api.item.ItemBase;
import ru.hollowhorizon.mastertech.item.IchorBag;

import java.util.function.Supplier;

public class MTRegistry {
    private static final RegistryHelper register = RegistryHelper.makeRegistry(MasterTech.MODID);;

    public static final Supplier<Item> ICHOR = register.registerItem("ihor", ItemBase::new);
    public static final Supplier<Item> ICHOR_BAG = register.registerItem("ihor_bag", IchorBag::new);
    public static final Supplier<Item> MITHRITE = register.registerItem("mithrite", ItemBase::new);
    public static final Supplier<Item> MITHRITE_ITEM = register.registerItem("mithrite_ingot", ItemBase::new);

    public static void init() {}
}
