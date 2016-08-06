package tko.pnpnpn.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tko.pnpnpn.ModBlocks;
import tko.pnpnpn.ModItems;
import tko.pnpnpn.dywe.DYWE;
import tko.pnpnpn.dywe.DYWEManager;

public class CommonProxy
{
    public void preInit(FMLPreInitializationEvent event)
    {
        ModBlocks.register();
        ModItems.register();

        for (DYWE f : DYWEManager.FEATURES){
            f.registerInGame();
        }
    }

    public void init(FMLInitializationEvent event)
    {

        for (DYWE f : DYWEManager.FEATURES){
            f.onInit();
        }
    }

    public void postInit(FMLPostInitializationEvent event)
    {

        for (DYWE f : DYWEManager.FEATURES){
            f.onPostInit();
        }
    }
}
