package zyngawow.nuclearcraft.blocks;

import zyngawow.nuclearcraft.core.NuclearCraft;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler{
	Container container;
	GuiContainer gui;
	public void registerRenderers() {
		NetworkRegistry.instance().registerGuiHandler(NuclearCraft.instance, new GuiHandler());
	}
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID){
		case 0:
			container = new ContainerSolar(player.inventory, world, x, y, z);
			break;
		case 1:
			container = new ContainerElectronAccumulator(player.inventory, world, x, y, z);
			break;
		case 2:
			container = new ContainerCentrifugeEnrichner(player.inventory, world, x, y, z);
		}
		return container;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		switch(ID){
		case 0:
			gui = new GuiSolar(player.inventory, world, x, y, z);
			break;
		case 1:
			gui = new GuiElectronAccumulator(player.inventory, world, x, y, z);
			break;
		case 2:
			gui = new GuiCentrifugeEnrichner(player.inventory, world, x, y, z);
			break;
		}
		return gui;
	}


}
