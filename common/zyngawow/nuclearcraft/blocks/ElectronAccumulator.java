package common.zyngawow.nuclearcraft.blocks;

import java.util.Random;

import common.zyngawow.nuclearcraft.core.NuclearCraft;


import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntityFurnace;
import net.minecraft.src.TileEntityMobSpawner;
import net.minecraft.src.World;

public class ElectronAccumulator extends BlockContainer{

    EntityPlayer player;
    
    public ElectronAccumulator(int par1, Material par2Material) {
	super(par1, par2Material);
	// TODO Auto-generated constructor stub
    }
    @Override
    public TileEntity createNewTileEntity(World par1World)
    {
	return new TileEntityElectronAccumulator();
    }
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
    {
	this.player = player;
	if(FMLCommonHandler.instance().getSide() == Side.CLIENT){
	    player.openGui(NuclearCraft.instance, 1, world, x, y, z);
		return true;
		}
		return false;
    }
    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
	TileEntityElectronAccumulator teec = (TileEntityElectronAccumulator) par1World.getBlockTileEntity(par2, par3, par4);
	if(teec != null){
	    if(teec.getStackInSlot(0) != null){
		par1World.spawnEntityInWorld(new EntityItem(par1World, par2, par3, par4, teec.getStackInSlot(0)));
	    }
	    if(teec.getStackInSlot(1) != null){
		par1World.spawnEntityInWorld(new EntityItem(par1World, par2, par3, par4, teec.getStackInSlot(1)));
	    }
	}
	super.harvestBlock(par1World, this.player, par2, par3, par4, par5);
	super.breakBlock(par1World, par2, par3, par4, par5, par6);
	par1World.removeBlockTileEntity(par2, par3, par4);
    }
    @Override
    public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
    }
}
