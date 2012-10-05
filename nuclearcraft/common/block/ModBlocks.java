package nuclearcraft.common.block;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import nuclearcraft.common.lib.BlockIds;

public class ModBlocks {
    
    /* Block name constants */
    public static final String SOLAR_GENERATOR_NAME = "Solar Generator";
	
    /* Mod block instances */
	public static Block solarGenerator;

	public static void init() {
		solarGenerator = new BlockSolarGenerator(BlockIds.SOLAR_GENERATOR).setBlockName(SOLAR_GENERATOR_NAME);
		GameRegistry.registerBlock(solarGenerator);
		
		initBlockRecipes();
		
	}
	
	private static void initBlockRecipes() {
	    
	    
	    
	}

}
