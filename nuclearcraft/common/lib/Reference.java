package nuclearcraft.common.lib;

public class Reference {
    /* Debug Mode On-Off */
    public static final boolean DEBUG_MODE = false;

    /* General Mod related constants */
    public static final String MOD_ID = "NuclearCraft";
    public static final String MOD_NAME = "NuclearCraft";
    public static final String VERSION = "1.0.0.0";
    public static final String CHANNEL_NAME = MOD_ID;
    public static final String LOGGER_PREFIX = "[" + MOD_ID + "] ";
    public static final int SECOND_IN_TICKS = 20;
    public static final int SHIFTED_ID_RANGE_CORRECTION = 256;

    /* Configuration related constants */
    public static final String ENABLE_SOUNDS = "enable_sounds";
    public static final String ENABLE_PARTICLE_FX = "enable_particle_fx";
    public static final String AUTO_RESOLVE_BLOCK_IDS = "auto_resolve_block_ids";

    /* Texture related constants */
    public static final String SPRITE_SHEET_LOCATION = "/nuclearcraft/art/sprites/";
    public static final String ARMOR_SHEET_LOCATION = "/nuclearcraft/art/armor/";
    public static final String GUI_SHEET_LOCATION = "/nuclearcraft/art/gui/";
    public static final String ITEM_SPRITE_SHEET = "nuclearcraft_items.png";
    public static final String BLOCK_SPRITE_SHEET = "nuclearcraft_blocks.png";

    /* General Tile Entity related constants */
    public static final String TE_GEN_OWNER_NBT_TAG_LABEL = "owner";
    public static final String TE_GEN_STATE_NBT_TAG_LABEL = "state";
    public static final String TE_GEN_DIRECTION_NBT_TAG_LABEL = "direction";
    
    /* KeyBindings */
    public static final String KEYBINDING_EXTRA = "mod.nuclearcraft.action_key";
    public static final int KEYBINDING_EXTRA_DEFAULT = 46;
    

}
