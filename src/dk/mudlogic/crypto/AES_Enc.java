package dk.mudlogic.crypto;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by soren.pedersen on 18-03-2017.
 */
public class AES_Enc {

    public byte[] aes(int size, String input) {

        //String FileName = "encryptedtext.txt";
        //String FileName2 = "decryptedtext.txt";

        KeyGenerator KeyGen = null;
        try {
            KeyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //KeyGen.init(128);
        KeyGen.init(size);

        SecretKey SecKey = KeyGen.generateKey();

        Cipher AesCipher = null;

        try {
            AesCipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }


        //byte[] byteText = "Your Plain Text Here".getBytes();
        byte[] byteText = input.getBytes();

        try {
            AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] byteCipherText = new byte[0];

        try {
            byteCipherText = AesCipher.doFinal(byteText);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        //Files.write(Paths.get(FileName), byteCipherText);

        byte[] cipherText = byteCipherText;

        try {
            AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            byte[] bytePlainText = AesCipher.doFinal(cipherText);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        //Files.write(Paths.get(FileName2), bytePlainText);

        return byteCipherText;
    }

    public byte[] aes__(int size, byte[] input) {

        //String FileName = "encryptedtext.txt";
        //String FileName2 = "decryptedtext.txt";

        KeyGenerator KeyGen = null;
        try {
            KeyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //KeyGen.init(128);
        KeyGen.init(size);

        SecretKey SecKey = KeyGen.generateKey();

        Cipher AesCipher = null;

        try {
            AesCipher = Cipher.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }


        //byte[] byteText = "Your Plain Text Here".getBytes();
        byte[] byteText = input;

        try {
            AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] byteCipherText = new byte[0];

        try {
            byteCipherText = AesCipher.doFinal(byteText);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        //Files.write(Paths.get(FileName), byteCipherText);

        byte[] cipherText = byteCipherText;

        try {
            AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        try {
            byte[] bytePlainText = AesCipher.doFinal(cipherText);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        //Files.write(Paths.get(FileName2), bytePlainText);

        return byteCipherText;
    }

}
