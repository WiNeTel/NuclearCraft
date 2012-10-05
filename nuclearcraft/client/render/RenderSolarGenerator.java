package nuclearcraft.client.render;

import net.minecraft.src.TileEntity;
import net.minecraft.src.TileEntitySpecialRenderer;
import nuclearcraft.common.tile.TileSolarGenerator;

public class RenderSolarGenerator extends TileEntitySpecialRenderer{
	static final float scale = (float) (1.0 / 16.0);

	private ModelSolarGenerator modelSolarGenerator = new ModelSolarGenerator();

	@Override
	public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float tick) {
		modelSolarGenerator.render((TileSolarGenerator)tileEntity, x, y, z);
	}

}
