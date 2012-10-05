package nuclearcraft.common.core.handlers;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.Configuration;
import nuclearcraft.common.NuclearCraft;
import nuclearcraft.common.block.ModBlocks;
import nuclearcraft.common.item.ModItems;
import nuclearcraft.common.lib.BlockIds;
import nuclearcraft.common.lib.ConfigurationSettings;
import nuclearcraft.common.lib.ItemIds;
import nuclearcraft.common.lib.Reference;
import static net.minecraftforge.common.Configuration.*;

public class ConfigurationHandler {

    private static final String CATEGORY_KEYBIND = "keybinds";

    public static void init(File configFile) {
        Configuration configuration = new Configuration(configFile);

        try {
            configuration.load();

            /* General Configs */
            ConfigurationSettings.ENABLE_SOUNDS = configuration
            		.get(CATEGORY_GENERAL, Reference.ENABLE_SOUNDS, ConfigurationSettings.ENABLE_SOUNDS_DEFAULT)
            		.getBoolean(ConfigurationSettings.ENABLE_SOUNDS_DEFAULT);
            ConfigurationSettings.ENABLE_PARTICLE_FX = configuration
            		.get(CATEGORY_GENERAL, Reference.ENABLE_PARTICLE_FX, ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT)
            		.getBoolean(ConfigurationSettings.ENABLE_PARTICLE_FX_DEFAULT);

            /* Block Configs */
            ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS = configuration
            		.get(CATEGORY_BLOCK, Reference.AUTO_RESOLVE_BLOCK_IDS, ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT)
            		.getBoolean(ConfigurationSettings.AUTO_RESOLVE_BLOCK_IDS_DEFAULT);
            
            BlockIds.SOLAR_GENERATOR =  configuration
            		.getBlock(ModBlocks.SOLAR_GENERATOR_NAME, BlockIds.SOLAR_GENERATOR_DEFAULT)
            		.getInt(BlockIds.SOLAR_GENERATOR_DEFAULT);
            /* Item Configs */

            /* KeyBinding Configs */
            configuration.addCustomCategoryComment(CATEGORY_KEYBIND, "");
           NuclearCraft.proxy.setKeyBinding(Reference.KEYBINDING_EXTRA, configuration
            		.get(CATEGORY_KEYBIND, Reference.KEYBINDING_EXTRA, Reference.KEYBINDING_EXTRA_DEFAULT)
            		.getInt(Reference.KEYBINDING_EXTRA_DEFAULT));
        }
        catch (Exception e) {
            FMLLog.log(Level.SEVERE, e, Reference.MOD_NAME + " has had a problem loading its configuration");
        }
        finally {
            configuration.save();
        }
    }
}
