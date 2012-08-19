
package hawksmachinery;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ModLoader;
import net.minecraft.src.TileEntity;
import net.minecraft.src.World;
import net.minecraftforge.client.MinecraftForgeClient;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMClientProxy extends HMCommonProxy
{
	public HawksMachinery BASEMOD;
	
	@Override
	public void preInit()
	{
		//Preload textures
		MinecraftForgeClient.preloadTexture(HawkManager.BLOCK_TEXTURE_FILE);
		MinecraftForgeClient.preloadTexture(HawkManager.ITEM_TEXTURE_FILE);
	}
	
	@Override
	public void init()
	{
		ModLoader.registerTileEntity(HawkTileEntityGrinder.class, "Grinder");
		
		HawkLanguageCore.addToolTips();
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
		
		if (tileEntity != null)
        {
			return new HawkGUIGrinder(player.inventory, ((HawkTileEntityGrinder)tileEntity));
        }
		
		return null;
	}
	
	@Override
	public World getWorld()
	{
		return ModLoader.getMinecraftInstance().theWorld;
	}
}