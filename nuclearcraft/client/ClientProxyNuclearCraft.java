package nuclearcraft.client;

import common.zyngawow.nuclearcraft.core.CommonProxyNuclearCraft;

import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxyNuclearCraft extends CommonProxyNuclearCraft{
	@Override
	public void registerRenderThings(){
		MinecraftForgeClient.preloadTexture("/gui/solarGenerator.png");
	}
}
