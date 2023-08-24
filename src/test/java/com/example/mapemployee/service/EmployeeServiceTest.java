package com.example.mapemployee.service;

import com.example.mapemployee.exceptions.EmployeeAlreadyAddedException;
import com.example.mapemployee.exceptions.EmployeeNotFoundException;
import com.example.mapemployee.exceptions.EmployeeStorageIsFullException;
import com.example.mapemployee.model.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeServiceTest {
    EmployeeService employeeService = new EmployeeService();

    @Test
    void getAll() {
        Employee employee1 = new Employee("Sergey", "Krylov", 1, 20000);
        Employee employee2 = new Employee("Nina", "Krylova", 1, 30000);
        employeeService.add(employee1);
        employeeService.add(employee2);
        List<Employee> expected = Arrays.asList(employee1, employee2);
        assertEquals(2, employeeService.getAll().size());
        assertIterableEquals(expected, employeeService.getAll());
    }

    @Test
    void add() {
        int prevSize = employeeService.getAll().size();
        Employee employee1 = new Employee("Sergey", "Krylov", 1, 20000);
        employeeService.add(employee1);
        assertEquals(prevSize + 1, employeeService.getAll().size());
        assertTrue(employeeService.getAll().contains(employee1));
    }

    @Test
    void whenAddDuplicateThenThrowException() {
        Employee employee1 = new Employee("Sergey", "Krylov", 1, 20000);
        assertDoesNotThrow(() -> employeeService.add(employee1));
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add(employee1));
    }
    

    @Test
    void findPositive() {
        Employee expected = new Employee("Sergey", "Krylov", 1, 20000);
        employeeService.add(expected);
        Employee actual = employeeService.find("Sergey", "Krylov");
        assertNotNull(actual);
        assertEquals(expected,actual);
    }

    @Test
    void findNegative() {
        Employee expected = new Employee("Sergey", "Krylov", 1, 20000);
        employeeService.add(expected);
        assertThrows(EmployeeNotFoundException.class,() -> employeeService.find("Nina","Krylova"));
    }

    @Test
    void remove() {
        Employee employee1 = new Employee("Sergey", "Krylov", 1, 20000);
        employeeService.add(employee1);
        assertTrue(employeeService.getAll().contains(employee1));
        employeeService.remove("Sergey", "Krylov");
        assertFalse(employeeService.getAll().contains(employee1));
    }
}
