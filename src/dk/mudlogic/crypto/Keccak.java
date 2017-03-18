package dk.mudlogic.crypto;

import dk.mudlogic.tools.log.LogFactory;
import dk.mudlogic.tools.log.LogTracer;
import org.bouncycastle.jcajce.provider.digest.Keccak.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by soren.pedersen on 24-02-2017.
 */
public class Keccak {

    private LogTracer log = new LogFactory().tracer();

    private byte[] final_state;

    private DigestKeccak keccak;

    public Keccak(int size) {
        log.setTracerTitle(Keccak.class.getSimpleName() + " " + size);
        log.info("Init Keccak");

        keccak = new DigestKeccak(size);
    }

    /** String input for Keccak to digest
     *
     * @param s String
     */
    public void input(String s) {
        log.info("Digest: " + s);

        try {
            keccak.update(s.getBytes("UTF-8"));
            final_state = keccak.digest();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String hex_finalState() {
        return new Utils().toHexString(final_state);
    }

    public byte[] byte_finalState() {
        return final_state;
    }

}
