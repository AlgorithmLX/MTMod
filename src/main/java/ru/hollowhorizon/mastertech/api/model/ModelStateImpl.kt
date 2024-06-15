package ru.hollowhorizon.mastertech.api.model

import com.google.common.collect.ImmutableMap
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType
import net.minecraftforge.common.model.IModelPart
import net.minecraftforge.common.model.IModelState
import net.minecraftforge.common.model.TRSRTransformation
import java.util.*

class ModelStateImpl @JvmOverloads constructor(
    map: Map<TransformType, TRSRTransformation>,
    private val trsr: Optional<TRSRTransformation> = Optional.empty()
) : IModelState {
    private val map: ImmutableMap<TransformType, TRSRTransformation> = ImmutableMap.copyOf(map)

    override fun apply(optional: Optional<out IModelPart?>): Optional<TRSRTransformation> {
        if (!optional.isPresent || optional.get() !is TransformType || !map.containsKey(optional.get())) return this.trsr
        return Optional.ofNullable(map[optional.get()])
    }
}
