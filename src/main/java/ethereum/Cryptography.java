package ethereum;



import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64; 
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;





public class Cryptography {
}




//*******************************************************************************************************
class AES {	 
    private static SecretKeySpec secretKey;
    private static byte[] key; 
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String strToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String strToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return null;
    }
}
//*******************************************************************************************************
class Twofish {    
    public static byte[] encrypt(String toEncrypt, String key) throws Exception {
     // create a binary key from the argument key (seed)
     SecureRandom sr = new SecureRandom(key.getBytes());
     
     KeyGenerator kg = javax.crypto.KeyGenerator.getInstance("twofish");
     kg.init(sr);
     SecretKey sk = kg.generateKey();
 
     // create an instance of cipher
     Cipher cipher = Cipher.getInstance("twofish");
 
     // initialize the cipher with the key
     cipher.init(Cipher.ENCRYPT_MODE, sk);
 
     // enctypt!
     byte[] encrypted = cipher.doFinal(toEncrypt.getBytes());
 
     return encrypted;
  }    
  public static String decrypt(byte[] toDecrypt, String key) throws Exception {
     // create a binary key from the argument key (seed)
     SecureRandom sr = new SecureRandom(key.getBytes());
     KeyGenerator kg = javax.crypto.KeyGenerator.getInstance("twofish");
     kg.init(sr);
     SecretKey sk = kg.generateKey();
 
     // do the decryption with that key
     Cipher cipher = Cipher.getInstance("twofish");
     cipher.init(Cipher.DECRYPT_MODE, sk);
     byte[] decrypted = cipher.doFinal(toDecrypt);
 
     return new String(decrypted);
  }
}
//*******************************************************************************************************

