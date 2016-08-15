package tko.pnpnpn.common.block;

import static tko.pnpnpn.common.block.BlockLogicD.OUTSIDE;

import java.util.Random;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tko.pnpnpn.PNPNPN;
import tko.pnpnpn.dywe.block.R;
import tko.pnpnpn.dywe.block.Y;

public class BlockLogicD extends BlockLogic
    implements ITileEntityProvider
{
    public static final PropertyBool INSIDE = PropertyBool.create("inside");
    public static final PropertyBool OUTSIDE = PropertyBool.create("outside");

    public final Wire outWire;
    public final Wire inWire;

    public BlockLogicD(BlockLogicWire block)
    {
        super();

        this.outWire = block.wire;
        this.inWire = Wire.oopp(block.wire);

        this.setDefaultState(
                getDefaultState()
                .withProperty(INSIDE, Boolean.FALSE)
                .withProperty(OUTSIDE, Boolean.FALSE));
    }

    private static final int DELAY = 4;

    @Override
    void updateLogic(World world, BlockPos pos)
    {
        Object tile = world.getTileEntity(pos);
        if (tile instanceof ILogicable){
            if (!world.isUpdateScheduled(pos, this)){
                world.updateBlockTick(pos, this, 0, -1);
            }
        }
    }

    
    
    
    
    private boolean isInsideEN(World world, BlockPos pos, Wire in)
    {
        if (in == Wire.P){
            return (!world.getBlockState(pos).getValue(OUTSIDE));
        }else{
            return (world.getBlockState(pos).getValue(OUTSIDE));
        }
    }

    
    
    
    
    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
    {
        Object tile = world.getTileEntity(pos);
        if (tile instanceof ILogicable){
            boolean o = state.getValue(OUTSIDE);
            boolean b1 = ((ILogicable) tile).calculateLogic(outWire);
            boolean b2 = ((ILogicable) tile).calculateLogic(inWire);

            if (shouldUpdate(o, b1)){
                world.setBlockState(pos, state.withProperty(OUTSIDE, b1), 2);
                world.updateBlockTick(pos, this, DELAY, -2);
            }
            
            boolean b3 = (inWire == Wire.P && !o && b2)
                            || (inWire == Wire.N && o && b2);
            toSetBlockState(world, pos, INSIDE, b3);
        }
    }

    
    
    
    
    private void toSetBlockState(World world, BlockPos pos, IProperty p, boolean value)
    {
        if (shouldUpdate(world, pos, p, value)){
            setBlockState(world, pos, p, value);
        }
    }

    
    
    
    
    private boolean shouldUpdate(World world, BlockPos pos, IProperty<Boolean> p, boolean value)
    {
        IBlockState state = world.getBlockState(pos);
        return shouldUpdate(state, p, value);
    }

    private boolean shouldUpdate(IBlockState state, IProperty<Boolean> p, boolean value)
    {
        boolean last = state.getValue(p);
        return shouldUpdate(last, value);
    }

    private boolean shouldUpdate(boolean last, boolean now)
    {
        return now != last;
    }

    
    
    
    
    private void toggleLogic(World world, BlockPos pos, IProperty<Boolean> p)
    {
        IBlockState state = world.getBlockState(pos);
        setBlockState(world, pos, p, state.cycleProperty(p).getValue(p));
    }

    
    
    
    
    private void setBlockState(World world, BlockPos pos, IProperty<Boolean> p, boolean value)
    {
        IBlockState state = world.getBlockState(pos);
        world.setBlockState(pos, state.withProperty(p, Boolean.valueOf(value)));
    }

    
    
    
    
    @Override
    public boolean onBlockActivated(World world, BlockPos pos,
            IBlockState state, EntityPlayer player, EnumHand hand,
            @Nullable ItemStack heldItem, EnumFacing side,
            float hitX, float hitY, float hitZ)
    {
        if(player.isSneaking()){
            if(!world.isRemote){
                takeOff(world, pos, ((ILogicable)world.getTileEntity(pos)).calculateLogic(inWire));
                if(!player.isCreative())spawnOutside(world, pos.offset(side));
            }
            return true;
        }else
            return false;
    }
    
    
    
    
    
    private void takeOff(World world, BlockPos pos, boolean value)
    {
        IBlockState s1 = getInsideState(value);
        world.setBlockState(pos, s1);
    }

    
    
    
    
    private void spawnOutside(World world, BlockPos where)
    {
        IBlockState b1 = getOutsideBlock().getDefaultState();
        spawnItemStack(world, where, b1);
    }

    
    
    
    
    private IBlockState getInsideState(boolean value)
    {
        return getInsideBlock().getDefaultState().withProperty(POWER, value);
    }
    
    
    
    
    
    private Block getOutsideBlock()
    {
        Predicate p1 = (o -> o == outWire);
        return f1(p1);
    }
    
    
    
    
    
    private Block getInsideBlock()
    {
        Predicate p1 = (o -> o == inWire);
        return f1(p1);
    }
    
    
    
    
    
    private Block f1(Predicate p)
    {
        if(p.test(Wire.N))return R.redLogicWire;
        else return Y.yellowLogicWire;
    }

    
    
    
    
    private void spawnItemStack(World world, BlockPos offset, IBlockState state)
    {
        ItemStack stack = createStackedBlock(state);
        spawnAsEntity(world, offset, stack);
    }
















    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        IBlockState state = this.getStateFromMeta(meta);
        boolean o = state.getValue(OUTSIDE);
        boolean i = state.getValue(INSIDE);
        return (new TileLogicD(outWire, o, i));
    }
	
    @Override
    protected BlockStateContainer createBlockState()
    {
        return (new BlockStateContainer(
                    this, (new IProperty[] { INSIDE, OUTSIDE })));
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState()
                    .withProperty(INSIDE, Boolean.valueOf((meta & 1) > 0))
                    .withProperty(OUTSIDE, Boolean.valueOf((meta & 2) > 0));
    }
    
    @Override
    public int getMetaFromState(IBlockState state)
    {
        int meta = 0;
        meta |= state.getValue(INSIDE) ? 1 : 0;
        meta |= state.getValue(OUTSIDE) ? 2 : 0;
        return meta;
    }
}
