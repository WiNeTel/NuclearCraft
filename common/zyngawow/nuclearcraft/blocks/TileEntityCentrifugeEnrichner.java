package common.zyngawow.nuclearcraft.blocks;

import java.util.Random;

import common.zyngawow.nuclearcraft.items.EnergyProvider;
import common.zyngawow.nuclearcraft.items.EnergyUser;

import common.zyngawow.nuclearcraft.items.Battery;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.IInventory;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.NBTTagList;
import net.minecraft.src.TileEntity;

public class TileEntityCentrifugeEnrichner extends TileEntity implements IInventory{
    float output;
    double storedEnergy;
    float enrichmentTime=0;
    int i=0;
    int maxEnergy = 1000;
    public ItemStack[] batteryStack = new ItemStack[8];
    /**
     * batteryStack[0] for loading
     * batteryStack[1] for unloading
     */
    private NBTTagList tagList;
    private NBTTagList itemList;
    int j = 0;
    public NBTTagCompound stackTagCompound;
    public TileEntityCentrifugeEnrichner()
    {
    }
    @Override
    public void updateEntity(){
	for(int k=0;k<32;k++){
	    if(batteryStack[0] != null){
		if((EnergyProvider.class.isAssignableFrom(batteryStack[0].getItem().getClass()) && batteryStack[0].getItemDamage() < batteryStack[0].getMaxDamage() && this.storedEnergy < maxEnergy)){
		    batteryStack[0].setItemDamage(batteryStack[0].getItemDamage() + 1);
		    storedEnergy+=0.97F;
		}
	    }
	}
	if(enrichmentTime<0.05/(storedEnergy/2000)){
	    enrichmentTime+=0.05;
	}else{

	    if(batteryStack[1] != null){
		if(storedEnergy > 10){
		    enrichItem(1);
		}
	    }
	    if(batteryStack[2] != null){
		if(storedEnergy > 10){
		    enrichItem(2);
		}
	    }
	    if(batteryStack[3] != null){
		if(storedEnergy > 10){
		    enrichItem(3);
		}
	    }
	    if(batteryStack[4] != null){
		if(storedEnergy > 10){
		    enrichItem(4);
		}
	    }
	    if(batteryStack[5] != null){
		if(storedEnergy > 10){
		    enrichItem(5);
		}
	    }
	    if(batteryStack[6] != null){
		if(storedEnergy > 10){
		    enrichItem(6);
		}
	    }

	    enrichmentTime = 0;
	}

    }
    
    protected float getChance(){
	for(int i=1;i<7;i++){
	    if(batteryStack[i] != null){
		System.out.println(EnrichnerRecipes.enrichment().getChance(this.batteryStack[i].itemID));
		return EnrichnerRecipes.enrichment().getChance(this.batteryStack[i].itemID);
	    }
	}
	return 0;
    }

    private void enrichItem(int i){
	if(this.canEnrich(i)){
	    {
		ItemStack var1 = EnrichnerRecipes.enrichment().getEnrichmentResult(this.batteryStack[i]);
		Random random = new Random();
		if(Math.abs(random.nextFloat())*100 <= EnrichnerRecipes.enrichment().getChance(this.batteryStack[i].itemID)){
		    if (this.batteryStack[7] == null)
		    {
			this.batteryStack[7] = var1.copy();
			storedEnergy-=EnrichnerRecipes.enrichment().getCost(this.batteryStack[i].itemID);
		    }
		    else if (this.batteryStack[7].isItemEqual(var1))
		    {
			batteryStack[7].stackSize += var1.stackSize;
			storedEnergy-=EnrichnerRecipes.enrichment().getCost(this.batteryStack[i].itemID);
		    }

		    --this.batteryStack[i].stackSize;

		    if (this.batteryStack[i].stackSize <= 0)
		    {

			this.batteryStack[i] = null;
		    }
		}else{
		    storedEnergy-=EnrichnerRecipes.enrichment().getCost(this.batteryStack[i].itemID)/10;
		}

	    }
	}
    }

    private boolean canEnrich(int x){
	if (this.batteryStack[x] == null)
	{

	    System.out.println(batteryStack[x] + " " + x);
	    return false;
	}
	else{
	    ItemStack var1 = EnrichnerRecipes.enrichment().getEnrichmentResult(this.batteryStack[x]);
	    if (var1 == null) return false;
	    if (this.batteryStack[7] == null) return true;
	    if (!this.batteryStack[7].isItemEqual(var1)) return false;
	    int result = batteryStack[7].stackSize + var1.stackSize;
	    return (result <= getInventoryStackLimit() && result <= var1.getMaxStackSize());
	}
    }

    protected double getEnergy(){
	if(storedEnergy <= 0){
	    return 0;
	}else if(storedEnergy >=maxEnergy){
	    return maxEnergy;
	}
	return storedEnergy;
    }

    @Override
    public int getSizeInventory()
    {
	return batteryStack.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int par1)
    {
	return this.batteryStack[par1];
    }

    /**
     * Returns the name of the inventory.
     */
    @Override
    public String getInvName()
    {
	return "Battery";
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
	if (this.batteryStack[par1] != null)
	{
	    ItemStack var3 = this.batteryStack[par1];
	    this.batteryStack[par1] = null;
	    return var3;
	}
	else
	{
	    return null;
	}
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
	if (this.batteryStack[par1] != null)
	{
	    ItemStack var2 = this.batteryStack[par1];
	    this.batteryStack[par1] = null;
	    return var2;
	}
	else
	{
	    return null;
	}
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
	this.batteryStack[par1] = par2ItemStack;
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit()
    {
	return 64;
    }

    /**
     * Called when an the contents of an Inventory change, usually
     */
    @Override
    public void onInventoryChanged() {
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
	return true;
    }

    @Override
    public void openChest() {
	// TODO Auto-generated method stub

    }

    @Override
    public void closeChest() {
	// TODO Auto-generated method stub

    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
	super.readFromNBT(nbttagcompound);
	NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
	this.storedEnergy = nbttagcompound.getDouble("storedEnergy");
	batteryStack = new ItemStack[getSizeInventory()];
	for (int i = 0; i < nbttaglist.tagCount(); i++)
	{
	    NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.tagAt(i);
	    int j = nbttagcompound1.getByte("Slot") & 0xff;
	    if (j >= 0 && j < batteryStack.length+1)
	    {
		batteryStack[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
	    }
	}
    }


    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
	super.writeToNBT(nbttagcompound);
	nbttagcompound.setDouble("storedEnergy", storedEnergy);
	NBTTagList nbttaglist = new NBTTagList();
	for (int i = 0; i < batteryStack.length; i++)
	{
	    if (batteryStack[i] != null)
	    {
		NBTTagCompound nbttagcompound1 = new NBTTagCompound();
		nbttagcompound1.setByte("Slot", (byte) i);
		batteryStack[i].writeToNBT(nbttagcompound1);
		nbttaglist.appendTag(nbttagcompound1);
	    }
	}

	nbttagcompound.setTag("Items", nbttaglist);
    }


}
