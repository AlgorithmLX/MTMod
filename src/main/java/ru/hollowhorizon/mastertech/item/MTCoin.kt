package ru.hollowhorizon.mastertech.item

import net.minecraft.entity.EntityLivingBase
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation
import net.minecraft.world.World
import ru.hollowhorizon.mastertech.api.item.ItemBase

class MTCoin : ItemBase() {
    init {
        addPropertyOverride(ResourceLocation("count")) { s: ItemStack, w: World?, e: EntityLivingBase? ->
            count2texture(
                s.count
            )
        }
    }

    private fun count2texture(count: Int): Float {
        if (count in 2..6) return 1f
        else if (count in 7..12) return 2f
        else if (count in 13..20) return 3f
        else if (count in 21..40) return 4f
        else if (count >= 54) return 5f
        return 0f
    }
}
