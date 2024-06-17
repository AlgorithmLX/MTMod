package com.algorithmlx.mtfixes.mixin.accessors;

import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.IInventory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ContainerRepair.class)
public interface RepairContainerAccessor {
    @Accessor("inputSlots")
    IInventory inputSlots();
}
