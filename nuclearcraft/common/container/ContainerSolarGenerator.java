package nuclearcraft.common.container;

import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.TileEntityFurnace;
import nuclearcraft.common.tile.TileSolarGenerator;

public class ContainerSolarGenerator extends Container {

	private TileSolarGenerator solarGenerator;

	public ContainerSolarGenerator(InventoryPlayer inventoryPlayer, TileSolarGenerator solarGenerator) {
		// Set the instance of the TilesolarGenerator for the container
		this.solarGenerator = solarGenerator;

		// Add the solarGenerator battery slot to the container
		this.addSlotToContainer(new Slot(solarGenerator, 0, 95, 23));

		bindPlayerInventory(inventoryPlayer);
	}
		
	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 9; j++) {
                        addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                                        8 + j * 18, 84 + i * 18));
                }
        }

        for (int i = 0; i < 9; i++) {
                addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
}

	@Override
	public boolean canInteractWith(EntityPlayer var1) {
		return true;
	}

	@Override
	public ItemStack transferStackInSlot(int slot)
	{
		ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);

        //null checks and checks if the item can be stacked (maxStackSize > 1)
        if (slotObject != null && slotObject.getHasStack()) {
                ItemStack stackInSlot = slotObject.getStack();
                stack = stackInSlot.copy();

                //merges the item into player inventory since its in the tileEntity
                if (slot == 0) {
                        if (!mergeItemStack(stackInSlot, 1,
                                        inventorySlots.size(), true)) {
                                return null;
                        }
                //places it into the tileEntity is possible since its in the player inventory
                } else if (!mergeItemStack(stackInSlot, 0, 1, false)) {
                        return null;
                }

                if (stackInSlot.stackSize == 0) {
                        slotObject.putStack(null);
                } else {
                        slotObject.onSlotChanged();
                }
        }

        return stack;
	}


}
