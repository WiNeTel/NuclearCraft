package nuclearcraft.common.tile;

import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import nuclearcraft.common.block.ModBlocks;

public class TileSolarGenerator extends TileNC implements IInventory{
	private ItemStack[] solarGeneratorItemStacks = new ItemStack[1];

	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);

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

	public ItemStack decrStackSize(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		// TODO Auto-generated method stub

	}

	public String getInvName() {
		return "container." + ModBlocks.SOLAR_GENERATOR_NAME;
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void openChest() { }
	public void closeChest() { }

}
