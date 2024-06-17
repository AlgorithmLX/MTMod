package com.algorithmlx.mtfixes.patch

import net.minecraft.inventory.IInventory
import net.minecraft.inventory.Slot
import net.minecraft.item.ItemStack
import thaumcraft.common.items.curios.ItemPrimordialPearl

class TCPatchedInputSlot(inv: IInventory, id: Int, x: Int, y: Int) : Slot(inv, id, x, y) {
    override fun isItemValid(stack: ItemStack): Boolean {
        val item = stack.item

        if (item !is ItemPrimordialPearl) return false

        return true
    }

    override fun putStack(stack: ItemStack) {
        val item = stack.item
        if (item !is ItemPrimordialPearl) super.putStack(stack)
    }

    override fun getItemStackLimit(stack: ItemStack): Int {
        val item = stack.item
        if (item is ItemPrimordialPearl) return 0

        return super.getItemStackLimit(stack)
    }
}