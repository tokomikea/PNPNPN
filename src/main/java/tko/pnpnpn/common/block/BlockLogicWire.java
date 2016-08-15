package tko.pnpnpn.common.block;

import static tko.pnpnpn.common.block.BlockLogic.POWER;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.common.S;
import tko.pnpnpn.dywe.block.R;
import tko.pnpnpn.dywe.block.RY;
import tko.pnpnpn.dywe.block.Y;
import tko.pnpnpn.dywe.block.YR;

public class BlockLogicWire extends BlockLogic
{
    public final Wire wire;

    public BlockLogicWire(Wire wire)
    {
        super();
        this.wire = wire;
        this.setDefaultState(
                this.blockState
                .getBaseState()
                .withProperty(POWER, Boolean.FALSE));
    }

    
    
    
    
    
    @Override
    public void updateLogic(World worldIn, BlockPos pos)
    {
        Object tile = worldIn.getTileEntity(pos);
        if(tile instanceof ILogicable){
            boolean bool = ((ILogicable) tile).calculateLogic(wire);
            this.setDistinctState(worldIn, pos, bool);
        }
    }

    
    
    
    
    
    private void setDistinctState(World world, BlockPos pos, boolean value)
    {

        boolean b1 = shouldSetState(world, pos, value);
        if(b1){
            IBlockState state = getBlockState(world, pos, POWER, value);
            setBlockState(world, pos, state);
        }
    }

    
    
    
    
    
    private boolean shouldSetState(World world, BlockPos pos, boolean value)
    {
        IBlockState state = world.getBlockState(pos);
        if(value != state.getValue(POWER)){return true;
        }else return false;
    }

    
    
    
    
    
    private void setBlockState(World world, BlockPos pos, boolean value)
    {
        IBlockState state = world.getBlockState(pos);
        setBlockState(world, pos, state.withProperty(POWER, value));
    }

    
    
    
    
    
    private static IBlockState getBlockState(World world, BlockPos pos, IProperty p, boolean v)
    {
        return world.getBlockState(pos).withProperty(p, v);
    }

    
    
    
    
    
    private void setBlockState(World world, BlockPos pos, IBlockState state)
    {
        world.setBlockState(pos, state);
    }

    
    
    
    
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos,
            IBlockState state, EntityPlayer player, EnumHand hand,
            @Nullable ItemStack heldItem, EnumFacing side,
            float hitX, float hitY, float hitZ)
    {
        if(!player.isSneaking()){
            boolean b = (heldItem != null)? canPutOn(world, pos, heldItem, state) : false;
            if(b){
                if(world.isRemote){
                    return true;
                }else{
                    boolean bool = state.getValue(POWER);
                    BlockLogic after = putOn(world, pos, bool);
                    if(!player.isCreative())heldItem.stackSize--;
                    after.updateLogic(world, pos);
                    return true;
                }
            }
        }
        return false;
    }

    
    
    
    
    
    private boolean canPutOn(World world, BlockPos pos, ItemStack heldItem, IBlockState state)
    {
        return  (wire == Wire.P
                    && heldItem.getItem().equals(Item.getItemFromBlock(R.redLogicWire)))
                || (wire == Wire.N
                    && heldItem.getItem().equals(Item.getItemFromBlock(Y.yellowLogicWire)));
    }
	
    
    
    
    
    
    private BlockLogic putOn(World world, BlockPos pos, boolean value)
    {
        IBlockState state = (wire == Wire.P) ? RY.ry.getDefaultState() : YR.yr.getDefaultState();
        world.setBlockState(pos, state.withProperty(BlockLogicD.INSIDE, value));
        return ((BlockLogic) state.getBlock());
    }


















    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        IBlockState state = this.getStateFromMeta(meta);
        boolean b1 = state.getValue(POWER);
        return (new TileLogicWire(wire, b1));
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return (new BlockStateContainer(
                    this, (new IProperty[] { POWER })));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(POWER, Boolean.valueOf(meta == 1));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((state.getValue(POWER) ? 1 : 0));
    }
}
