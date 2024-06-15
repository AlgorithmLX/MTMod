package ru.hollowhorizon.mastertech.api.item

import net.minecraft.inventory.EntityEquipmentSlot
import net.minecraft.item.ItemArmor
import ru.hollowhorizon.mastertech.MasterTech
import ru.hollowhorizon.mastertech.api.model.IItemModeledByDefault
import ru.hollowhorizon.mastertech.api.model.IModeled

class ArmorBase(materialIn: ArmorMaterial, equipmentSlot: EntityEquipmentSlot) : ItemArmor(materialIn, idRender, equipmentSlot), IItemModeledByDefault {
    init {
        idRender = if (equipmentSlot == EntityEquipmentSlot.LEGS) 2
        else 1
    }

    companion object {
        private var idRender = 0
    }
}
