
package hawksmachinery;

import hawksmachinery.container.HMContainerWasher;
import hawksmachinery.tileentity.HMTileEntityWasher;
import org.lwjgl.opengl.GL11;
import universalelectricity.electricity.ElectricInfo;
import net.minecraft.src.GuiContainer;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.StatCollector;

/**
 * 
 * 
 * 
 * @author Elusivehawk
 */
public class HMGUIWasher extends GuiContainer
{
	public static HawksMachinery BASEMOD;
	private HMTileEntityWasher tileEntity;
	
	private int containerWidth;
	private int containerHeight;	
	
	public HMGUIWasher(InventoryPlayer playerInv, HMTileEntityWasher tileEntity)
	{
		super(new HMContainerWasher(playerInv, tileEntity));
		this.tileEntity = tileEntity;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRenderer.drawString(ElectricInfo.getDisplayShort(this.tileEntity.getVoltage(), ElectricInfo.ElectricUnit.VOLTAGE), 116, 60, 4210752);
		this.fontRenderer.drawString(ElectricInfo.getDisplayShort(this.tileEntity.electricityStored, ElectricInfo.ElectricUnit.WATT), 116, 70, 4210752);
		
	}
	
	/**
	 * Draw the background layer for the GuiContainer (everything behind the items)
	 */
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		int var4 = this.mc.renderEngine.getTexture(BASEMOD.GUI_PATH + "/Washer.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		this.containerWidth = (this.width - this.xSize) / 2;
		this.containerHeight = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(this.containerWidth, this.containerHeight, 0, 0, this.xSize, this.ySize);
		
		if (this.tileEntity.workTicks > 0)
		{
			int scale = this.tileEntity.getWashingStatus(this.tileEntity.TICKS_REQUIRED);
			this.drawTexturedModalRect(containerWidth + 52, containerHeight + 28, 176, 0, 52 - scale, 20);
		}

		this.drawTexturedModalRect(this.containerWidth - 32, this.containerHeight - 16, 0, 232, 240, 16);

		if (this.tileEntity.machineHP > 0)
		{
			int lightYPos = 0;
			
			if (this.tileEntity.machineHP <= 5)
			{
				lightYPos = 0;
			}
			else if (this.tileEntity.machineHP <= 10)
			{
				lightYPos = 10;
			}
			else if (this.tileEntity.machineHP <= 15)
			{
				lightYPos = 20;
			}
			else if (this.tileEntity.machineHP <= 20)
			{
				lightYPos = 30;
			}
			
			for (int counter = 0; counter < this.tileEntity.machineHP; ++counter)
			{
				this.drawTexturedModalRect((this.containerWidth - 12) + (counter * 10), this.containerHeight - 12, lightYPos, 248, 10, 8);
				
			}
			
		}
		
	}
	
}
