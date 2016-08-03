package tko.pnpnpn.dywe.block;

import net.minecraft.block.Block;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;
import tko.pnpnpn.common.block.BlockMetal;
import tko.pnpnpn.dywe.DYWE;

public class M extends DYWE
{
	public static final String Id = "metal";
	public static Block m;
	
	@Override
	public void registerInGame()
	{
		 m = (new BlockMetal()).setCreativeTab(PNPNPN.pnpnpnTab)
		 .setUnlocalizedName(Id)
		 .setRegistryName(Id);
		 GameRegistry.register(m);
		 GameRegistry.register(S.getUnRegisterItemBlock(M.m));
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
		// TODO
		
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
	public void renderBlock()
	{
		S.renderBlock(M.m);		
	}

	@Override
	public void onClientInit()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClientPostInit()
	{
		// TODO Auto-generated method stub
		
	}

}
