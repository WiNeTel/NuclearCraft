package common.zyngawow.nuclearcraft.blocks;

import net.minecraft.src.Block;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class EnergyGenerator extends BlockContainer{
	int capacity;
	public EnergyGenerator(int par1, Material par2Material) {
		super(par1, Material.rock);
	}
	
	public void setCapacity(int x){
		this.capacity = x;
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving) {
		
	}
	public TileEntity createNewTileEntity(World par1World) {
		// TODO Auto-generated method stub
		return null;
	}
}
