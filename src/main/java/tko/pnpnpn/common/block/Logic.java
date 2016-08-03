package tko.pnpnpn.common.block;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;

public class Logic
{
	public static List<TileLogicD> getLogicD(World world, BlockPos pos){
		List<TileLogicD> list = Lists.newLinkedList();
		S.getSurroundPos(pos).stream()
		.map(world::getTileEntity)
		.filter(o -> o instanceof TileLogicD)
		.forEach(tile -> list.add((TileLogicD) tile));
		return list;
	}
	public static List<ILogicProvider> getLogicProviers(World world, BlockPos pos, Set mem){
		List<ILogicProvider> list = Lists.newLinkedList();
		S.getSurroundPos(pos).stream()
		.filter(p -> (! mem.contains(p)))
		.peek(mem::add)
		.map(world::getTileEntity)
		.filter(ILogicProvider.instanceOf)
		.forEach(tile -> list.add((ILogicProvider) tile));
		return list;
	}
	
	public static List<ILogicable> getLogicables(World world, BlockPos pos, Set mem){
		List<ILogicable> list = Lists.newLinkedList();
		S.getSurroundPos(pos).stream()
		.filter(p -> (! mem.contains(p)))
		.peek(mem::add)
		.map(world::getTileEntity)
		.filter(ILogicable.instanceOf)
		.forEach(tile -> list.add((ILogicable) tile));
		return list;
	}
	
	public static boolean calculateLogic(ILogicable o, Wire wire, Set mem){
		if (o.canPassLogic(wire)) {
			wire = o.getWireType(); // !!
			mem.add(o.getPos());
			boolean b1 = Logic.getLogicByList(wire, Logic.getLogicProviers(o.getWorld(), o.getPos(), Sets.newHashSet(mem)));
			return b1 || Logic.calculateSurroundLogic(wire, mem, Logic.getLogicables(o.getWorld(), o.getPos(), Sets.newHashSet(mem)));
		} else {
			return false;
		}
	}
	
	public static boolean getLogicByList(Wire wire, List<ILogicProvider> list){
		if(list.isEmpty()){return false;}
		else {
			ILogicProvider first = list.remove(0);
			boolean b = first.getLogic(wire);
			return b || getLogicByList(wire, list);
		}
	}

	public static boolean calculateSurroundLogic(Wire wire, Set mem, List<ILogicable> list){
		if(list.isEmpty()){return false;}
		else{
			ILogicable first = list.remove(0);
			if(first.canPassLogic(wire)){
				boolean b1 = calculateLogic(first, wire, mem);
				return b1 || calculateSurroundLogic(wire, mem, list);
			}else return calculateSurroundLogic(wire, mem, list);
		}
	}
	
}
