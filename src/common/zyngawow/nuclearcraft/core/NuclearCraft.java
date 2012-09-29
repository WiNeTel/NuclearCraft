package zyngawow.nuclearcraft.core;

import zyngawow.nuclearcraft.blocks.ElectronAccumulator;
import zyngawow.nuclearcraft.blocks.GuiHandler;
import zyngawow.nuclearcraft.blocks.SolarGenerator;
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
	
	public static Block solarGenerator, electronAccumulator;
	public static Item graphitePickaxe, battery;

	@Instance
    public static NuclearCraft instance;
	int graphitePickaxeId, solarGeneratorId, batteryId, electronAccumulatorId;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event){
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		graphitePickaxeId = config.getItem("Graphite Pickaxe", 1200).getInt();
		batteryId = config.getItem("Battery", 1201).getInt();
		solarGeneratorId = config.getBlock("Solar generator", 256).getInt();
		electronAccumulatorId = config.getBlock("Electron Accumulator", 257).getInt();
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
		.setCreativeTab(CreativeTabs.tabTools);
		
		solarGenerator = new SolarGenerator(solarGeneratorId, Material.rock)
		.setBlockName("Solar Generator")
		.setCreativeTab(CreativeTabs.tabTools);
		
		electronAccumulator = new ElectronAccumulator(electronAccumulatorId, Material.rock)
		.setBlockName("Electron Accumulator")
		.setCreativeTab(CreativeTabs.tabTools);
		
		guiHandler = new GuiHandler();
		NetworkRegistry.instance().registerGuiHandler(instance, guiHandler);
		
		GameRegistry.registerBlock(solarGenerator);
		GameRegistry.registerBlock(electronAccumulator);
		GameRegistry.registerTileEntity(TileEntitySolarGenerator.class, "SolarGeneratorTE");
		GameRegistry.registerTileEntity(TileEntityElectronAccumulator.class, "ElectronAccumulatorTE");
		LanguageRegistry.addName(solarGenerator, "Solar Generator");
		LanguageRegistry.addName(graphitePickaxe, "Graphite Pickaxe");
		LanguageRegistry.addName(battery, "Battery");
		LanguageRegistry.addName(electronAccumulator, "Electron Accumulator");
		
		
		proxy.registerRenderThings();
	}


}
