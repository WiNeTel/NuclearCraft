package nuclearcraft.common.core.helper;

import java.util.logging.Level;

import nuclearcraft.common.lib.Reference;

import cpw.mods.fml.common.FMLCommonHandler;

public class LogHelper {

	public static void log(Level logLevel, String message) {
		System.out.println(Reference.LOGGER_PREFIX + message);
		FMLCommonHandler.instance().getFMLLogger().log(logLevel, Reference.LOGGER_PREFIX + message);
	}
	
}
