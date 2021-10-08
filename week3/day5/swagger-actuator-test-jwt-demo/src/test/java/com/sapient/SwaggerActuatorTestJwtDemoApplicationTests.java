package com.sapient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.sapient.dao.EmployeeDao;
import com.sapient.entity.Employee;
import com.sapient.service.EmployeeService;
import com.sapient.service.ServiceException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class SwaggerActuatorTestJwtDemoApplicationTests {

	@Autowired
	EmployeeService service;

	@MockBean
	EmployeeDao dao;

	Employee e1;
	Employee e2;

	String id = "VALIDID";

	@BeforeEach
	void setup() {
		e1 = new Employee();
		e1.setName("John Keller");
		e1.setSalary(2500.);
		e1.setCity("Dallas");
		e1.setEmail("johnkeller@xmpl.com");

		e2 = new Employee();
		e2.setId("asd123");
		e2.setName("John Keller");
		e2.setSalary(2500.);
		e2.setCity("Dallas");
		e2.setEmail("johnkeller@xmpl.com");

		Mockito.when(dao.insert(e1)).thenReturn(e2);
		Mockito.when(dao.findById(id)).thenReturn(Optional.of(e1));
		Mockito.when(dao.findById("INVALIDID")).thenReturn(Optional.empty());
		Mockito.when(dao.findByEmail("johnkeller@xmpl.com")).thenReturn(e2);
		Mockito.when(dao.findAllByCity("Bangalore")).thenReturn(Arrays.asList(new Employee(), new Employee()));
	}

	@Test
	void shouldAddNewEmployee() {
		Employee e1 = new Employee();
		e1.setName("John Keller");
		e1.setSalary(2500.);
		e1.setCity("Dallas");
		e1.setEmail("johnkeller@xmpl.com");

		Employee e2 = service.addNewEmployee(e1); // interanally this uses dao.insert(e1)
		// we have to mock the behaviour of dao.insert() in order to test the service
		// layer in isolation
		assertNotNull(e2);
		assertNotNull(e2.getId());
		assertEquals("asd123", e2.getId());
	}

	@Test
	void shouldThrowServiceExceptionForEmployeeWithNullName() {
		Employee emp = new Employee();
		// using try-catch for expected exception is an old technique, check the next
		// test case
		try {
			service.addNewEmployee(emp);
			fail("Expected ServiceException be thrown; but did not catch!");
		} catch (ServiceException e) {
			// test passes
		}
	}

	@Test
	void shouldThrowServiceExceptionForNullEmployee() {
		assertThrows(ServiceException.class, () -> service.addNewEmployee(null));
	}

	@Test
	void shouldGetEmployeeForId() {

		Employee emp = service.getEmployeeById(id);
		assertNotNull(emp);
		assertEquals("John Keller", emp.getName());
		assertEquals("johnkeller@xmpl.com", emp.getEmail());
	}

	@Test
	void shouldThrowServiceExceptionForNullId() {
		assertThrows(ServiceException.class, () -> service.getEmployeeById(null));
		assertThrows(ServiceException.class, () -> service.getEmployeeById(""));
	}

	@Test
	void shouldReturnNullForInvalidId() {
		assertNull(service.getEmployeeById("INVALIDID"));
	}

	@Test
	void shouldGetEmployeeForValidEmail() {
		Employee emp = service.getEmployeeByEmail("johnkeller@xmpl.com");
		assertNotNull(emp);
		assertEquals("John Keller", emp.getName());
	}

	@Test
	void shouldGetEmployeesInCity() {
		List<Employee> list = service.getAllInCity("Bangalore");
		assertEquals(2, list.size());
	}

}
