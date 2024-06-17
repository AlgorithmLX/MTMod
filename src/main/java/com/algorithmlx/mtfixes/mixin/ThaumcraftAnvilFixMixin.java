package com.algorithmlx.mtfixes.mixin;

import com.algorithmlx.mtfixes.patch.TCPatchedInputSlot;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import ru.hollowhorizon.mastertech.MasterTech;

@Mixin(ContainerRepair.class)
public abstract class ThaumcraftAnvilFixMixin extends Container {
    @Shadow @Final private static Logger LOGGER;

    @Shadow @Final private IInventory inputSlots;

    @Redirect(
            method = "<init>(Lnet/minecraft/entity/player/InventoryPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/EntityPlayer;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/inventory/ContainerRepair;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                    ordinal = 0
            )
    )
    public Slot redirectFirst(ContainerRepair instance, Slot slot) {
        MasterTech.LOGGER.info("redirecting slots 0");
        return new TCPatchedInputSlot(this.inputSlots, 0, 27, 47);
    }

    @Redirect(
            method = "<init>(Lnet/minecraft/entity/player/InventoryPlayer;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/entity/player/EntityPlayer;)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/inventory/ContainerRepair;addSlotToContainer(Lnet/minecraft/inventory/Slot;)Lnet/minecraft/inventory/Slot;",
                    ordinal = 1
            )
    )
    public Slot redirectSecond(ContainerRepair instance, Slot slot) {
        MasterTech.LOGGER.info("redirecting slots 1 (anvil)");
        return new TCPatchedInputSlot(this.inputSlots, 1, 76, 47);
    }
}
