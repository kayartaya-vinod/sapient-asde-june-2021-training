package com.sapient.asde.batch5.vehicledataservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfContact {
    private String firstName;
    private String lastName;
    private String designation;
    private String email;
    private String alternateEmail;
    private String contactNo;
    private String alternateContactNo;
    private String aadharNo;
}
