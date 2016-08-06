package tko.pnpnpn.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tko.pnpnpn.common.S;

public abstract class BlockLogic extends Block
    implements ITileEntityProvider
{
    public static final PropertyBool POWER = PropertyBool.create("power");
    public static final PropertyEnum<Wire> WIRE = PropertyEnum.create("logic_type", Wire.class);

    public BlockLogic()
    {
        super(Material.IRON);
    }

    @Override abstract public TileEntity createNewTileEntity(World worldIn, int meta);

    @Override abstract protected BlockStateContainer createBlockState();

    @Override abstract public IBlockState getStateFromMeta(int meta);

    @Override abstract public int getMetaFromState(IBlockState state);

    abstract void updateLogic(World worldIn, BlockPos pos);

    @Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn)
    {
        if (!worldIn.isRemote)
            updateLogic(worldIn, pos);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        if (!worldIn.isRemote)
            updateLogic(worldIn, pos);
    }

}
