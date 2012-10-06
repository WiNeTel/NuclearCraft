package nuclearcraft.common.core.handlers;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import nuclearcraft.common.core.helper.LogHelper;
import nuclearcraft.common.network.*;
import nuclearcraft.common.tile.TileSolarGenerator;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(NetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		// TODO Auto-generated method stub
		PacketNC packetNC = PacketTypeHandler.buildPacket(packet.data);
		packetNC.execute(manager, player);
	}
	public static void sendStoredElectricityPacket(TileSolarGenerator sender)
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		DataOutputStream output = new DataOutputStream(bytes);

		try {
			output.writeInt(1);
			output.writeInt(sender.xCoord);
			output.writeInt(sender.yCoord);
			output.writeInt(sender.zCoord);
			output.writeInt(sender.facing);
			output.writeDouble(sender.storedEnergy);
		} catch (IOException e)
		{
			LogHelper.log(Level.SEVERE, "Unable to send stored electricity packet");
			e.printStackTrace();
		}
	}
}