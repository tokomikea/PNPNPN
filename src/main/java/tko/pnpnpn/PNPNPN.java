package tko.pnpnpn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tko.pnpnpn.common.CommonProxy;

@Mod(
        modid = PNPNPN.MODID,
        name = PNPNPN.MODID,
        version = PNPNPN.VERSION,
        acceptedMinecraftVersions = "1.10.2",
        dependencies = "required-after:Forge@[12.18.0.2046,);")
public class PNPNPN
{
    public static final String MODID = "pnpnpn";
    public static final String VERSION = "0.0.1#1";
    public static final String SERVER_SIDE = "tko.pnpnpn.common.CommonProxy";
    public static final String CLIENT_SIDE = "tko.pnpnpn.client.ClientProxy";

    public static final Logger LOG = LogManager.getLogger(PNPNPN.MODID);

    @Mod.Instance
    public static PNPNPN INSTANCE;

    @SidedProxy(
            modId = PNPNPN.MODID,
            serverSide = PNPNPN.SERVER_SIDE,
            clientSide = PNPNPN.CLIENT_SIDE)
    public static CommonProxy proxy;

    public static class WujiaCT extends CreativeTabs
    {
        public WujiaCT(String label)
        {
            super(label);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            return Items.APPLE;
        }
    }

    public static final CreativeTabs pnpnpnTab = new WujiaCT("pnpnpn_tab");

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
