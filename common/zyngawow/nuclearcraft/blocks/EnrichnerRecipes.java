package common.zyngawow.nuclearcraft.blocks;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.Block;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;


public class EnrichnerRecipes {
	private static final EnrichnerRecipes enrichmentBase = new EnrichnerRecipes();

	private Map enrichmentList = new HashMap();
    private Map experienceList = new HashMap();
    private Map costList = new HashMap();
    private Map metaEnrichmentList = new HashMap();
	
	public static final EnrichnerRecipes enrichment(){
		return enrichmentBase;
	}
	private EnrichnerRecipes(){
		this.addEnrichment(Item.coal.shiftedIndex, new ItemStack(Block.blockSteel), 0.7F, 10);
		this.addEnrichment(Item.ingotIron.shiftedIndex, new ItemStack(Item.coal), 0.7F, 10);
	}

	public void addEnrichment(int par1, ItemStack par2ItemStack, float par3, int par4){
        this.enrichmentList.put(Integer.valueOf(par1), par2ItemStack);
        this.experienceList.put(Integer.valueOf(par2ItemStack.itemID), Float.valueOf(par3));
        this.costList.put(Integer.valueOf(par2ItemStack.itemID), par4);
    }
	public Map getEnrichmentList(){
        return this.enrichmentList;
    }

    public float getExperience(int par1)
    {
        return this.experienceList.containsKey(Integer.valueOf(par1)) ? ((Float)this.experienceList.get(Integer.valueOf(par1))).floatValue() : 0.0F;
    }
    public int getCost(int par1){
    	return (Integer) (this.costList.containsKey(Integer.valueOf(par1)) ? (this.costList.get(Integer.valueOf(par1))) : 0.0F);
    }
    public ItemStack getEnrichmentResult(ItemStack item) 
    {
        if (item == null)
        {
            return null;
        }
        ItemStack ret = (ItemStack)metaEnrichmentList.get(Arrays.asList(item.itemID, item.getItemDamage()));
        if (ret != null) 
        {
            return ret;
        }
        return (ItemStack)enrichmentList.get(Integer.valueOf(item.itemID));
    }
    public void addEnrichment(int itemID, int metadata, ItemStack itemstack)
    {
        metaEnrichmentList.put(Arrays.asList(itemID, metadata), itemstack);
    }
}
