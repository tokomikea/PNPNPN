package tko.pnpnpn.dywe;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraftforge.common.MinecraftForge;

public class DYWEManager
{
//	public static final Map<String, DYWE> FEATURES = Maps.newHashMap();
	public static final List<DYWE> FEATURES = Lists.newArrayList();
	
	public static DYWE register(DYWE feature, String Id) {
		FEATURES.add(feature);
		
		if (feature.usesEvents()) {
			MinecraftForge.EVENT_BUS.register(feature);
		}
		
		return feature;
	}
}
