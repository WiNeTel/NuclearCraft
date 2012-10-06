package nuclearcraft.common.block;

import java.util.Random;
import java.util.logging.Level;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityItem;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IInventory;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import nuclearcraft.common.NuclearCraft;
import nuclearcraft.common.core.helper.LogHelper;
import nuclearcraft.common.lib.GuiIds;
import nuclearcraft.common.lib.Reference;
import nuclearcraft.common.lib.RenderIds;
import nuclearcraft.common.tile.TileSolarGenerator;

public class BlockSolarGenerator extends BlockNC {

	protected BlockSolarGenerator(int id) {
		super(id, Material.rock);
		setHardness(5F);
		setCreativeTab(CreativeTabs.tabDecorations);
	}

	@Override
	public TileEntity createNewTileEntity(World var1) {
		// TODO Auto-generated method stub
		return new TileSolarGenerator();
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public int getRenderType() {
		return RenderIds.solarGeneratorRenderId;
	}

	@Override
	public int getBlockTextureFromSide(int par1) {
		return 1;
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {

		TileSolarGenerator tileSolarGenerator = (TileSolarGenerator) world.getBlockTileEntity(x, y, z);

		if (tileSolarGenerator == null) {
			LogHelper.log(Level.SEVERE, "The tile entity is null");
			return true;
		}
		if (world.isRemote) {
			LogHelper.log(Level.SEVERE, "The world is remote");
			return true;
		}
		player.openGui(NuclearCraft.instance, GuiIds.SOLAR_GENERATOR, world, x, y, z);

		return true;

	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int par5, int par6) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z){
		Random rand = new Random();

		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world,
						x + rx, y + ry, z + rz,
						new ItemStack(item.itemID, item.stackSize, item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.item.setTagCompound((NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}


}
