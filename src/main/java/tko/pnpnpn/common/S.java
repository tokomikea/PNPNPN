package tko.pnpnpn.common;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.property.IExtendedBlockState;
import tko.pnpnpn.PNPNPN;

public class S
{
    public static <T> List<T> filter(List<T> list, Predicate<T> p){
        int size = list.size();
        List<T> l = Lists.newArrayListWithCapacity(size);
        for (T t : list){
            if (p.test(t))l.add(t);
        }
        return l;
    }

    public static <U, T> List<T> map(List<U> list, Function<U, T> f){
        int size = list.size();
        List<T> l = Lists.newArrayListWithCapacity(size);
        for (U o : list){
            l.add(f.apply(o));
        }
        return l;
    }

    public static List<BlockPos> getSurroundPos(BlockPos pos){
        List list = Lists.<BlockPos> newLinkedList();
        for (EnumFacing f : EnumFacing.VALUES){
            list.add(pos.offset(f));
        }
        return list;
    }

    public static IExtendedBlockState getExtendedState(World world, BlockPos pos){
        return getExtendedState(world.getBlockState(pos));
    }

    public static IExtendedBlockState getExtendedState(IBlockState state){
        return ((IExtendedBlockState) state);
    }

    public static <T> void when(Predicate p, Class<T> c, Object o, Consumer<T> w){
        if (p.test(o)){
            w.accept(c.cast(o));
        }
    }

    public static Item getUnRegisterItemBlock(Block block){
        return (new ItemBlock(block)).setRegistryName(block.getRegistryName());
    }

    public static void renderBlock(Block block)
    {
        ModelLoader.setCustomModelResourceLocation(
                Item.getItemFromBlock(block),
                0,
                new ModelResourceLocation(
                        PNPNPN.MODID + ":" + block.getUnlocalizedName().substring(5),
                        "inventory"));
    }

}
