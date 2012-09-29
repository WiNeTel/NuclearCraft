package zyngawow.nuclearcraft.blocks;

import zyngawow.nuclearcraft.core.NuclearCraft;
import net.minecraft.src.BlockContainer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;

public class EnergyStorage extends BlockContainer{

	public EnergyStorage(int par1) {
		super(par1, Material.rock);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityEnergyStorage();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if(world.isRemote){
			return true;
		}else{
			player.openGui(NuclearCraft.instance, 1, world, x, y, z);
			return true;
		}
	}

}
