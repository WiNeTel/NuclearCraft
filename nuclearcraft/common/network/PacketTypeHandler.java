package nuclearcraft.common.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;
import nuclearcraft.common.lib.Reference;

public enum PacketTypeHandler {
	KEY(PacketKeyPressed.class),
	TILE(PacketTileUpdate.class);
	
	private Class<? extends PacketNC> clazz;
	
	PacketTypeHandler(Class<? extends PacketNC> clazz) {
		this.clazz = clazz;
	}
	
	public static PacketNC buildPacket(byte[] data) {
		ByteArrayInputStream bis = new ByteArrayInputStream(data);
		int selector = bis.read();
		DataInputStream dis = new DataInputStream(bis);
		
		PacketNC packet = null;
		
		try {
			packet = values()[selector].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		packet.readPopulate(dis);
		
		return packet;
	}
	
	public static PacketNC buildPacket(PacketTypeHandler type) {
		PacketNC packet = null;
		
		try {
			packet = values()[type.ordinal()].clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
		
		return packet;
	}
	
	public static Packet populatePacket(PacketNC PacketNC) {
		byte[] data = PacketNC.populate();
		
		Packet250CustomPayload packet250 = new Packet250CustomPayload();
		packet250.channel = Reference.CHANNEL_NAME;
		packet250.data = data;
		packet250.length = data.length;
		packet250.isChunkDataPacket = PacketNC.isChunkDataPacket;
		
		return packet250;
	}
}
