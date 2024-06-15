package ru.hollowhorizon.mastertech.api.model

import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType
import net.minecraftforge.common.model.IModelState
import net.minecraftforge.common.model.TRSRTransformation
import java.util.*
import javax.vecmath.Vector3f
import kotlin.collections.HashMap

object Transforms {
    @JvmField
    val DEFAULT_BLOCK: IModelState
    @JvmField
    val DEFAULT_ITEM: IModelState
    @JvmField
    val DEFAULT_TOOL: IModelState
    @JvmField
    val DEFAULT_BOW: IModelState
    @JvmField
    val DEFAULT_HANDHELD_ROD: IModelState

    init {
        var map: MutableMap<TransformType, TRSRTransformation> = EnumMap(TransformType::class.java)
        var thirdPerson = create(0f, 2.5f, 0f, 75f, 45f, 0f, 0.375f)
        map[TransformType.GUI] = create(0f, 0f, 0f, 30f, 225f, 0f, 0.625f)
        map[TransformType.GROUND] = create(0f, 3f, 0f, 0f, 0f, 0f, 0.25f)
        map[TransformType.FIXED] = create(0f, 0f, 0f, 0f, 0f, 0f, 0.5f)
        map[TransformType.THIRD_PERSON_RIGHT_HAND] = thirdPerson
        map[TransformType.THIRD_PERSON_LEFT_HAND] = flipLeft(thirdPerson)
        map[TransformType.FIRST_PERSON_RIGHT_HAND] = create(0f, 0f, 0f, 0f, 45f, 0f, 0.4f)
        map[TransformType.FIRST_PERSON_LEFT_HAND] = create(0f, 0f, 0f, 0f, 225f, 0f, 0.4f)
        DEFAULT_BLOCK = ModelStateImpl(map)

        map = EnumMap(TransformType::class.java)
        thirdPerson = create(0f, 3f, 1f, 0f, 0f, 0f, 0.55f)
        val firstPerson =
            create(1.13f, 3.2f, 1.13f, 0f, -90f, 25f, 0.68f)
        map[TransformType.GROUND] = create(0f, 2f, 0f, 0f, 0f, 0f, 0.5f)
        map[TransformType.HEAD] = create(0f, 13f, 7f, 0f, 180f, 0f, 1f)
        map[TransformType.THIRD_PERSON_RIGHT_HAND] = thirdPerson
        map[TransformType.THIRD_PERSON_LEFT_HAND] = flipLeft(thirdPerson)
        map[TransformType.FIRST_PERSON_RIGHT_HAND] = firstPerson
        map[TransformType.FIRST_PERSON_LEFT_HAND] = flipLeft(firstPerson)
        DEFAULT_ITEM = ModelStateImpl(map)

        map = EnumMap(TransformType::class.java)
        map[TransformType.GROUND] = create(0f, 2f, 0f, 0f, 0f, 0f, 0.5f)
        map[TransformType.THIRD_PERSON_RIGHT_HAND] =
            create(0f, 4f, 0.5f, 0f, -90f, 55f, 0.85f)
        map[TransformType.THIRD_PERSON_LEFT_HAND] = create(0f, 4f, 0.5f, 0f, 90f, -55f, 0.85f)
        map[TransformType.FIRST_PERSON_RIGHT_HAND] = create(1.13f, 3.2f, 1.13f, 0f, -90f, 25f, 0.68f)
        map[TransformType.FIRST_PERSON_LEFT_HAND] = create(1.13f, 3.2f, 1.13f, 0f, 90f, -25f, 0.68f)
        DEFAULT_TOOL = ModelStateImpl(map)

        map = EnumMap(TransformType::class.java)
        map[TransformType.GROUND] = create(0f, 2f, 0f, 0f, 0f, 0f, 0.5f)
        map[TransformType.THIRD_PERSON_RIGHT_HAND] = create(-1f, -2f, 2.5f, -80f, 260f, -40f, 0.9f)
        map[TransformType.THIRD_PERSON_LEFT_HAND] =
            create(-1f, -2f, 2.5f, -80f, -280f, 40f, 0.9f)
        map[TransformType.FIRST_PERSON_RIGHT_HAND] =
            create(1.13f, 3.2f, 1.13f, 0f, -90f, 25f, 0.68f)
        map[TransformType.FIRST_PERSON_LEFT_HAND] = create(1.13f, 3.2f, 1.13f, 0f, 90f, -25f, 0.68f)
        DEFAULT_BOW = ModelStateImpl(map)

        map = EnumMap(TransformType::class.java)
        map[TransformType.GROUND] = create(0f, 2f, 0f, 0f, 0f, 0f, 0.5f)
        map[TransformType.THIRD_PERSON_RIGHT_HAND] =
            create(0f, 4f, 2.5f, 0f, 90f, 55f, 0.85f)
        map[TransformType.THIRD_PERSON_LEFT_HAND] =
            create(0f, 4f, 2.5f, 0f, -90f, -55f, 0.85f)
        map[TransformType.FIRST_PERSON_RIGHT_HAND] = create(0f, 1.6f, 0.8f, 0f, 90f, 25f, 0.68f)
        map[TransformType.FIRST_PERSON_LEFT_HAND] = create(0f, 1.6f, 0.8f, 0f, -90f, -25f, 0.68f)
        DEFAULT_HANDHELD_ROD = ModelStateImpl(map)
    }

    @JvmStatic
    fun create(tx: Float, ty: Float, tz: Float, rx: Float, ry: Float, rz: Float, s: Float): TRSRTransformation {
        return create(Vector3f(tx / 16, ty / 16, tz / 16), Vector3f(rx, ry, rz), Vector3f(s, s, s))
    }

    @JvmStatic
    fun create(transform: Vector3f?, rotation: Vector3f?, scale: Vector3f?): TRSRTransformation {
        return TRSRTransformation.blockCenterToCorner(
            TRSRTransformation(
                transform,
                TRSRTransformation.quatFromXYZDegrees(rotation),
                scale,
                null
            )
        )
    }

    @JvmStatic
    fun flipLeft(transform: TRSRTransformation?): TRSRTransformation {
        return TRSRTransformation.blockCenterToCorner(
            TRSRTransformation(
                null,
                null,
                Vector3f(-1f, 1f, 1f),
                null
            ).compose(TRSRTransformation.blockCornerToCenter(transform))
                .compose(TRSRTransformation(null, null, Vector3f(-1f, 1f, 1f), null))
        )
    }
}
