package zyngawow.nuclearcraft.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumToolMaterial;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class Pickaxe extends EnergyUser{
	ArrayList<String> modes = new ArrayList<String>();
	int selectedMode = 0;
	ItemStack is;
	public static final Block[] blocksEffectiveAgainst = new Block[] {Block.cobblestone, Block.stoneDoubleSlab, Block.stoneSingleSlab, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold, Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, Block.railDetector, Block.railPowered};
	public Pickaxe(int par1, int par2,
			EnumToolMaterial par3EnumToolMaterial) {
		super(par1, par2, par3EnumToolMaterial, blocksEffectiveAgainst);
		modes.add("3x3");
		// TODO Auto-generated constructor stub
	}
	public String getTextureFile()
	{
		return "/zyngawow/nuclearcraft/items/itemTextures.png";
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int par7, float par8, float par9, float par10){
		this.is = is;
		if(!player.isSneaking()){
			if(player.rotationPitch <= -27){
				if(this.modes.get(selectedMode).equals("3x3")){
					threeByThreeUpDown(world, player, x, y, z);
				}
			}else if(player.rotationPitch >= 50){
				if(this.modes.get(selectedMode).equals("3x3")){
					threeByThreeUpDown(world, player, x, y, z);
				}
			}else if((Math.abs(player.rotationYaw) >= 51 && Math.abs(player.rotationYaw) < 135) || 
					(Math.abs(player.rotationYaw) >= 225 && Math.abs(player.rotationYaw) < 315)){
				if(this.modes.get(selectedMode).equals("3x3")){
					threeByThreeForBack(world, player, x, y, z);
				}
			}else if((Math.abs(player.rotationYaw) >= 315 && Math.abs(player.rotationYaw) < 360) ||
					(Math.abs(player.rotationYaw) >= 0 && Math.abs(player.rotationYaw) < 30) ||
					(Math.abs(player.rotationYaw) >= 135 && Math.abs(player.rotationYaw) < 225)){
				if(this.modes.get(selectedMode).equals("3x3")){
					threeByThreeLeftRight(world, player, x, y, z);
				}
			}
		}else{
			destroyBlock(world, player, x, y, z);
		}
		return false;
	}


	public void threeByThreeUpDown(World world,EntityPlayer player, int x, int y, int z){
		destroyBlock(world, player, x+1, y, z+1);
		destroyBlock(world, player, x+1, y, z-1);
		destroyBlock(world, player, x+1, y, z);
		destroyBlock(world, player, x, y, z);
		destroyBlock(world, player, x, y, z+1);
		destroyBlock(world, player, x, y, z-1);
		destroyBlock(world, player, x-1, y, z);
		destroyBlock(world, player, x-1, y, z+1);
		destroyBlock(world, player, x-1, y, z-1);
	}

	public void threeByThreeForBack(World world,EntityPlayer player, int x, int y, int z){
		destroyBlock(world, player, x, y+1, z+1);
		destroyBlock(world, player, x, y+1, z-1);
		destroyBlock(world, player, x, y+1, z);
		destroyBlock(world, player, x, y, z);
		destroyBlock(world, player, x, y, z+1);
		destroyBlock(world, player, x, y, z-1);
		destroyBlock(world, player, x, y-1, z);
		destroyBlock(world, player, x, y-1, z+1);
		destroyBlock(world, player, x, y-1, z-1);		
	}

	public void threeByThreeLeftRight(World world,EntityPlayer player, int x, int y, int z){		
		destroyBlock(world, player, x-1, y+1, z);
		destroyBlock(world, player, x, y+1, z);
		destroyBlock(world, player, x+1, y+1, z);
		destroyBlock(world, player, x-1, y, z);
		destroyBlock(world, player, x, y, z);
		destroyBlock(world, player, x+1, y, z);
		destroyBlock(world, player, x-1, y-1, z);
		destroyBlock(world, player, x, y-1, z);
		destroyBlock(world, player, x+1, y-1, z);
	}

	protected void destroyBlock(World world,EntityPlayer player, int x, int y, int z){
		if(world.getBlockId(x, y, z) != Block.bedrock.blockID && world.getBlockId(x, y, z) != 0){
			if(is.getItemDamage() <= is.getMaxDamage() - 9 && player.capabilities.isCreativeMode == false){
				Block.blocksList[world.getBlockId(x, y, z)].harvestBlock(world, player, x, y, z, 1);
				world.setBlockWithNotify(x, y, z, 0);
				is.damageItem(10, player);
			}else if(player.capabilities.isCreativeMode == true){
				Block.blocksList[world.getBlockId(x, y, z)].harvestBlock(world, player, x, y, z, 1);
				world.setBlockWithNotify(x, y, z, 0);
			}
		}
	}
	@Override
	public void addInformation(ItemStack par1ItemStack, List par2List){
		par2List.add(par1ItemStack.getMaxDamage() - par1ItemStack.getItemDamage() + "/" + par1ItemStack.getMaxDamage());
	}

}
