package components;

import java.lang.reflect.Array;
import java.util.List;

public abstract class Coefs {

    public static short[] getCoefficients() {
        return new short[]{
                -395, -54, 984, 485, -2112, -1833, 5390, 13903, 13903,
                5390, -1833, -2112, 485, 984, -54, -395
        };
    }

}
