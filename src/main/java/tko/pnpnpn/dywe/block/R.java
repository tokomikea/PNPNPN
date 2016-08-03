package tko.pnpnpn.dywe.block;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;
import tko.pnpnpn.common.block.BlockLogicWire;
import tko.pnpnpn.common.block.Wire;
import tko.pnpnpn.dywe.DYWE;

public class R extends DYWE
{

	public static final String Id = "red-logic-wire";
	public static Block redLogicWire;

	@Override
	public void registerInGame()
	{
		redLogicWire = (new BlockLogicWire(Wire.N))
				.setCreativeTab(PNPNPN.pnpnpnTab)
				.setUnlocalizedName(Id)
				.setRegistryName(Id);
		GameRegistry.register(R.redLogicWire);
		GameRegistry.register(S.getUnRegisterItemBlock(R.redLogicWire));
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
		S.renderBlock(R.redLogicWire);			
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
