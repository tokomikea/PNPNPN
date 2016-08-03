package tko.pnpnpn.common.block;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;

public class BlockMetal extends BlockLogic
	implements ITileEntityProvider
{
	public BlockMetal()
	{
		super();
		this.setDefaultState(blockState.getBaseState()
								.withProperty(POWER, false));
	}
	
	@Override
	public void updateLogic(World worldIn, BlockPos pos)
	{
		Object tile = worldIn.getTileEntity(pos);
		if(tile instanceof ILogicable) {
			boolean	bool = ((ILogicable) tile).calculateLogic(Wire.M);
			this.ToSetBlockState(worldIn, pos, bool);
		}		
	}
	
	private void ToSetBlockState(World world, BlockPos pos, boolean value)
	{
//		TileEntity tile = world.getTileEntity(pos);
		IBlockState state = world.getBlockState(pos);
		if(value != state.getValue(POWER))
		{
			world.setBlockState(pos, state.withProperty(POWER, value));
//			world.setTileEntity(pos, tile);
		}
	}
	
	
	
	
	
	
	
	

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		IBlockState state = this.getStateFromMeta(meta);
		boolean l = state.getValue(POWER);
		return (new TileMetal(Wire.M, l));
	}

	@Override
	protected BlockStateContainer createBlockState()
	{
		return (new BlockStateContainer
					(this, (new IProperty[] {POWER})));
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(POWER, Boolean.valueOf(meta == 1));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((state.getValue(POWER)? 1:0));
	}
	

}
