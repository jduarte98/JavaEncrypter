package joaoduarte.samples.javaencrypter;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author https://howtodoinjava.com/java/java-security/java-aes-encryption-example/
 * Last Access: 11-Apr-2022
 */
public class Encryption {
    private static SecretKeySpec secretKey;
    private static byte[] key;

    /**
     * Set Keys
     * @param myKey String
     */
    private static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            System.out.println("       !!! Cannot set Key !!!");
        }
        catch (UnsupportedEncodingException e) {
            System.out.println("       !!! Cannot set Key !!!");
        }
    }

    /**
     * Encrypt String
     * @param strToEncrypt String to be Encrypted
     * @param secret Salt Key
     * @return Encrypted String
     */
    public static String encrypt(String strToEncrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e) {
            System.out.println("       !!! Cannot Encrypt this String !!!");
        }
        return null;
    }

    /**
     * Decript String
     * @param strToDecrypt String to be Decrypted
     * @param secret Salt Key
     * @return Decrypted String
     */
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        }
        catch (Exception e) {
            System.out.println("       !!! Cannot Decrypt this String !!!");
        }
        return null;
    }
}