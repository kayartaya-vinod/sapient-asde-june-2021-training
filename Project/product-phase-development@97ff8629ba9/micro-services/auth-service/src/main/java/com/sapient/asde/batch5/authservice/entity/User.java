package com.sapient.asde.batch5.authservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "users")
public class User {
    @Id
    String id;
    String firstName;
    String lastName;
    String email;
    String password;
    String userType;
    Boolean isVerified;
    public User(){
    }
    public User(String id, String firstName,String lastName, String email, String password, String userType,Boolean isVerified) {
        this.id = id;
        this.lastName = lastName;
        this.firstName=firstName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.isVerified=isVerified;
    }
    @Override
    public String toString() {
        return "User [email=" + email + ", firstName=" + firstName + ", id=" + id + ", isVerified=" + isVerified
                + ", lastName=" + lastName + ", password=" + password + ", userType=" + userType + "]";
    }
   
    

   

}
