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

public class Y extends DYWE
{
    public static final String Id = "yellow-logic-wire";
    public static Block yellowLogicWire;

    @Override
    public void registerInGame()
    {
        yellowLogicWire = (new BlockLogicWire(Wire.P))
                .setCreativeTab(PNPNPN.pnpnpnTab)
                .setUnlocalizedName(Id)
                .setRegistryName(Id);
        GameRegistry.register(Y.yellowLogicWire);
        GameRegistry.register(S.getUnRegisterItemBlock(Y.yellowLogicWire));
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
        S.renderBlock(Y.yellowLogicWire);
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
