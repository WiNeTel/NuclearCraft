package zyngawow.nuclearcraft.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemTool;
import net.minecraft.src.World;

public class EnergyProvider extends ItemTool{

	private InventoryPlayer inventory;
	private ItemStack stack;
	static EnumToolMaterial par3EnumToolMaterial = EnumToolMaterial.IRON;
	static Block[] par4ArrayOfBlock = {Block.dirt, Block.sand};
	protected EnergyProvider(int par1, int par2){
		super(par1, 2, par3EnumToolMaterial, par4ArrayOfBlock);
		// TODO Auto-generated constructor stub
		this.setMaxDamage(par2);
	}
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{

		return false;
	}
	@Override
	public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
		EntityPlayer player = (EntityPlayer) par3Entity;
		inventory = player.inventory;
		for(int i=0;i<inventory.getSizeInventory();i++){
			stack = inventory.getStackInSlot(i);
			for(int j=0;j<32;j++){
			if(stack != null){
				if(EnergyUser.class.isAssignableFrom(stack.getItem().getClass()) && stack.getItemDamage() >= 1 && par1ItemStack.getItemDamage() < par1ItemStack.getMaxDamage()){
					stack.setItemDamage(stack.getItemDamage() - 1);
					par1ItemStack.damageItem(1, player);
				}
			}
			}
		}
	}
	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) 
	{
		if(itemstack.getItemDamage() < itemstack.getMaxDamage() - 2){
			return true;
		}
		return false;
	}
	@Override
	public void addInformation(ItemStack par1ItemStack, List par2List){
		par2List.add(par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() + "/" + par1ItemStack.getMaxDamage());
	}
}
