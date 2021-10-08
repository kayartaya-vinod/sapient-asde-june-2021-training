package com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1445;

import static org.junit.jupiter.api.Assertions.assertNotNull;


import com.sapient.asde.batch5.vehicleservice.repository.ReviewRepository;
import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest

class ReviewServiceTest {
    @Autowired
    ReviewService service;

    @MockBean
    ReviewRepository repo;

    @BeforeEach
    void setup() throws ServiceException {
        String[][] newArray = new String[1][5]; 
        newArray[0][0] = "good car" ;
        newArray[0][1] = "zing";
       
         newArray[0][2] = "5" ;
       
         
        newArray[0][3] = "123";
        newArray[0][4] = "123456";
      

        Mockito.when(repo.getArray("123"))
                .thenReturn(newArray);
    }
    @Test
    void shouldReturnSearchedListOfVehicles() throws ServiceException {
       
        assertNotNull(service.getfeedbacks("123"));
    }
}
