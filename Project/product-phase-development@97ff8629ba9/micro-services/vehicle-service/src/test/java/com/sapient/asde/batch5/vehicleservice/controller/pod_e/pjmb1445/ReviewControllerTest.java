package com.sapient.asde.batch5.vehicleservice.controller.pod_e.pjmb1445;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;
import com.sapient.asde.batch5.vehicleservice.service.pod_e.pjmb1445.ReviewService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ReviewService service;
    
    @BeforeEach
    void setup() throws ServiceException {
        
        String[][] newArray = new String[1][5]; 
        newArray[0][0] = "good car" ;
        newArray[0][1] = "zing";
       
         newArray[0][2] = "5" ;
       
         
        newArray[0][3] = "123";
        newArray[0][4] = "123456";
        Mockito.when(service.getfeedbacks("123456")).thenReturn(newArray);
        Mockito.when(service.getfeedbacks("")).thenThrow(new ServiceException("NO TEXT ENTERED", HttpStatus.NO_CONTENT));
    }
    @Test
    void testReviewController() throws Exception
    {
        String url="/api/vehicles/reviews?q=123456";
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }
    @Test
    void testReviewControllerfornotext() throws Exception
    {
        
            String url = "/api/vehicles/reviews?q=";
            mockMvc.perform(get(url)).andExpect(status().isNoContent());
    }
}
