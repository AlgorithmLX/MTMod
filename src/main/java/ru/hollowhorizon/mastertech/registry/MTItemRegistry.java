package ru.hollowhorizon.mastertech.registry;

import net.minecraft.item.Item;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.item.ItemBase;
import ru.hollowhorizon.mastertech.item.IchorBag;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

public class MTItemRegistry {
    public static Set<Item> ITEMS = new LinkedHashSet<>();

    public static final Item ICHOR = new ItemBase();
    public static final Item ICHOR_BAG = new IchorBag();
    public static final Item MITHRITE = new ItemBase();
    public static final Item MITHRITE_ITEM = new ItemBase();

    public static void init() {
        try {
            for (Field field : MTItemRegistry.class.getDeclaredFields()) {
                if (field.get(null) instanceof Item) {
                    Item item = (Item) field.get(null);
                    reg(item, field.getName());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void reg(Item item, String fn) {
        ITEMS.add(item);
        String name = fn.toLowerCase(Locale.ENGLISH);
        item.setRegistryName(MasterTech.MODID, fn).setUnlocalizedName(MasterTech.MODID + "." + name);
    }
}
