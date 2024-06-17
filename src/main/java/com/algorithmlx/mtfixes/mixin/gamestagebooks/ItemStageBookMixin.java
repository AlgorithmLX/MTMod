package com.algorithmlx.mtfixes.mixin.gamestagebooks;

import net.minecraft.client.resources.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xt9.gamestagebooks.common.item.ItemStageBook;

@Mixin(value = ItemStageBook.class, remap = false)
public class ItemStageBookMixin {
    @Shadow(remap = false) private String stageHumanReadable;

    @Inject(method = "<init>", at = @At("TAIL"), remap = false)
    public void initInj(String stageName, String stageHumanReadable, String displayName, String unlockItemName, int bookColor, CallbackInfo ci) {
        this.stageHumanReadable = I18n.hasKey(stageHumanReadable) ? I18n.format(stageHumanReadable) : stageHumanReadable;
    }
}
