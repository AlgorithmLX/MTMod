package com.algorithmlx.mtfixes.mixin;

import com.algorithmlx.mtfixes.patch.PCFluidSlotFixed;
import me.desht.pneumaticcraft.common.inventory.ContainerLiquidCompressor;
import net.minecraft.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ContainerLiquidCompressor.class, remap = false)
public class LiquidCompressorContainerFixer {
    @Redirect(
            method = "<init>",
            at = @At(
                    value = "INVOKE",
                    target = "Lme/desht/pneumaticcraft/common/inventory/ContainerLiquidCompressor;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                    ordinal = 0
            )
    )
    public Slot r(ContainerLiquidCompressor instance, Slot slot) {
        return new PCFluidSlotFixed(instance.te.getPrimaryInventory(), 0, 62, 22);
    }
}
