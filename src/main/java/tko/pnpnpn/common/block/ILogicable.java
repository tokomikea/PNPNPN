package tko.pnpnpn.common.block;

import java.util.Set;
import java.util.function.Predicate;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public interface ILogicable
{
	public static final Predicate instanceOf = o -> o instanceof ILogicable;
	
	boolean canPassLogic(Wire wire);
	
	boolean calculateLogic(Wire wire);
	
	Wire getWireType();
	
	void setValue(Wire wire, boolean v, Set mem);
	
	boolean getValue(Wire wire);
	
	IBlockAccess getBlockAccess();
	
	BlockPos getBlockPos();
	
}
