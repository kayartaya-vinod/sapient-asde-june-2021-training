package com.sapient.asde.batch5.customerservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapient.asde.batch5.customerservice.entity.Address;
import com.sapient.asde.batch5.customerservice.entity.AddressFields;
import com.sapient.asde.batch5.customerservice.entity.Customer;

public final class Utils {

  /**
   * Customer Object Creator
   * Returns {@Customer}
   * @param idRequired to get the customer with id or without id
   */
  public static Customer getCustomerObject(Boolean idRequired) {
    List<String> wishlist = new ArrayList<>(); 
    wishlist.add("car1");
    wishlist.add("car2");
    
    Customer customer = new Customer();
    Address address = new Address();
    AddressFields primaryFields = new AddressFields();
    AddressFields secondaryFields = new AddressFields();
    primaryFields.setBuildingNo("p-buildingNo");
    primaryFields.setStreet("p-street");
    primaryFields.setLandMark("p-landMark");
    primaryFields.setCity("p-city");
    primaryFields.setState("p-state");
    primaryFields.setPinCode("p-pinCode");
    secondaryFields.setBuildingNo("s-buildingNo");
    secondaryFields.setStreet("s-street");
    secondaryFields.setLandMark("s-landMark");
    secondaryFields.setCity("s-city");
    secondaryFields.setState("s-state");
    secondaryFields.setPinCode("s-pinCode");
    address.setPrimary(primaryFields);
    address.setAnotherAddress(secondaryFields);
    
    if(idRequired){
      customer.setId("1");
    }
    customer.setUserId("u1");
    customer.setContactNo("1111111111");
    customer.setAlternateContactNo("2222222222");
    customer.setAlternateEmail("abc@xmpl.com");
    customer.setWishlist(wishlist);
    customer.setAddress(address);
    return customer;
  }


  /**
   * Customer creator to be send as content in request
   * Returns {@Customer}
   * @param idRequired to get the customer with id or without id
   */
  public static Map<String, Object> getCustomer(Boolean idRequired){
    Map<String, Object> customer = new HashMap<>();
    List<String> wishlist = new ArrayList<>(); 
    wishlist.add("car1");
    wishlist.add("car2");
    
    Map<String, Object> primaryAddressFields = new HashMap<>();
    primaryAddressFields.put("buildingNo", "p-buildingNo");
    primaryAddressFields.put("street","p-street");
    primaryAddressFields.put("landMark","p-landMark");
    primaryAddressFields.put("city","p-city");
    primaryAddressFields.put("state","p-state");
    primaryAddressFields.put("pinCode","p-pinCode");
    Map<String, Object> secondaryAddressFields = new HashMap<>();
    secondaryAddressFields.put("buildingNo", "s-buildingNo");
    secondaryAddressFields.put("street","s-street");
    secondaryAddressFields.put("landMark","s-landMark");
    secondaryAddressFields.put("city","s-city");
    secondaryAddressFields.put("state","s-state");
    secondaryAddressFields.put("pinCode","s-pinCode");
    Map<String, Object> address = new HashMap<>();
    address.put("primary", primaryAddressFields);
    address.put("anotherAddress", secondaryAddressFields);
    
    if(idRequired){
      customer.put("id", "1");
    }
    customer.put("userId", "u1");
    customer.put("contactNo", "1111111111");
    customer.put("alternateContactNo","2222222222");
    customer.put("alternateEmail","abc@xmpl.com");
    customer.put("wishlist", wishlist);
    customer.put("address", address);
    return customer;
  }

  /**
   * Function to get the claims to mock the filter
   * Returns {@Map<String, Object>}
   */
  public static Map<String, Object> getClaims(){
    Map<String, Object> claims = new HashMap<>();
    claims.put("firstName", "Name");
    claims.put("userType", "customer");
    claims.put("exp", "324323");
    claims.put("userId", "id");
    claims.put("iat", "234234234");
    claims.put("email", "email");
    return claims;
  }
}
