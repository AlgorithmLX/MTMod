package ru.hollowhorizon.mastertech.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.hollowhorizon.mastertech.api.item.ItemBase;
import ru.hollowhorizon.mastertech.registry.RegistryArray;

import javax.annotation.Nonnull;

public class IchorBag extends ItemBase {
    public IchorBag() {
        super("ichor_bag");
        maxStackSize = 1;
    }

    @Override
    public @Nonnull ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack held = playerIn.getHeldItem(handIn);

        if (held.getItem() == this && !worldIn.isRemote) {
            EntityItem ichorEnt = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(RegistryArray.ICHOR, 8));
            ichorEnt.setNoPickupDelay();
            worldIn.spawnEntity(ichorEnt);
            held.shrink(1);
            return ActionResult.newResult(EnumActionResult.SUCCESS, held);
        } else {
            return ActionResult.newResult(EnumActionResult.PASS, held);
        }
    }
}
