package nuclearcraft.common.tile;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

import com.google.common.io.ByteArrayDataInput;

import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.NetworkManager;
import net.minecraft.src.Packet;
import net.minecraft.src.Packet250CustomPayload;
import nuclearcraft.common.block.ModBlocks;
import nuclearcraft.common.core.handlers.PacketHandler;
import nuclearcraft.common.core.helper.LogHelper;
import nuclearcraft.common.lib.Reference;

public class TileSolarGenerator extends TileNC implements IInventory{
	public ItemStack[] solarGeneratorItemStacks = new ItemStack[1];
	public double storedEnergy;
	public int facing;
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		this.storedEnergy = nbtTagCompound.getDouble("electricityStored");
		// Read in the ItemStacks in the inventory from NBT
		NBTTagList tagList = nbtTagCompound.getTagList("Items");
		this.solarGeneratorItemStacks = new ItemStack[this.getSizeInventory()];
		for (int i = 0; i < tagList.tagCount(); ++i) {
			NBTTagCompound tagCompound = (NBTTagCompound)tagList.tagAt(i);
			byte slot = tagCompound.getByte("Slot");
			if (slot >= 0 && slot < this.solarGeneratorItemStacks.length) {
				this.solarGeneratorItemStacks[slot] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}

	}

	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setDouble("electricityStored", this.storedEnergy);
		// Write the ItemStacks in the inventory to NBT
		NBTTagList tagList = new NBTTagList();
		for (int currentIndex = 0; currentIndex < this.solarGeneratorItemStacks.length; ++currentIndex) {
			if (this.solarGeneratorItemStacks[currentIndex] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte)currentIndex);
				this.solarGeneratorItemStacks[currentIndex].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}
		nbtTagCompound.setTag("Items", tagList);

	}
	
	public Packet getDescriptionPacket()
	{
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(bytes);
        
        try {
        	output.writeInt(1);
        	output.writeInt(xCoord);
        	output.writeInt(yCoord);
        	output.writeInt(zCoord);
        	output.writeInt(facing);
        	output.writeDouble(storedEnergy);
        } catch (IOException e)
        {
        	LogHelper.log(Level.SEVERE, "Unable to send stored electricity packet");
        	e.printStackTrace();
        }
        
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = Reference.CHANNEL_NAME;
        packet.data = bytes.toByteArray();
        packet.length = packet.data.length;
        
        return packet;
	}
	
	public void handlePacketData(NetworkManager network, Packet250CustomPayload packet, EntityPlayer player, ByteArrayDataInput dataStream) 
	{
		try {
			facing = dataStream.readInt();
			storedEnergy = dataStream.readDouble();
			worldObj.markBlockAsNeedsUpdate(xCoord, yCoord, zCoord);
		} catch (Exception e)
		{
			LogHelper.log(Level.SEVERE, "Error handling electricity packet");
			e.printStackTrace();
		}
	}


	public void updateEntity() {
		if(this.storedEnergy<1000){
			this.storedEnergy++;
		}
		if(!worldObj.isRemote){
			PacketHandler.sendStoredElectricityPacket(this);
		}
		System.out.println(storedEnergy);

	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory() {
		return this.solarGeneratorItemStacks.length;
	}

	/**
	 * Returns the stack in slot i
	 */
	public ItemStack getStackInSlot(int i) {
		return this.solarGeneratorItemStacks[i];
	}

	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			if (stack.stackSize <= amt) {
				setInventorySlotContents(slot, null);
			} else {
				stack = stack.splitStack(amt);
				if (stack.stackSize == 0) {
					setInventorySlotContents(slot, null);
				}
			}
		}
		return stack;
	}

	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
		if (stack != null) {
			setInventorySlotContents(slot, null);
		}
		return stack;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		solarGeneratorItemStacks[var1] = var2;
		if (var2 != null && var2.stackSize > getInventoryStackLimit()) {
			var2.stackSize = getInventoryStackLimit();
		}     

	}

	public String getInvName() {
		return "container." + ModBlocks.SOLAR_GENERATOR_NAME;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void openChest() { 
	}
	public void closeChest() { }

}
