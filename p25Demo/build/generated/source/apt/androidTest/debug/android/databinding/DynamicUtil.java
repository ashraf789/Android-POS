package android.databinding;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.databinding.BindingConversion;
public class DynamicUtil {
    public static int safeUnbox(java.lang.Integer boxed) {
        return boxed == null ? 0 : (int)boxed;
    }
    public static long safeUnbox(java.lang.Long boxed) {
        return boxed == null ? 0L : (long)boxed;
    }
    public static short safeUnbox(java.lang.Short boxed) {
        return boxed == null ? 0 : (short)boxed;
    }
    public static byte safeUnbox(java.lang.Byte boxed) {
        return boxed == null ? 0 : (byte)boxed;
    }
    public static char safeUnbox(java.lang.Character boxed) {
        return boxed == null ? '\u0000' : (char)boxed;
    }
    public static double safeUnbox(java.lang.Double boxed) {
        return boxed == null ? 0.0 : (double)boxed;
    }
    public static float safeUnbox(java.lang.Float boxed) {
        return boxed == null ? 0f : (float)boxed;
    }
    public static boolean safeUnbox(java.lang.Boolean boxed) {
        return boxed == null ? false : (boolean)boxed;
    }
}