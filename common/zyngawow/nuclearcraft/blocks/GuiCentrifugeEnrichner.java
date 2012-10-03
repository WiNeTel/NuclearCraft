package common.zyngawow.nuclearcraft.blocks;

import java.text.DecimalFormat;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Container;
import net.minecraft.src.ContainerWorkbench;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiCentrifugeEnrichner extends GuiContainer{
	World world;
	TileEntityCentrifugeEnrichner teea;
	public GuiCentrifugeEnrichner(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
		super(new ContainerCentrifugeEnrichner(par1InventoryPlayer, par2World, par3, par4, par5));
		world = par2World;
		teea = (TileEntityCentrifugeEnrichner) world.getBlockTileEntity(par3, par4, par5);
	}

	@Override
	protected void drawGuiContainerForegroundLayer()
	{
		DecimalFormat df = new DecimalFormat( "#########0.00");
		String formattedValue = df.format(teea.getEnergy());
		this.fontRenderer.drawString("Centrifuge Enrichner", 30, 5, 4210752);
		//this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		int var4 = this.mc.renderEngine.getTexture("/zyngawow/nuclearcraft/gui/centrifugeEnrichner.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		int var7 = (this.width - this.xSize) * 644 /1000;
		int var8 = (this.height - this.ySize)* 719/1000;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);		
		//this.drawTexturedModalRect(var5 + 36, var6 + 16, 0, 10, 56);
		//this.drawTexturedModalRect(var5 + 36, var6 + 16, 0, 10, 56);
		int energy;
		//System.out.println(teea.getEnergy()/2000);
		if(teea.getEnergy() == 0){
			energy = 0;
		}else if(teea.getEnergy() == teea.maxEnergy){
			energy = 58;
		}else{
			energy = (int) (teea.getEnergy()*58)/teea.maxEnergy;
		}
		this.drawTexturedModalRect(var5 + 36, var6 + 16, 176, 0, 10, 58-energy);
	}

}
