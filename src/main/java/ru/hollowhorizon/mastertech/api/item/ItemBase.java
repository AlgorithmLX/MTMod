package ru.hollowhorizon.mastertech.api.item;

import net.minecraft.item.Item;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.model.IModeled;

public class ItemBase extends Item implements IModeled {
    public ItemBase() {
        setCreativeTab(MasterTech.tab);
    }

    @Override
    public void registerModels() {
        MasterTech.proxy.getRender(this, 0, "inventory");
    }
}
