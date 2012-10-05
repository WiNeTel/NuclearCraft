package nuclearcraft.client.core;

import net.minecraft.src.EnumRarity;
import net.minecraftforge.client.EnumHelperClient;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import nuclearcraft.client.core.handlers.KeyBindingHandler;
import nuclearcraft.client.lib.KeyBindings;
import nuclearcraft.common.core.CommonProxy;
import nuclearcraft.common.lib.Reference;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerKeyBindingHandler() {
	KeyBindingRegistry.registerKeyBinding(new KeyBindingHandler());
    }

    @Override
    public void setKeyBinding(String name, int value) {
	KeyBindings.addKeyBinding(name, value);
	KeyBindings.addIsRepeating(false);
    }

    @Override
    public void registerSoundHandler() {
	MinecraftForge.EVENT_BUS.register(new SoundHandler());
    }

    @Override
    public void initCustomRarityTypes() {
    }

    @Override
    public EnumRarity getCustomRarityType(String customRarity) {
	for (EnumRarity rarity : EnumRarity.class.getEnumConstants()) {
	    if (rarity.name().equals(customRarity))
		return rarity;
	}
	return EnumRarity.common;
    }

    @Override
    public void initRenderingAndTextures() {    	
	MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.BLOCK_SPRITE_SHEET);
	MinecraftForgeClient.preloadTexture(Reference.SPRITE_SHEET_LOCATION + Reference.ITEM_SPRITE_SHEET);
    }

    @Override
    public void initTileEntities() {
	super.initTileEntities();
    }
}
