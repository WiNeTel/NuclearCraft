package nuclearcraft.common.network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import cpw.mods.fml.common.network.Player;
import nuclearcraft.common.*;
import nuclearcraft.common.lib.*;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;

public class PacketKeyPressed extends PacketNC {

	public String key;
	
	public PacketKeyPressed() {
		super(PacketTypeHandler.KEY, false);
	}
	
	public PacketKeyPressed(String key) {
		super(PacketTypeHandler.KEY, false);
		this.key = key;
	}

	@Override
	public void writeData(DataOutputStream data) throws IOException {
		data.writeUTF(key);
	}
	
	public void readData(DataInputStream data) throws IOException {
		this.key = data.readUTF();
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public void execute(NetworkManager manager, Player player) {
		EntityPlayer thePlayer = (EntityPlayer) player;

		if ((this.key.equals(Reference.KEYBINDING_EXTRA)) && (thePlayer.getCurrentEquippedItem().getItem().shiftedIndex == ItemIds.MINIUM_STONE)) {
			thePlayer.openGui(NuclearCraft.instance, GuiIds.SOLAR_GENERATOR, thePlayer.worldObj, (int)thePlayer.posX, (int)thePlayer.posY, (int)thePlayer.posZ);
		}
	}
}
