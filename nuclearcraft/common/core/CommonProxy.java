package nuclearcraft.common.core;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.World;
import nuclearcraft.client.gui.GuiSolarGenerator;
import nuclearcraft.common.container.ContainerSolarGenerator;
import nuclearcraft.common.lib.GuiIds;
import nuclearcraft.common.tile.TileSolarGenerator;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;


public class CommonProxy implements IGuiHandler {

	public void registerKeyBindingHandler() {}

	public void setKeyBinding(String name, int value) {}

	public void registerSoundHandler() {}

	public void initCustomRarityTypes() {}

	public EnumRarity getCustomRarityType(String customRarity) {
		return null;
	}

	public void initRenderingAndTextures() {}

	public void initTileEntities() {
		// TODO: Constant
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GuiIds.SOLAR_GENERATOR){
			TileSolarGenerator solarGenerator = (TileSolarGenerator) world.getBlockTileEntity(x, y, z);
			return new ContainerSolarGenerator(player.inventory, solarGenerator);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == GuiIds.SOLAR_GENERATOR){
			TileSolarGenerator solarGenerator = (TileSolarGenerator) world.getBlockTileEntity(x, y, z);
			return new GuiSolarGenerator(player.inventory, solarGenerator);
		}
		return null;
	}

}
