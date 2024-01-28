package ru.hollowhorizon.mastertech.api.item;

import net.minecraft.item.Item;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.IModeled;

public class ItemBase extends Item implements IModeled {
    public ItemBase(String id) {
        setRegistryName(id);
        setCreativeTab(MasterTech.tab);
        setUnlocalizedName(String.format("%s.%s", MasterTech.MODID, id));
    }

    @Override
    public void registerModels() {
        MasterTech.proxy.getRender(this, 0, "inventory");
    }
}
