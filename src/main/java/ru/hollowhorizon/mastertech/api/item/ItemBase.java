package ru.hollowhorizon.mastertech.api.item;

import net.minecraft.item.Item;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.IModeled;

public class ItemBase extends Item implements IModeled {
    public ItemBase(String id) {
        setRegistryName(id);
        setCreativeTab(MasterTech.tab);
        setUnlocalizedName(String.format("mastertech.%s", id));
    }

    @Override
    public void registerModels() {}
}
