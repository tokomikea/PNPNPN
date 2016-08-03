package tko.pnpnpn.common.block;

import static tko.pnpnpn.common.block.BlockLogicWire.POWER;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockVCC extends Block
	implements ITileEntityProvider
{

	public BlockVCC()
	{
		super(Material.IRON);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return (new TileVCC());
	}

	
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
		if (worldIn.isRemote) return;
		
		TileEntity tile = worldIn.getTileEntity(pos);
		if(tile instanceof TileVCC)
		{
			((TileVCC) tile).setPowered(worldIn.isBlockIndirectlyGettingPowered(pos) > 0);
		}
    }
}
