
package hawksmachinery.tileentity;

import hawksmachinery.api.HMRecipes;
import hawksmachinery.api.HMRecipes.HMEnumProcessing;
import net.minecraft.src.FurnaceRecipes;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMTileEntitySinterer extends HMTileEntityMachine
{
	public HMTileEntitySinterer()
	{
		super();
		ELECTRICITY_REQUIRED = 10;
		TICKS_REQUIRED = 40;
		ELECTRICITY_LIMIT = 2000;
		containingItems = new ItemStack[6];
		machineEnum = HMEnumProcessing.SINTERER;
		voltage = 120;
		isProcessor = true;
		
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (this.canSmelt())
		{
			this.electricityStored -= this.ELECTRICITY_REQUIRED;
			
			if (this.workTicks == 0)
			{
				this.workTicks = this.TICKS_REQUIRED;
				
			}
			else
			{
				--this.workTicks;
				
				if (this.workTicks == 1)
				{
					this.smeltItems();
					this.workTicks = 0;
					
				}
				
			}
			
		}
		else
		{
			this.workTicks = 0;
			
		}
		
	}
	
	public boolean canSmelt()
	{
		if (this.isDisabled()) return false;
		ItemStack output = null;
		
		for (int counter = 0; counter < 4; ++counter)
		{
			if (output == null) output = HMRecipes.getResult(this.containingItems[counter], this.machineEnum);
			if (output == null) output = FurnaceRecipes.smelting().getSmeltingResult(this.containingItems[counter]);
			
			if (output != null && (this.electricityStored >= (this.ELECTRICITY_REQUIRED * 2)) && (this.containingItems[counter + 3] == null || (output.isItemEqual(this.containingItems[counter + 3]) && output.stackSize + this.containingItems[counter + 3].stackSize <= output.getMaxStackSize())))
			{
				return true;
			}
			
		}
		
		return false;
	}
	
	public void smeltItems()
	{
		if (this.canSmelt())
		{
			for (int counter = 0; counter < 4; ++counter)
			{
				ItemStack newItem = HMRecipes.getResult(this.containingItems[counter], this.machineEnum);
				if (newItem == null) newItem = FurnaceRecipes.smelting().getSmeltingResult(this.containingItems[counter]);
				
				if (newItem != null)
				{
					if (this.containingItems[counter + 3] == null)
					{
						this.containingItems[counter + 3] = newItem.copy();
					}
					else if (this.containingItems[counter + 3].isItemEqual(newItem))
					{
						this.containingItems[counter + 3].stackSize += newItem.stackSize;
					}
					
					this.decrStackSize(counter, (HMRecipes.getResult(this.containingItems[counter], this.machineEnum) != null) ? HMRecipes.getQuantity(this.containingItems[counter], this.machineEnum) : 1);
					
				}
				
			}
			
			this.randomlyDamageSelf();
			
		}
		
	}
	
}
