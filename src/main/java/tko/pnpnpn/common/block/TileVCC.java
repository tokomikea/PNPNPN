package tko.pnpnpn.common.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class TileVCC extends TileEntity
    implements ILogicProvider
{

    private boolean powered;

    @Override
    public boolean getLogic(Wire w)
    {
        return Boolean.valueOf(this.getPowered());
    }

    public boolean getPowered()
    {
        return this.powered;
    }

    public void setPowered(boolean bool)
    {
        this.powered = bool;
    }
















    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.powered = compound.getBoolean("powered");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setBoolean("powered", this.powered);
        return super.writeToNBT(compound);
    }
}
