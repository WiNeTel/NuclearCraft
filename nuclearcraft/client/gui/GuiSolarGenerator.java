/**
 * 
 */
package nuclearcraft.client.gui;

import java.util.logging.Level;

import org.lwjgl.opengl.GL11;

import net.minecraft.src.Container;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;
import nuclearcraft.common.container.ContainerSolarGenerator;
import nuclearcraft.common.core.helper.LogHelper;
import nuclearcraft.common.lib.Reference;
import nuclearcraft.common.tile.TileSolarGenerator;

/**
 * @author Adrian
 *
 */
public class GuiSolarGenerator extends GuiContainer {

	private TileSolarGenerator solarGenerator;
	
	 public GuiSolarGenerator(InventoryPlayer player, TileSolarGenerator solarGenerator) {
	        super(new ContainerSolarGenerator(player, solarGenerator));
	        this.solarGenerator = solarGenerator;
	        LogHelper.log(Level.SEVERE, "Created GUI");
	    }
	@Override
	protected void drawGuiContainerForegroundLayer()
	{
		if(solarGenerator.solarGeneratorItemStacks[0] != null){
			System.out.println(solarGenerator.solarGeneratorItemStacks[0]);
		}
		this.fontRenderer.drawString("Solar Generator", 60, 6, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		int var4 = this.mc.renderEngine.getTexture(Reference.GUI_SHEET_LOCATION + "solarGenerator.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);

	}

}
