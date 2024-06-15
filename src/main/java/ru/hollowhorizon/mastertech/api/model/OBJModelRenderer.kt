package ru.hollowhorizon.mastertech.api.model

import net.minecraft.client.Minecraft
import net.minecraft.client.model.ModelBase
import net.minecraft.client.model.ModelRenderer
import net.minecraft.client.renderer.GLAllocation
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.block.model.BakedQuad
import net.minecraft.client.renderer.block.model.IBakedModel
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.ResourceLocation
import net.minecraftforge.client.model.obj.OBJLoader
import org.lwjgl.opengl.GL11
import ru.hollowhorizon.mastertech.MasterTech

class OBJModelRenderer(model: ModelBase, private val customModel: ResourceLocation, texture: ResourceLocation) :
    ModelRenderer(model) {
    var texture: ResourceLocation
    private var displayList = 0
    private var compiled = false
    private var objModel: IBakedModel? = null
    var scale: Float = 0f

    init {
        try {
            this.objModel = OBJLoader.INSTANCE.loadModel(customModel)
                .bake(
                    Transforms.DEFAULT_TOOL,
                    DefaultVertexFormats.ITEM
                ) { rl: ResourceLocation -> this.getSprite(rl) }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        this.texture = texture
    }

    override fun render(scale: Float) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled) this.compileDisplayList(scale)
        }
    }

    private fun compileDisplayList(scale: Float) {
        var s = scale
        if (this.scale == 0f) {
            this.scale = s
        }

        if (objModel == null) {
            compiled = true
            MasterTech.LOGGER.error("Failed to compile model. Because model (rl: {}) is null", this.customModel)
            return
        }

        s = this.scale
        this.displayList = GLAllocation.generateDisplayLists(1)
        GlStateManager.glNewList(this.displayList, GL11.GL_COMPILE)

        GlStateManager.pushMatrix()
        GlStateManager.scale(s, s, s)
        GlStateManager.rotate(180f, -1f, 0f, 1f)

        GlStateManager.bindTexture(Minecraft.getMinecraft().textureMapBlocks.glTextureId)
        this.renderQuads(objModel!!.getQuads(null, null, 0))

        GlStateManager.popMatrix()

        GlStateManager.glEndList()
        this.compiled = true
    }

    private fun renderQuads(listQuads: List<BakedQuad>) {
        val tessellator = Tessellator.getInstance()
        val vertexbuffer = tessellator.buffer
        var i = 0
        vertexbuffer.begin(7, DefaultVertexFormats.ITEM)
        val j = listQuads.size
        while (i < j) {
            val bakedquad = listQuads[i]

            vertexbuffer.addVertexData(bakedquad.vertexData)

            vertexbuffer.putColorRGB_F4(1f, 1f, 1f)

            val vec3i = bakedquad.face.directionVec
            vertexbuffer.putNormal(vec3i.x.toFloat(), vec3i.y.toFloat(), vec3i.z.toFloat())

            ++i
        }
        tessellator.draw()
    }


    private fun getSprite(rl: ResourceLocation): TextureAtlasSprite {
        return Minecraft.getMinecraft().textureMapBlocks.getAtlasSprite(rl.toString())
    }
}
