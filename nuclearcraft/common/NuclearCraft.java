package nuclearcraft.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import net.minecraftforge.common.MinecraftForge;
import nuclearcraft.common.block.ModBlocks;
import nuclearcraft.common.core.CommonProxy;
import nuclearcraft.common.core.handlers.ConfigurationHandler;
import nuclearcraft.common.core.handlers.LocalizationHandler;
import nuclearcraft.common.core.handlers.PacketHandler;
import nuclearcraft.common.core.handlers.VersionCheckTickHandler;
import nuclearcraft.common.core.helper.VersionHelper;
import nuclearcraft.common.item.ModItems;
import nuclearcraft.common.lib.*;


@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
@NetworkMod(channels = { Reference.CHANNEL_NAME }, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
public class NuclearCraft {

	@Instance
	public static NuclearCraft instance;

	@SidedProxy(clientSide = "nuclearcraft.client.core.ClientProxy", serverSide = "nuclearcraft.common.core.CommonProxy")
	public static CommonProxy proxy;

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {

		// Load the localization files into the LanguageRegistry
		LocalizationHandler.loadLanguages();

		// Initialize the configuration
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());

		// Conduct the version check and log the result
		VersionHelper.checkVersion();
		VersionHelper.logResult();

		// Initialize the Version Check Tick Handler (Client only)
		TickRegistry.registerTickHandler(new VersionCheckTickHandler(), Side.CLIENT);

		// Register the KeyBinding Handler (Client only)
		proxy.registerKeyBindingHandler();

		// Register the Sound Handler (Client only)
		proxy.registerSoundHandler();

	}

	@Init
	public void load(FMLInitializationEvent event) {

		// Initialize the custom item rarity types
		proxy.initCustomRarityTypes();

		// Register the GUI Handler
		NetworkRegistry.instance().registerGuiHandler(instance, proxy);

		// Initialize mod blocks
		ModBlocks.init();

		// Initialize mod items
		ModItems.init();

		// Initialize mod tile entities
		proxy.initTileEntities();

		// Initialize custom rendering and pre-load textures (Client only)
		proxy.initRenderingAndTextures();



	}
}
