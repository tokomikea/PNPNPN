package tko.pnpnpn.common.block;

import static tko.pnpnpn.common.block.BlockLogic.POWER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;

public class TileMetal extends TileLogicWire
	implements ILogicable
{
	private boolean logic;
	
	public TileMetal()
	{
		super();
	}
	
	public TileMetal(Wire wire, boolean l)
	{
		super(wire, l);
	}
	
	
	@Override public boolean canPassLogic(Wire wire){return true;}

	@Override public Wire getWireType(){return Wire.M;}

	@Override
	public boolean getValue(Wire wire)
	{
		return getLogic();
	}
	
	private boolean getLogic()
	{
		return worldObj.getBlockState(pos).getValue(BlockLogic.POWER);
	}

//	@Override
//	public boolean calculateLogic(Wire wire)
//	{
//		return this.calculateLogic(wire, (new HashSet()));
//	}
	
//	@Override
//	public boolean calculateLogic(Wire wire, Set mem)
//	{
//		return Logic.calculateLogic(this, Wire.M, (new HashSet()));
//	}
	
	@Override
	public void setValue(Wire wire, boolean v, Set mem)
	{
		if (wire == Wire.M) {
			IBlockState state = worldObj.getBlockState(pos);
			worldObj.setBlockState(pos, state.withProperty(POWER, v));
			
			List<ILogicable> list = Logic.getLogicables(worldObj, pos, mem);
			for(ILogicable tile : list) {
				tile.setValue(Wire.M, v, mem);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void readFromNBT(NBTTagCompound compound)
//	{
//		super.readFromNBT(compound);
//		this.logic = compound.getBoolean("logic_value");
//	}
//
//	@Override
//	public NBTTagCompound writeToNBT(NBTTagCompound compound)
//	{
//		compound.setBoolean("logic_value", this.logic);
//		return super.writeToNBT(compound);
//	}
}
