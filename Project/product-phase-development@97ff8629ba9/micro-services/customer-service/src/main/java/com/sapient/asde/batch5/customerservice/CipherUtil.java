package com.sapient.asde.batch5.customerservice;

import java.util.Base64;
import java.util.ResourceBundle;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sapient.asde.batch5.customerservice.entity.Address;
import com.sapient.asde.batch5.customerservice.entity.AddressFields;
import com.sapient.asde.batch5.customerservice.entity.Customer;

public class CipherUtil {

   
    
    private static String key;

    private static String initVector;

    static {
        
        ResourceBundle rb = ResourceBundle.getBundle("aes");
        key= rb.getString("cipherKey");
        initVector = rb.getString("cipherIv");
        
    }
    private static final String UTF8 = "UTF-8";

    public static String encrypt(String value) throws CipherException {

        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
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

    public static String decrypt(String encrypted) throws CipherException {
        try {

            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(UTF8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(UTF8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));
            return new String(original);
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static void encryptCustomer(Customer customer) throws CipherException {

        try {
            customer.setContactNo(CipherUtil.encrypt(customer.getContactNo()));
            customer.setAlternateEmail(CipherUtil.encrypt(customer.getAlternateEmail()));
            customer.setAlternateContactNo(CipherUtil.encrypt(customer.getAlternateContactNo()));
            customer.setAddress(CipherUtil.encryptAddress(customer.getAddress()));

        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static void decryptCustomer(Customer customer) throws CipherException {
        try {
           customer.setContactNo(CipherUtil.decrypt(customer.getContactNo()));
           customer.setAlternateEmail(CipherUtil.decrypt(customer.getAlternateEmail()));
           customer.setAlternateContactNo(CipherUtil.decrypt(customer.getAlternateContactNo()));
           customer.setAddress(CipherUtil.decryptAddress(customer.getAddress()));
       } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static Address encryptAddress(Address address) throws CipherException {
        try {

            AddressFields primary = address.getPrimary();
            AddressFields alternate = address.getAnotherAddress();

            address.setPrimary(encryptAddressFields(primary));

            address.setAnotherAddress(encryptAddressFields(alternate));
            
            return address;
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static AddressFields encryptAddressFields(AddressFields primary) throws CipherException {
        try {
            primary.setBuildingNo(CipherUtil.encrypt(primary.getBuildingNo()));
            primary.setCity(CipherUtil.encrypt(primary.getCity()));
            primary.setLandMark(CipherUtil.encrypt(primary.getLandMark()));
            primary.setPinCode(CipherUtil.encrypt(primary.getPinCode()));
            primary.setState(CipherUtil.encrypt(primary.getState()));
            primary.setStreet(CipherUtil.encrypt(primary.getStreet()));

            return primary;
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static Address decryptAddress(Address address) throws CipherException {
        try {

            AddressFields primary = address.getPrimary();
            AddressFields alternate = address.getAnotherAddress();

            address.setPrimary(decryptAddressFields(primary));

            address.setAnotherAddress(decryptAddressFields(alternate));
            
            return address;
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    public static AddressFields decryptAddressFields(AddressFields primary) throws CipherException {
        try {
            primary.setBuildingNo(CipherUtil.decrypt(primary.getBuildingNo()));
            primary.setCity(CipherUtil.decrypt(primary.getCity()));
            primary.setLandMark(CipherUtil.decrypt(primary.getLandMark()));
            primary.setPinCode(CipherUtil.decrypt(primary.getPinCode()));
            primary.setState(CipherUtil.decrypt(primary.getState()));
            primary.setStreet(CipherUtil.decrypt(primary.getStreet()));

            return primary;
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    
}