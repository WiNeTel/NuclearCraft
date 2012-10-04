package common.zyngawow.nuclearcraft.blocks;

import common.zyngawow.nuclearcraft.core.NuclearCraft;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler{
    public void registerRenderers() {
	NetworkRegistry.instance().registerGuiHandler(NuclearCraft.instance, new GuiHandler());
    }
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
	    int x, int y, int z) {
	System.out.println(ID);
	switch(ID){
	case 0:
	    return new ContainerSolar(player.inventory, world, x, y, z);
	case 1:
	    return new ContainerElectronAccumulator(player.inventory, world, x, y, z);
	case 2:
	    System.out.println("2");
	    return new ContainerCentrifugeEnrichner(player.inventory, world, x, y, z);
	}
	return null;
    }
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
	    int x, int y, int z) {

	System.out.println(ID);
	switch(ID){
	case 0:
	    return new GuiSolar(player.inventory, world, x, y, z);
	case 1:
	    return new GuiElectronAccumulator(player.inventory, world, x, y, z);
	case 2:
	    return new GuiCentrifugeEnrichner(player.inventory, world, x, y, z);
	}
	return null;
    }


}
