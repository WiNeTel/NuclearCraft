package nuclearcraft.client.core;

import java.io.File;
import java.util.logging.Level;
import cpw.mods.fml.common.FMLCommonHandler;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;
import nuclearcraft.common.core.helper.LogHelper;
import nuclearcraft.common.lib.Sounds;

public class SoundHandler {

    @ForgeSubscribe
    public void onSoundLoad(SoundLoadEvent event) {

        // For each custom sound file we have defined in Sounds
        for (String soundFile : Sounds.soundFiles) {
            // Try to add the custom sound file to the pool of sounds
            try {
                event.manager.addSound(soundFile, new File(this.getClass().getResource("/" + soundFile).toURI()));
            }
            // If we cannot add the custom sound file to the pool, log the
            // exception
            catch (Exception e) {
                LogHelper.log(Level.WARNING, "Failed loading sound file: " + soundFile);
            }
        }

    }

}