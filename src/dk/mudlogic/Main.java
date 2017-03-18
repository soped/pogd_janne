package dk.mudlogic;

import dk.mudlogic.crypto.AES_Enc;
import dk.mudlogic.crypto.Keccak;
import dk.mudlogic.crypto.Utils;
import dk.mudlogic.tools.log.LogFactory;
import dk.mudlogic.tools.log.LogTracer;

/**
 * Nu er vi to om det her skidt.
 *
 * Her en en ændring
 */

public class Main {

    public static void main(String[] args) {
        LogTracer log = new LogFactory(Main.class.toString()).tracer();


        //String subscribe = "{ \"method\": \"login\", \"params\": {\"login\":\"43RLpGhLhE5ZXGVQ8b9wFoJWTMBoyN1M4XVtWLK4hcuB2HrGJSWuRWuWbwaVe4vUMveKAzAiA4j8xgUi29TpKXpm3yigw23\",\"pass\":\"x\"},\"id\":1}";

        //Connector conn = new Connector("monerohash.com",3333);
        //conn.write(subscribe);
        //conn.close();

        /*
        try {
            DigestKeccak md = new DigestKeccak(512);
            md.update("secret".getBytes("UTF-8"));
            byte[] b = md.digest();
            byte[] b2 = org.bouncycastle.util.Arrays.copyOfRange(b,0,31);


            log.info("Lenght: " + md.getDigestLength());
            log.info("Lenght of copy: " + b2.length);
            log.info("HEX: " + org.bouncycastle.util.encoders.Hex.toHexString(b) );

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        */


        Keccak k = new Keccak(512);
        //Input string to encrypt
        k.input("secret");
        log.info("HEX: " + k.hex_finalState() );

        byte[] keccak_byte0_31 = new Utils().copyBytes(k.byte_finalState(),0,31);
        log.info("Keccak byte 0-31: " + keccak_byte0_31.length);

        //bit 0-31 of keccak final state is encrypted using AES 128bit encryption
        //and becomes keccak_aes_block1
        byte[] keccak_aes_block1 = new AES_Enc().aes(128, new Utils().toHexString(keccak_byte0_31));

        //hex031_1 is encrypted using AES 128bit encryption
        //and becomes keccak_aes_block2
        byte[] keccak_aes_block2 = new AES_Enc().aes(128, new Utils().toHexString(keccak_aes_block1));

        //The encrypted keccak_aes_block1 and the double-encrypted keccak_aes_bloack2 are concatenated
        //And becomes keccak_aes_256
        String keccak_aes_256 = new Utils().toHexString(keccak_aes_block1) + new Utils().toHexString(keccak_aes_block2);
        log.info(new Utils().toHexString(keccak_aes_block1));
        log.info(new Utils().toHexString(keccak_aes_block2));
        log.info(keccak_aes_256);
        log.info("keccak final state.length: "+keccak_byte0_31.length);
        log.info("keccak__aes_block1.length: "+keccak_aes_block1.length);
        log.info("keccak_aes_block2.length: "+keccak_aes_block2.length);
        log.info("keccak_aes_256.length: "+keccak_aes_256.getBytes().length);
    }
}
