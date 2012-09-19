
package hawksmachinery.itemblocks;

import net.minecraft.src.EnumRarity;
import net.minecraft.src.ItemBlock;
import net.minecraft.src.ItemStack;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HawkItemBlockChunkloader extends ItemBlock
{
	public HawkItemBlockChunkloader(int id)
	{
		super(id);
	}
	
	@Override
	public EnumRarity getRarity(ItemStack item)
	{
		return EnumRarity.rare;
	}
	
}
