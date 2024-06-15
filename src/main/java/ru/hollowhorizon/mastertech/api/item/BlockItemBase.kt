package ru.hollowhorizon.mastertech.api.item

import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import ru.hollowhorizon.mastertech.MasterTech
import ru.hollowhorizon.mastertech.api.model.IItemModeledByDefault
import ru.hollowhorizon.mastertech.api.model.IModeled

class BlockItemBase(block: Block) : ItemBlock(block), IItemModeledByDefault {
    init {
        creativeTab = MasterTech.tab
    }
}
