package tko.pnpnpn.client;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tko.pnpnpn.common.CommonProxy;
import tko.pnpnpn.common.S;
import tko.pnpnpn.dywe.DYWE;
import tko.pnpnpn.dywe.DYWEManager;
import tko.pnpnpn.dywe.block.R;
import tko.pnpnpn.dywe.block.RY;
import tko.pnpnpn.dywe.block.VCC;
import tko.pnpnpn.dywe.block.Y;
import tko.pnpnpn.dywe.block.YR;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);

        for (DYWE f : DYWEManager.FEATURES){
            f.renderBlock();
        }

    }

    @Override
    public void init(FMLInitializationEvent event)
    {
        super.init(event);

        for (DYWE f : DYWEManager.FEATURES){
            f.onClientInit();
        }
    }

    @Override
    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);

        for (DYWE f : DYWEManager.FEATURES){
            f.onClientPostInit();
        }
    }
}
