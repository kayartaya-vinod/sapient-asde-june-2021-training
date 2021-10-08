package main.java.com.sapient.asde.batch5.authservice;

import java.util.Base64;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.beans.factory.annotation.Value;



public class CipherUtil {

    private static String key;

    private static String initVector;

    static {
       
        ResourceBundle rb = ResourceBundle.getBundle("aes");
        key= rb.getString("cipherKey");
        initVector = rb.getString("cipherIv");
        
    }

    private static final String UTF8 = "UTF-8";
    

    public static String encrypt( String value) throws CipherException {

           try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            
            byte[] encrypted = cipher.doFinal(value.getBytes(UTF8));
            String encoded = Base64.getEncoder().encodeToString(encrypted);
            return encoded;
           } catch (Exception e) {
            throw new CipherException(e);
        }
    }
  
    public static String decrypt( String encrypted)  throws CipherException {
        try{

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        }
        catch(Exception e){
            throw new CipherException(e);
        }
    }

    

}