package tko.pnpnpn;

import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import tko.pnpnpn.common.block.TileLogicD;
import tko.pnpnpn.common.block.TileLogicWire;
import tko.pnpnpn.common.block.TileMetal;
import tko.pnpnpn.common.block.TileVCC;
import tko.pnpnpn.dywe.DYWEManager;
import tko.pnpnpn.dywe.block.M;
import tko.pnpnpn.dywe.block.R;
import tko.pnpnpn.dywe.block.RY;
import tko.pnpnpn.dywe.block.VCC;
import tko.pnpnpn.dywe.block.Y;
import tko.pnpnpn.dywe.block.YR;

public class ModBlocks
{
	public static void register()
	{
		DYWEManager.register((new R()), R.Id);
		DYWEManager.register((new Y()), Y.Id);
		DYWEManager.register((new RY()), RY.Id);
		DYWEManager.register((new YR()), YR.Id);
		DYWEManager.register((new VCC()), VCC.Id);
		DYWEManager.register((new M()), M.Id);
		
		GameRegistry.registerTileEntity(TileLogicWire.class, "tile-logic-wire");
		GameRegistry.registerTileEntity(TileVCC.class, "tile-vcc");
		GameRegistry.registerTileEntity(TileLogicD.class, "tile-d");
		GameRegistry.registerTileEntity(TileMetal.class, "tile-m");
	}
}
