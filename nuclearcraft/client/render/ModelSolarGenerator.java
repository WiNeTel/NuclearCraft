package nuclearcraft.client.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Entity;
import net.minecraft.src.ModelBase;
import net.minecraft.src.ModelRenderer;
import net.minecraftforge.client.ForgeHooksClient;
import nuclearcraft.common.lib.Reference;
import nuclearcraft.common.tile.TileSolarGenerator;

public class ModelSolarGenerator extends ModelBase{
	//fields
	ModelRenderer Base;
	ModelRenderer RightBar;
	ModelRenderer LeftBar;
	ModelRenderer RightSupport;
	ModelRenderer LeftSupport;
	ModelRenderer Pannel;

	public ModelSolarGenerator()
	{
		textureWidth = 64;
		textureHeight = 32;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(0F, 0F, 0F, 16, 2, 16);
		Base.setRotationPoint(-8F, 22F, -8F);
		Base.setTextureSize(64, 32);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		RightBar = new ModelRenderer(this, 0, 0);
		RightBar.addBox(0F, 0F, 0F, 1, 2, 16);
		RightBar.setRotationPoint(7F, 20F, -8F);
		RightBar.setTextureSize(64, 32);
		RightBar.mirror = true;
		setRotation(RightBar, 0F, 0F, 0F);
		LeftBar = new ModelRenderer(this, 0, 0);
		LeftBar.addBox(0F, 0F, 0F, 1, 2, 16);
		LeftBar.setRotationPoint(-8F, 20F, -8F);
		LeftBar.setTextureSize(64, 32);
		LeftBar.mirror = true;
		setRotation(LeftBar, 0F, 0F, 0F);
		RightSupport = new ModelRenderer(this, 0, 0);
		RightSupport.addBox(0F, 0F, 0F, 1, 1, 16);
		RightSupport.setRotationPoint(6F, 20F, -8F);
		RightSupport.setTextureSize(64, 32);
		RightSupport.mirror = true;
		setRotation(RightSupport, 0F, 0F, 0F);
		LeftSupport = new ModelRenderer(this, 0, 0);
		LeftSupport.addBox(0F, 0F, 0F, 1, 1, 16);
		LeftSupport.setRotationPoint(-7F, 20F, -8F);
		LeftSupport.setTextureSize(64, 32);
		LeftSupport.mirror = true;
		setRotation(LeftSupport, 0F, 0F, 0F);
		Pannel.mirror = true;
		Pannel = new ModelRenderer(this, 0, 0);
		Pannel.addBox(0F, 0F, 0F, 14, 1, 16);
		Pannel.setRotationPoint(-7F, 20.9F, -8F);
		Pannel.setTextureSize(64, 32);
		Pannel.mirror = true;
		setRotation(Pannel, 0F, 0F, 0F);
		Pannel.mirror = false;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		Base.render(f5);
		RightBar.render(f5);
		LeftBar.render(f5);
		RightSupport.render(f5);
		LeftSupport.render(f5);
		Pannel.render(f5);
	}
	public void render(TileSolarGenerator SolarGenerator, double x, double y, double z) {
		GL11.glPushMatrix();
		GL11.glDisable(GL11.GL_LIGHTING);

		GL11.glTranslated(x, y, z);
		ForgeHooksClient.bindTexture(Reference.SPRITE_SHEET_LOCATION + "SolarGenerator.png", 0);


		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glPopMatrix();
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5);
	}

}
