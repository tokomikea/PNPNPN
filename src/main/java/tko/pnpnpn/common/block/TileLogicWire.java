package tko.pnpnpn.common.block;

import static tko.pnpnpn.common.block.BlockLogic.POWER;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.IExtendedBlockState;
import tko.pnpnpn.ModBlocks;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;

public class TileLogicWire extends TileEntity
    implements ILogicable
{

    private Wire wire;
    private boolean logic;

    public TileLogicWire()
    {
        super();
    }

    public TileLogicWire(Wire wire, boolean logic)
    {
        super();
        this.wire = wire;
        this.logic = logic;
    }

    @Override
    public boolean canPassLogic(Wire source)
    {
        return this.wire == source
                || source == Wire.M;
    }

    @Override
    public Wire getWireType(){return this.wire;}

    @Override
    public void setValue(Wire wire, boolean v, Set mem)
    {
        if(wire == this.wire){
            if(v != logic){
                mem.add(pos);
                IBlockState state = worldObj.getBlockState(pos);
                worldObj.setBlockState(pos, state.withProperty(POWER, v));
                List<ILogicable> list = Logic.getLogicables(worldObj, pos, mem);
                for(ILogicable tile : list){
                    tile.setValue(wire, v, mem);
                }
            }
        }
    }

    @Override
    public boolean getValue(Wire wire)
    {
        if(canPassLogic(wire)){
            return logic;
        }
        return false;
    }

    @Override
    public boolean calculateLogic(Wire wire)
    {
        return Logic.calculateLogic(this, wire, (new HashSet()));
    }

    @Override
    public IBlockAccess getBlockAccess()
    {
        return this.worldObj;
    }

    @Override
    public BlockPos getBlockPos()
    {
        return this.pos;
    }












    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.wire = Wire.i2t(compound.getByte("logic_type"));
        this.logic = compound.getBoolean("logic_value");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setByte("logic_type", (byte) Wire.t2i(this.wire));
        compound.setBoolean("logic_value", this.logic);
        return super.writeToNBT(compound);
    }
}
