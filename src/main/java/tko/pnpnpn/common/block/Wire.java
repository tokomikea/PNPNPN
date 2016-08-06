package tko.pnpnpn.common.block;

import net.minecraft.util.IStringSerializable;

public enum Wire
    implements IStringSerializable
{
    N,
    P,
    M,
    D;

    @Override
    public String getName()
    {
        return this.toString();
    }

    public static Wire i2t(int i)
    {
        return Wire.values()[i];
    }

    public static int t2i(Wire o)
    {
        int i = 0;
        for(Wire p : Wire.values()){
            if(p == o)
                return i;

            i++;
        }
        return i;
    }

    public static Wire oopp(Wire o)
    {
        switch(o){
            case M:
                return M;
            case N:
                return P;
            case P:
                return N;
            case D:
                return D;
            default:
                break;
        }
        return o;
    }
}
