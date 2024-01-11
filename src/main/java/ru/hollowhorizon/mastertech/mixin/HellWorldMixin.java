package ru.hollowhorizon.mastertech.mixin;

import net.minecraft.world.WorldProviderHell;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = WorldProviderHell.class)
public class HellWorldMixin {
    @Inject(
            method = "canRespawnHere",
            at = @At("RETURN")
    )
    public void respawnInj(CallbackInfoReturnable<Boolean> cir) {}
}
