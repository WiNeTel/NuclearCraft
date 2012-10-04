package nuclearcraft.client;

import common.zyngawow.nuclearcraft.blocks.GuiHandler;
import common.zyngawow.nuclearcraft.core.CommonProxyNuclearCraft;

import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxyNuclearCraft extends CommonProxyNuclearCraft{
	@Override
	public void registerRenderThings(){
		MinecraftForgeClient.preloadTexture("/gui/solarGenerator.png");
		MinecraftForgeClient.preloadTexture("/gui/centrifugeEnrichner.png");
	}
}
