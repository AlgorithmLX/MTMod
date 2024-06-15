package ru.hollowhorizon.mastertech.api.item

import net.minecraft.item.Item
import ru.hollowhorizon.mastertech.MasterTech
import ru.hollowhorizon.mastertech.api.model.IItemModeledByDefault
import ru.hollowhorizon.mastertech.api.model.IModeled

open class ItemBase : Item(), IItemModeledByDefault {
    init {
        creativeTab = MasterTech.tab
    }
}
