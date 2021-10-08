package com.sapient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.entity.Employee;
import com.sapient.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TestMvcRequests {

    @Autowired
    MockMvc mockMvc; // allows us to perform HTTP requests (like vs code extension REST CLIENT or
                     // Postman)

    @MockBean
    EmployeeService service;

    Employee e2;

    @BeforeEach
    void setup() {
        e2 = new Employee();
        e2.setId("asd123");
        e2.setName("John Keller");
        e2.setSalary(2500.);
        e2.setCity("Dallas");
        e2.setEmail("johnkeller@xmpl.com");

        List<Employee> emps = Arrays.asList(new Employee(), new Employee());

        Mockito.when(service.getEmployeeById("VALIDID")).thenReturn(new Employee());
        Mockito.when(service.getEmployeeById("INVALIDID")).thenReturn(null);
        Mockito.when(service.getEmployeeByEmail("johnkeller@xmpl.com")).thenReturn(e2);
        Mockito.when(service.getAll()).thenReturn(emps);
        Mockito.when(service.addNewEmployee(e2)).thenReturn(e2);
    }

    @ParameterizedTest
    @ValueSource(strings = { "/api/employees/VALIDID", "/api/employees?email=johnkeller@xmpl.com", "/api/employees" })
    void shouldGetStatusOkForValidId(String url) throws Exception {
        mockMvc.perform(get(url)).andExpect(status().isOk());
    }

    @Test
    void shouldStatusNotFoundForInvalidId() throws Exception {
        String url = "/api/employees/INVALIDID";
        mockMvc.perform(get(url)).andExpect(status().isNotFound());
    }

    void shouldAddEmployee() throws Exception {
        String url = "/api/employees";
        mockMvc.perform(post(url).content(asJsonString(e2)).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
