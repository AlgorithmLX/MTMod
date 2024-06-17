package com.algorithmlx.mtfixes.patch

import net.minecraft.item.ItemStack
import net.minecraftforge.fluids.FluidUtil
import net.minecraftforge.items.IItemHandler
import net.minecraftforge.items.SlotItemHandler
import techreborn.items.ItemDynamicCell

class PCFluidSlotFixed(itemHandler: IItemHandler, index: Int, xPosition: Int, yPosition: Int) : SlotItemHandler(itemHandler, index, xPosition, yPosition) {
    override fun isItemValid(stack: ItemStack): Boolean {
        val s = FluidUtil.getFluidContained(stack)
        val i = stack.item

        if (i is ItemDynamicCell) return false

        return s != null && s.amount > 0
    }

    override fun putStack(stack: ItemStack) {
        val item = stack.item
        if (item !is ItemDynamicCell) super.putStack(stack)
    }

    override fun getItemStackLimit(stack: ItemStack): Int {
        val item = stack.item
        if (item is ItemDynamicCell) return 0

        return super.getItemStackLimit(stack)
    }
}