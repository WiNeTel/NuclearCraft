package common.zyngawow.nuclearcraft.items;

import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemTool;
import net.minecraft.src.World;

public class EnergyUser extends ItemTool{


	protected EnergyUser(int par1, int par2,
			EnumToolMaterial par3EnumToolMaterial, Block[] par4ArrayOfBlock) {
		super(par1, par2, par3EnumToolMaterial, par4ArrayOfBlock);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
	{
		return false;
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
