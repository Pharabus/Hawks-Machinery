
package hawksmachinery.container;

import hawksmachinery.HMInventoryCrafting;
import net.minecraft.src.Container;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.Slot;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMContainerStarForge extends Container
{
	public HMInventoryCrafting matrix = new HMInventoryCrafting("Star Forge", this, 3, 3);
	
	public HMContainerStarForge(InventoryPlayer playerInv)
	{
		int slotNumber = 0;
		
		for (int width = 0; width < matrix.getInvWidth(); ++width)
		{
			for (int height = 0; height < matrix.inventoryHeight; ++height)
			{
				this.addSlotToContainer(new Slot(matrix, slotNumber, ((height + 1) * 18) + 14, ((width + 1) * 18) + 15));
				++slotNumber;
			}
			
		}

		for (int counter = 0; counter < 3; ++counter)
		{
			for (int var4 = 0; var4 < 9; ++var4)
			{
				this.addSlotToContainer(new Slot(playerInv, var4 + counter * 9 + 9, 8 + var4 * 18, 84 + counter * 18));
			}
		}
		
		for (int counter = 0; counter < 9; ++counter)
		{
			this.addSlotToContainer(new Slot(playerInv, counter, 8 + counter * 18, 142));
		}
		
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return true;
	}
	
}
