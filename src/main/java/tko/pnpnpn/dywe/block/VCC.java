package tko.pnpnpn.dywe.block;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;
import tko.pnpnpn.common.block.BlockVCC;
import tko.pnpnpn.dywe.DYWE;

public class VCC extends DYWE
{

	public static final String Id = "vcc";
	public static Block vcc;

	@Override
	public void registerInGame()
	{
		vcc = (new BlockVCC())
				.setCreativeTab(PNPNPN.pnpnpnTab)
				.setUnlocalizedName(Id)
				.setRegistryName(Id);
		GameRegistry.register(VCC.vcc);
		GameRegistry.register(S.getUnRegisterItemBlock(VCC.vcc));
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
		S.renderBlock(VCC.vcc);
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
