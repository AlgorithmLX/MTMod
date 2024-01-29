package ru.hollowhorizon.mastertech.api.model;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraftforge.common.model.IModelState;
import net.minecraftforge.common.model.TRSRTransformation;

import javax.vecmath.Vector3f;
import java.util.HashMap;
import java.util.Map;

public class Transforms {
    public static final IModelState DEFAULT_BLOCK;
    public static final IModelState DEFAULT_ITEM;
    public static final IModelState DEFAULT_TOOL;
    public static final IModelState DEFAULT_BOW;
    public static final IModelState DEFAULT_HANDHELD_ROD;

    static {
        Map<TransformType, TRSRTransformation> map;
        TRSRTransformation thirdPerson;
        TRSRTransformation firstPerson;

        map = new HashMap<>();
        thirdPerson = create(0F,2.5F, 0F,75F, 45F, 0F,0.375F );
        map.put(TransformType.GUI, create(0F,  0F, 0F,30F,225F, 0F,0.625F));
        map.put(TransformType.GROUND, create(0F, 3F, 0F, 0F, 0F, 0F, 0.25F));
        map.put(TransformType.FIXED, create(0F, 0F, 0F, 0F, 0F, 0F, 0.5F));
        map.put(TransformType.THIRD_PERSON_RIGHT_HAND, thirdPerson);
        map.put(TransformType.THIRD_PERSON_LEFT_HAND, flipLeft(thirdPerson));
        map.put(TransformType.FIRST_PERSON_RIGHT_HAND, create(0F, 0F, 0F, 0F, 45F, 0F, 0.4F));
        map.put(TransformType.FIRST_PERSON_LEFT_HAND, create(0F, 0F, 0F, 0F, 225F, 0F, 0.4F));
        DEFAULT_BLOCK = new ModelStateImpl(map);

        map = new HashMap<>();
        thirdPerson = create(0F, 3F, 1F, 0F, 0F, 0F, 0.55F);
        firstPerson = create(1.13F,3.2F,1.13F, 0F,-90F,25F, 0.68F);
        map.put(TransformType.GROUND, create(0F, 2F, 0F, 0F, 0F, 0F, 0.5F));
        map.put(TransformType.HEAD, create(0F, 13F, 7F, 0F,180F, 0F, 1F));
        map.put(TransformType.THIRD_PERSON_RIGHT_HAND, thirdPerson);
        map.put(TransformType.THIRD_PERSON_LEFT_HAND, flipLeft(thirdPerson));
        map.put(TransformType.FIRST_PERSON_RIGHT_HAND, firstPerson);
        map.put(TransformType.FIRST_PERSON_LEFT_HAND, flipLeft(firstPerson));
        DEFAULT_ITEM = new ModelStateImpl(map);

        map = new HashMap<>();
        map.put(TransformType.GROUND, create(0F, 2F,0F, 0F,0F, 0F, 0.5F));
        map.put(TransformType.THIRD_PERSON_RIGHT_HAND, create(   0F,  4F, 0.5F, 0F,-90F, 55,0.85F));
        map.put(TransformType.THIRD_PERSON_LEFT_HAND, create(   0F,  4F, 0.5F, 0F, 90F,-55,0.85F));
        map.put(TransformType.FIRST_PERSON_RIGHT_HAND, create(1.13F,3.2F,1.13F, 0F,-90F, 25,0.68F));
        map.put(TransformType.FIRST_PERSON_LEFT_HAND, create(1.13F,3.2F,1.13F, 0F, 90F,-25,0.68F));
        DEFAULT_TOOL = new ModelStateImpl(map);

        map = new HashMap<>();
        map.put(TransformType.GROUND, create(0F, 2F, 0F, 0F, 0F, 0F, 0.5F));
        map.put(TransformType.THIRD_PERSON_RIGHT_HAND, create(-1F, -2F, 2.5F,-80F, 260F,-40F, 0.9F));
        map.put(TransformType.THIRD_PERSON_LEFT_HAND, create(-1F, -2F, 2.5F,-80F,-280F, 40F, 0.9F));
        map.put(TransformType.FIRST_PERSON_RIGHT_HAND, create(1.13F,3.2F,1.13F, 0F, -90F, 25F,0.68F));
        map.put(TransformType.FIRST_PERSON_LEFT_HAND, create(1.13F,3.2F,1.13F, 0F, 90F,-25F,0.68F));
        DEFAULT_BOW = new ModelStateImpl(map);

        map = new HashMap<>();
        map.put(TransformType.GROUND, create(0F, 2F, 0F, 0F, 0F,0F, 0.5F));
        map.put(TransformType.THIRD_PERSON_RIGHT_HAND, create(0F, 4F,2.5F, 0F, 90F, 55F,0.85F));
        map.put(TransformType.THIRD_PERSON_LEFT_HAND, create(0F, 4F,2.5F, 0F,-90F,-55F,0.85F));
        map.put(TransformType.FIRST_PERSON_RIGHT_HAND, create(0F,1.6F,0.8F, 0F, 90F, 25F,0.68F));
        map.put(TransformType.FIRST_PERSON_LEFT_HAND, create(0F,1.6F,0.8F, 0F,-90F,-25F,0.68F));
        DEFAULT_HANDHELD_ROD = new ModelStateImpl(map);
    }

    public static TRSRTransformation create(float tx, float ty, float tz, float rx, float ry, float rz, float s) {
        return create(new Vector3f(tx / 16, ty / 16, tz / 16), new Vector3f(rx, ry, rz), new Vector3f(s, s, s));
    }

    public static TRSRTransformation create(Vector3f transform, Vector3f rotation, Vector3f scale) {
        return TRSRTransformation.blockCenterToCorner(new TRSRTransformation(transform, TRSRTransformation.quatFromXYZDegrees(rotation), scale, null));
    }

    public static TRSRTransformation flipLeft(TRSRTransformation transform) {
        return TRSRTransformation.blockCenterToCorner(new TRSRTransformation(null, null, new Vector3f(-1, 1, 1), null).compose(TRSRTransformation.blockCornerToCenter(transform)).compose(new TRSRTransformation(null, null, new Vector3f(-1, 1, 1), null)));
    }
}
