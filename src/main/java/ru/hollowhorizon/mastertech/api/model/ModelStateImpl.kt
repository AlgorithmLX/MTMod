package ru.hollowhorizon.mastertech.api.model;

import com.google.common.collect.ImmutableMap;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraftforge.common.model.IModelPart;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import java.util.Map;
import java.util.Optional;

public class ModelStateImpl implements IModelState {
    private final ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> map;
    private final Optional<TRSRTransformation> trsr;

    public ModelStateImpl(Map<ItemCameraTransforms.TransformType, TRSRTransformation> map) {
        this(map, Optional.empty());
    }

    public ModelStateImpl(Map<ItemCameraTransforms.TransformType, TRSRTransformation> map, Optional<TRSRTransformation> trsr) {
        this.map = ImmutableMap.copyOf(map);
        this.trsr = trsr;
    }

    @Override
    public Optional<TRSRTransformation> apply(Optional<? extends IModelPart> optional) {
        if (!optional.isPresent() || !(optional.get() instanceof ItemCameraTransforms.TransformType) || !map.containsKey(optional.get()))
            return this.trsr;
        return Optional.ofNullable(map.get(optional.get()));
    }
}
