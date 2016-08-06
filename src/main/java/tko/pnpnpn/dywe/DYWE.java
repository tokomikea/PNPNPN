package tko.pnpnpn.dywe;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * I get to the idea, feature system, from darkhas's Dark-Utilities mod.
 * Thank you darkhax.
 */
public abstract class DYWE
{
    abstract public void registerInGame();

    abstract public void onInit();

    abstract public void onPostInit();

    abstract public void setupRecipes();

    abstract public void setupConfiguration(Configuration config);

    abstract public boolean usesEvents();

    @SideOnly(Side.CLIENT)
    abstract public void renderBlock();

    @SideOnly(Side.CLIENT)
    abstract public void onClientInit();

    @SideOnly(Side.CLIENT)
    abstract public void onClientPostInit();
}
