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
public class GuiSolar extends GuiContainer{
    TileEntitySolarGenerator tesg;
    World world;
    int x, y, z;
    public GuiSolar(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5) {
	super(new ContainerSolar(par1InventoryPlayer, par2World, par3, par4, par5));
	x = par3;
	y = par4;
	z = par5;
	world = par2World;
	tesg = (TileEntitySolarGenerator) world.getBlockTileEntity(x, y, z);
    }

    @Override
    protected void drawGuiContainerForegroundLayer()
    {
	DecimalFormat df = new DecimalFormat( "#########0.00");
	String formattedValue = df.format(tesg.getEnergy());
	this.fontRenderer.drawString("Stored " + formattedValue, 52, 47, 4210752);
	this.fontRenderer.drawString("Solar Generator", 48, 7, 4210752);	
	this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2,
	    int var3) {
	int var4 = this.mc.renderEngine.getTexture("/gui/solarGenerator.png");
	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	this.mc.renderEngine.bindTexture(var4);
	int var5 = (this.width - this.xSize) / 2;
	int var6 = (this.height - this.ySize) / 2;
	this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	if(tesg.providesEnery()){
	    System.out.println("Providing energy");
	    this.drawTexturedModalRect(var5 + 64, var6 + 20, 176, 0, 22, 22);
	}
    }

}
