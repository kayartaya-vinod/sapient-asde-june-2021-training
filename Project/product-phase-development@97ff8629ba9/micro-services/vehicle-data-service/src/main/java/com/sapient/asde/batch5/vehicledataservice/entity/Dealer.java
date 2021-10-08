package com.sapient.asde.batch5.vehicledataservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "#{@environment.getProperty('spring.data.mongodb.dealerCollection')}")
public class Dealer {
    
    @Id
    private String id;
    private String userId;
    private String organizationName;
    private String description;
    private String organizatonEmail;
    private String businessContactWithStd;
    private List<PointOfContact> pocs;
    private String salesTaxNo;
    private String salesLicenseNo;
    private String experience;
    private HeadOfficeAddress headOfficeAddress;
    private String gstIN;
    private String salesPanNo;
    private Location location;
    private List<String> sellingBrands;
    private List<String> pictureUrls;
    private List<Branch> branches;
    private SocialMedia socialMedia;
    private String typeOfCompany;
    private Boolean emiFacility;
    private List<String> awardsAndRecognition;

}
