package nuclearcraft.common.core.handlers;

import nuclearcraft.client.core.helper.LocalizationHelper;
import nuclearcraft.common.lib.Localizations;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class LocalizationHandler {
	
	public static void loadLanguages() {
		// For every file specified in the Localization library class, load them into the Language Registry
		for (String localizationFile : Localizations.localeFiles) {
			LanguageRegistry.instance().loadLocalization(localizationFile, LocalizationHelper.getLocaleFromFileName(localizationFile), LocalizationHelper.isXMLLanguageFile(localizationFile));
		}
	}
	
}
