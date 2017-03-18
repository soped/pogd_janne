package dk.mudlogic.crypto;

/**
 * Created by soren.pedersen on 26-02-2017.
 */
public class Utils {

    public Utils() {
    }

    public String toHexString(byte[] b) {
        return org.bouncycastle.util.encoders.Hex.toHexString(b);
    }

    public byte[] copyBytes(byte[] b,int start,int end) {
        return org.bouncycastle.util.Arrays.copyOfRange(b,start,end);
    }

}
