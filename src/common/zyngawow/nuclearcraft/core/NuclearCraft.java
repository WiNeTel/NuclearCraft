package zyngawow.nuclearcraft.core;

import zyngawow.nuclearcraft.blocks.CentrifugeEnrichner;
import zyngawow.nuclearcraft.blocks.ElectronAccumulator;
import zyngawow.nuclearcraft.blocks.GuiHandler;
import zyngawow.nuclearcraft.blocks.SolarGenerator;
import zyngawow.nuclearcraft.blocks.TileEntityCentrifugeEnrichner;
import zyngawow.nuclearcraft.blocks.TileEntityElectronAccumulator;
import zyngawow.nuclearcraft.blocks.TileEntitySolarGenerator;
import zyngawow.nuclearcraft.items.Battery;
import zyngawow.nuclearcraft.items.Pickaxe;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.*;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.*;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraftforge.common.*;

@Mod(modid = "zyngawow_nuclearCraft", name = "NuclearCraft", version = "0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class NuclearCraft{
	@SidedProxy(clientSide = "nuclearcraft.client.ClientProxyNuclearCraft", serverSide = "core.nuclearcraft.common.CommonProxyNuclearCraft")
	public static CommonProxyNuclearCraft proxy;

	public GuiHandler guiHandler;
	
	public static Block solarGenerator, electronAccumulator, centrifugeEnrichner;
	public static Item graphitePickaxe, battery;

	@Instance
    public static NuclearCraft instance;
	int graphitePickaxeId, solarGeneratorId, batteryId, electronAccumulatorId
	,centrifugeEnrichnerId;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		graphitePickaxeId = config.getItem("Graphite Pickaxe", 1200).getInt();
		batteryId = config.getItem("Battery", 4201).getInt();
		solarGeneratorId = config.getBlock("Solar generator", 2243).getInt();
		electronAccumulatorId = config.getBlock("Electron Accumulator", 2244).getInt();
		centrifugeEnrichnerId = config.getBlock("Centrifuge Enrichner", 2245).getInt();
		config.save();
	}
	@Init
	public void load(FMLInitializationEvent event){
		graphitePickaxe = new Pickaxe(graphitePickaxeId, 2, EnumToolMaterial.IRON)
		.setCreativeTab(CreativeTabs.tabTools)
		.setItemName("Graphite Pickaxe")
		.setMaxDamage(1000)
		.setIconCoord(0, 0);
		
		battery = new Battery(batteryId, 1200)
		.setItemName("Battery")
		.setMaxStackSize(1)
		.setMaxStackSize(1)
		.setCreativeTab(CreativeTabs.tabTools);
		
		solarGenerator = new SolarGenerator(solarGeneratorId, Material.rock)
		.setBlockName("Solar Generator")
		.setCreativeTab(CreativeTabs.tabTools);
		
		electronAccumulator = new ElectronAccumulator(electronAccumulatorId, Material.rock)
		.setBlockName("Electron Accumulator")
		.setCreativeTab(CreativeTabs.tabTools);
		
		centrifugeEnrichner = new CentrifugeEnrichner(centrifugeEnrichnerId, Material.rock)
		.setBlockName("Centrifuge Enrichner")
		.setCreativeTab(CreativeTabs.tabTools);
		
		guiHandler = new GuiHandler();
		NetworkRegistry.instance().registerGuiHandler(instance, guiHandler);
		
		GameRegistry.registerBlock(solarGenerator);
		GameRegistry.registerBlock(electronAccumulator);
		GameRegistry.registerBlock(centrifugeEnrichner);
		GameRegistry.registerTileEntity(TileEntitySolarGenerator.class, "SolarGeneratorTE");
		GameRegistry.registerTileEntity(TileEntityElectronAccumulator.class, "ElectronAccumulatorTE");
		GameRegistry.registerTileEntity(TileEntityCentrifugeEnrichner.class, "CentrifugeEnrichnerTE");
		LanguageRegistry.addName(solarGenerator, "Solar Generator");
		LanguageRegistry.addName(graphitePickaxe, "Graphite Pickaxe");
		LanguageRegistry.addName(battery, "Battery");
		LanguageRegistry.addName(electronAccumulator, "Electron Accumulator");
		LanguageRegistry.addName(centrifugeEnrichner, "Centrifuge Enrichner");
		
		
		proxy.registerRenderThings();
	}


}
