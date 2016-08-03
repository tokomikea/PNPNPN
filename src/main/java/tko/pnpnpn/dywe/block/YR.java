package tko.pnpnpn.dywe.block;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;
import tko.pnpnpn.common.block.BlockLogicD;
import tko.pnpnpn.common.block.BlockLogicWire;
import tko.pnpnpn.dywe.DYWE;

public class YR extends DYWE
{
	public static final String Id = "y-r-logic-wire";
	public static Block yr;

	@Override
	public void registerInGame()
	{
		yr = (new BlockLogicD((BlockLogicWire)Y.yellowLogicWire))
				.setCreativeTab(PNPNPN.pnpnpnTab)
				.setUnlocalizedName(YR.Id)
				.setRegistryName(YR.Id);
		GameRegistry.register(YR.yr);
		GameRegistry.register(S.getUnRegisterItemBlock(YR.yr));		
	}

	@Override
	public void onInit()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPostInit()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupRecipes()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setupConfiguration(Configuration config)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean usesEvents()
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void renderBlock()
	{
		S.renderBlock(YR.yr);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onClientInit()
	{
		// TODO
		
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onClientPostInit()
	{
		// TODO Auto-generated method stub
		
	}

}
