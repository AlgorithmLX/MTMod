package com.algorithmlx.mtfixes.mixin.gamestagebooks;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import xt9.gamestagebooks.common.item.ItemBase;

@Mixin(value = ItemBase.class, remap = false)
public class ItemBaseMixin {
    @Shadow(remap = false) public String displayName;

    //TODO: Fix
    //@Inject(method = "getItemStackDisplayName", at = @At("RETURN"), cancellable = true, remap = true)
    public void displayName(ItemStack stack, CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(I18n.hasKey(this.displayName) ? I18n.format(this.displayName) : this.displayName);
    }
}
