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
		this.addSlotToContainer(new Slot(solarGenerator, 0, 56, 17));

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
		return false;
	}

	@Override
	public ItemStack transferStackInSlot(int par1)
	{
		ItemStack var2 = null;
		Slot var3 = (Slot)this.inventorySlots.get(par1);

		if (var3 != null && var3.getHasStack())
		{
			ItemStack var4 = var3.getStack();
			var2 = var4.copy();

			if (par1 == 2)
			{
				if (!this.mergeItemStack(var4, 3, 39, true))
				{
					return null;
				}

				var3.onSlotChange(var4, var2);
			}
			else if (par1 != 1 && par1 != 0)
			{
				if (FurnaceRecipes.smelting().getSmeltingResult(var4) != null)
				{
					if (!this.mergeItemStack(var4, 0, 1, false))
					{
						return null;
					}
				}
				else if (TileEntityFurnace.isItemFuel(var4))
				{
					if (!this.mergeItemStack(var4, 1, 2, false))
					{
						return null;
					}
				}
				else if (par1 >= 3 && par1 < 30)
				{
					if (!this.mergeItemStack(var4, 30, 39, false))
					{
						return null;
					}
				}
				else if (par1 >= 30 && par1 < 39 && !this.mergeItemStack(var4, 3, 30, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(var4, 3, 39, false))
			{
				return null;
			}

			if (var4.stackSize == 0)
			{
				var3.putStack((ItemStack)null);
			}
			else
			{
				var3.onSlotChanged();
			}

			if (var4.stackSize == var2.stackSize)
			{
				return null;
			}

			var3.onPickupFromSlot(var4);
		}

		return var2;
	}


}