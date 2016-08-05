package tko.pnpnpn.common.block;

import static tko.pnpnpn.common.block.BlockLogicD.INSIDE;
import static tko.pnpnpn.common.block.BlockLogicD.OUTSIDE;

import java.util.HashSet;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public final class TileLogicD extends TileEntity
	implements ILogicable
{
	private TileLogicWire outside;
	private TileLogicWire inside;
	
	public TileLogicD()
	{
		super();
	}
	
	private Wire outw;
	private Wire inw;
	private boolean ol;
	private boolean il;
	
	public TileLogicD(Wire wire, boolean o, boolean i)
	{
		outw = wire;
		inw = Wire.oopp(wire);
		ol = o;
		il = i;
	}
	
	@Override
	public void onLoad()
	{
		this.outside = (new TileLogicWire(outw, ol));
		outside.setWorldObj(worldObj);
		outside.setPos(pos);
		this.inside = (new TileLogicWire(inw, il));
		inside.setWorldObj(worldObj);
		inside.setPos(pos);
	}

	@Override
	public boolean canPassLogic(Wire wire)
	{
		if (wire == outside.getWireType()) {
			return false;
		} else if (wire == inside.getWireType()
					|| wire == Wire.M) {
			return worldObj.getBlockState(pos).getValue(INSIDE);
		}
		return false;
	}
	
	private boolean canInsidePassLogic(Wire wire)
	{
		if (wire == Wire.P) {
			return (! worldObj.getBlockState(pos).getValue(OUTSIDE));
		} else {
			return (  worldObj.getBlockState(pos).getValue(OUTSIDE));
		}
	}
	
	@Override
	public boolean calculateLogic(Wire wire)
	{
		if(wire == this.outside.getWireType()) {
			return this.outside.calculateLogic(wire);
		} else if (wire == this.inside.getWireType()) {
			return this.inside.calculateLogic(wire);
		}
		return false;
	}
	

	@Override
	public Wire getWireType()
	{
		return inw;
	}
	
	@Override
	public boolean getValue(Wire wire)
	{
		if (wire == outside.getWireType()) {
			return outside.getValue(wire);
		} else if(wire == inside.getWireType()) {
			return inside.getValue(wire);
		}
		return false;
	}
	
	@Override
	public void setValue(Wire wire, boolean v, Set mem) {
		if(wire == outw){
			if(v != ol){
				this.inside.setValue(wire, v, mem);
			}
		}
	}
	
	@Override
	public IBlockAccess getBlockAccess(){
		return this.worldObj;
	}
	
	@Override
	public BlockPos getBlockPos(){
		return this.pos;
	}
	

	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
//		this.outside.readFromNBT(compound);
//		this.inside.readFromNBT(compound);
		this.outw = Wire.i2t(compound.getByte("outw"));
		this.inw = Wire.i2t(compound.getByte("inw"));
		this.ol = compound.getBoolean("ol");
		this.il = compound.getBoolean("il");
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
//		this.inside.writeToNBT(compound);
//		this.outside.writeToNBT(compound);
		compound.setByte("outw", (byte) Wire.t2i(outw));
		compound.setByte("inw", (byte) Wire.t2i(inw));
		compound.setBoolean("ol", ol);
		compound.setBoolean("il", il);
		return super.writeToNBT(compound);
	}


}
