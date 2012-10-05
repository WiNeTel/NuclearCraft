package nuclearcraft.common.core.handlers;

import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet250CustomPayload;
import nuclearcraft.common.network.*;
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

}
