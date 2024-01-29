package ru.hollowhorizon.mastertech.api.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3i;
import net.minecraftforge.client.model.obj.OBJLoader;
import org.lwjgl.opengl.GL11;
import ru.hollowhorizon.mastertech.MasterTech;

import java.util.List;

public class OBJModelRenderer extends ModelRenderer {
    private ResourceLocation customModel;
    public ResourceLocation texture;
    private int displayList;
    private boolean compiled = false;
    private IBakedModel objModel;
    public float scale = 0;

    public OBJModelRenderer(ModelBase p_i1172_1_, ResourceLocation customModel, ResourceLocation texture) {
        super(p_i1172_1_);
        this.customModel = customModel;
        try {
            this.objModel = OBJLoader.INSTANCE.loadModel(customModel)
                    .bake(
                            Transforms.DEFAULT_TOOL,
                            DefaultVertexFormats.ITEM,
                            this::getSprite
                    );
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.texture = texture;
    }

    @Override
    public void render(float p_78785_1_) {
        if (!this.isHidden && this.showModel) {
            if (!this.compiled)
                this.compileDisplayList(p_78785_1_);

        }
    }

    private void compileDisplayList(float scale) {
        if (this.scale == 0) {
            this.scale = scale;
        }

        if (objModel == null) {
            compiled = true;
            MasterTech.LOGGER.error("Failed to compile model. Because model (rl: {}) is null", this.customModel);
            return;
        }

        scale = this.scale;
        this.displayList = GLAllocation.generateDisplayLists(1);
        GlStateManager.glNewList(this.displayList, GL11.GL_COMPILE);

        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.rotate(180, -1, 0, 1);

        GlStateManager.bindTexture(Minecraft.getMinecraft().getTextureMapBlocks().getGlTextureId());
        this.renderQuads(objModel.getQuads(null, null, 0));

        GlStateManager.popMatrix();

        GlStateManager.glEndList();
        this.compiled = true;
    }

    private void renderQuads(List<BakedQuad> listQuads) {
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        int i = 0;
        vertexbuffer.begin(7, DefaultVertexFormats.ITEM);
        for (int j = listQuads.size(); i < j; ++i) {
            BakedQuad bakedquad = listQuads.get(i);

            vertexbuffer.addVertexData(bakedquad.getVertexData());

            vertexbuffer.putColorRGB_F4(1, 1, 1);

            Vec3i vec3i = bakedquad.getFace().getDirectionVec();
            vertexbuffer.putNormal((float) vec3i.getX(), (float) vec3i.getY(), (float) vec3i.getZ());

        }
        tessellator.draw();
    }


    private TextureAtlasSprite getSprite(ResourceLocation rl) {
        return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(rl.toString());
    }
}
