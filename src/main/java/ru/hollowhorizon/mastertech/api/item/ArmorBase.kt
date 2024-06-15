package ru.hollowhorizon.mastertech.api.item;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import ru.hollowhorizon.mastertech.MasterTech;
import ru.hollowhorizon.mastertech.api.model.IModeled;

public class ArmorBase extends ItemArmor implements IModeled {
    private static int idRender;

    public ArmorBase(ArmorMaterial materialIn, EntityEquipmentSlot equipmentSlot) {
        super(materialIn, idRender, equipmentSlot);
        if (equipmentSlot == EntityEquipmentSlot.LEGS) idRender = 2;
        else idRender = 1;
    }

    @Override
    public void registerModels() {
        MasterTech.proxy.getRender(this, 0, "inventory");
    }
}
