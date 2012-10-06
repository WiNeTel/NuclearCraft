package nuclearcraft.common.block;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Material;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import nuclearcraft.common.NuclearCraft;
import nuclearcraft.common.lib.GuiIds;
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

		if (!world.isRemote) {
			TileSolarGenerator tileSolarGenerator = (TileSolarGenerator) world.getBlockTileEntity(x, y, z);

			if (tileSolarGenerator != null && !player.isSneaking()) {
				player.openGui(NuclearCraft.instance, GuiIds.SOLAR_GENERATOR, world, x, y, z);
			}
		}
		return true;

	}

}
