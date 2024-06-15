package ru.hollowhorizon.mastertech.api.model

import net.minecraft.item.Item
import ru.hollowhorizon.mastertech.MasterTech

interface IItemModeledByDefault: IModeled {
    override fun registerModels() {
        if (this is Item) {
            MasterTech.proxy.getRender(this as Item, 0, "inventory")
        }
    }
}