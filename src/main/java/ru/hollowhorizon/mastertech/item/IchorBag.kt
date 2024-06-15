package ru.hollowhorizon.mastertech.item

import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.SoundEvents
import net.minecraft.item.ItemStack
import net.minecraft.util.ActionResult
import net.minecraft.util.EnumActionResult
import net.minecraft.util.EnumHand
import net.minecraft.util.SoundCategory
import net.minecraft.world.World
import ru.hollowhorizon.mastertech.api.item.ItemBase
import ru.hollowhorizon.mastertech.registry.MTRegistry
import javax.annotation.Nonnull

class IchorBag : ItemBase() {
    init {
        setMaxStackSize(1)
    }

    @Nonnull
    override fun onItemRightClick(
        @Nonnull worldIn: World,
        @Nonnull playerIn: EntityPlayer,
        @Nonnull handIn: EnumHand
    ): ActionResult<ItemStack> {
        val held = playerIn.getHeldItem(handIn)

        if (held.item === this && !worldIn.isRemote) {
            val ichorEnt =
                EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, ItemStack(MTRegistry.ICHOR, 8))
            ichorEnt.setNoPickupDelay()
            worldIn.spawnEntity(ichorEnt)
            held.shrink(1)
            worldIn.playSound(
                playerIn,
                playerIn.posX,
                playerIn.posY,
                playerIn.posZ,
                SoundEvents.BLOCK_SNOW_BREAK,
                SoundCategory.MASTER,
                1.3f,
                1f
            )
            return ActionResult.newResult(EnumActionResult.SUCCESS, held)
        } else {
            return ActionResult.newResult(EnumActionResult.PASS, held)
        }
    }
}
