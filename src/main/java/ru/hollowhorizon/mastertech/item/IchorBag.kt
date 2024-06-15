package ru.hollowhorizon.mastertech.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import ru.hollowhorizon.mastertech.api.item.ItemBase;
import ru.hollowhorizon.mastertech.registry.MTRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class IchorBag extends ItemBase {
    public IchorBag() {
        setMaxStackSize(1);
    }

    @Override
    public @Nonnull ActionResult<ItemStack> onItemRightClick(@Nonnull World worldIn, @Nonnull EntityPlayer playerIn, @Nonnull EnumHand handIn) {
        ItemStack held = playerIn.getHeldItem(handIn);

        if (held.getItem() == this && !worldIn.isRemote) {
            EntityItem ichorEnt = new EntityItem(worldIn, playerIn.posX, playerIn.posY, playerIn.posZ, new ItemStack(MTRegistry.ICHOR, 8));
            ichorEnt.setNoPickupDelay();
            worldIn.spawnEntity(ichorEnt);
            held.shrink(1);
            worldIn.playSound(playerIn, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.BLOCK_SNOW_BREAK, SoundCategory.MASTER, 1.3f, 1f);
            return ActionResult.newResult(EnumActionResult.SUCCESS, held);
        } else {
            return ActionResult.newResult(EnumActionResult.PASS, held);
        }
    }
}
