package tko.pnpnpn.common.block;

import java.util.Set;
import java.util.function.Predicate;

public interface ILogicProvider
{
	public static final Predicate instanceOf = o -> o instanceof ILogicProvider; 
	boolean getLogic(Wire wire);
	

}
