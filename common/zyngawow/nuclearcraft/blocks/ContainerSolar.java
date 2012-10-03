package common.zyngawow.nuclearcraft.blocks;

import common.zyngawow.nuclearcraft.items.EnergyProvider;
import common.zyngawow.nuclearcraft.items.EnergyUser;

import net.minecraft.src.Block;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.InventoryCraftResult;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Slot;
import net.minecraft.src.SlotCrafting;
import net.minecraft.src.World;

public class ContainerSolar extends Container{
	private int posX;
	private int posY;
	private int posZ;
	private World worldObj;
	public IInventory batterySlot;
	public ContainerSolar(InventoryPlayer par1InventoryPlayer, World par2World, int par3, int par4, int par5){
		this.worldObj = par2World;
		this.posX = par3;
		this.posY = par4;
		this.posZ = par5;
		batterySlot = (IInventory) par2World.getBlockTileEntity(par3, par4, par5);
		this.addSlotToContainer(new Slot(this.batterySlot , 0, 95, 23));
		int var6;
		int var7;


		for (var6 = 0; var6 < 3; ++var6)
		{
			for (var7 = 0; var7 < 9; ++var7)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 84 + var6 * 18));
			}
		}

		for (var6 = 0; var6 < 9; ++var6)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 142));
		}
	}
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return true;
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

			if (par1 != 0 && par1 != 1)
			{
				if (var4.getItem() instanceof EnergyProvider || var4.getItem() instanceof EnergyUser)
				{
					if (((EnergyProvider)var4.getItem()) != null){
						if (!this.mergeItemStack(var4, 1, 0, false)){
							return null;
						}
					}else{
						if (!this.mergeItemStack(var4, 0, 1, false)){
							return null;
						}
					}
				}else if(var4.getItem() instanceof EnergyUser){
					if(((EnergyUser)var4.getItem()) != null){
						if(!mergeItemStack(var4, 1, 0, false)){
							return null;
						}
					}
					else{if(!mergeItemStack(var4, 0, 1, false)){
						return null;
					}
					}
				}
				else if(par1 >= 30 && par1 < 38 && !this.mergeItemStack(var4, 3, 30, false))
				{
					return null;
				}
			}
			else if (!this.mergeItemStack(var4, 3, 38, false))
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