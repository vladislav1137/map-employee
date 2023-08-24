package com.example.mapemployee.service;

import com.example.mapemployee.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;
    List<Employee> employees = Arrays.asList(
            new Employee("Nikita", "Razin", 1, 100),
            new Employee("Fedor", "Utkin", 1, 100),
            new Employee("Evgeniy", "Laptev", 2, 150),
            new Employee("Alexey", "Noskov", 2, 200),
            new Employee("Marina", "Lokteva", 3, 300)
    );

    @BeforeEach
    void setup() {
        Mockito.when(employeeService.getAll()).thenReturn(employees);
    }

    @Test
    void sum() {
        double actual = departmentService.getEmployeeSumSalary(1);
        assertEquals(200, actual, 0.000001);
    }

    @Test
    void min() {
        double actual = departmentService.getEmployeeWithMinSalary(2);
        assertEquals(150,actual,0.00001);
    }
    @Test
    void max() {
        double actual = departmentService.getEmployeeWithMaxSalary(2);
        assertEquals(200,actual,0.00001);
    }

    @Test
    void getAllByDepartment() {
        List<Employee> actual = departmentService.getAll(2);
        List<Employee> expected = Arrays.asList(
                new Employee("Evgeniy", "Laptev", 2, 150),
        new Employee("Alexey", "Noskov", 2, 200));
        assertEquals(expected.size(),actual.size());
        assertTrue(expected.containsAll(actual));
    }

    @Test
    void getAll() {
        List<Integer> expectedDepartments = employees.stream()
                .map(Employee::getDepartment)
                .distinct()
                .collect(Collectors.toList());

        Map<Integer, List<Employee>> actual = departmentService.getAll();
        assertEquals(expectedDepartments.size(),actual.keySet().size());
        assertTrue(expectedDepartments.containsAll(actual.keySet()));
    }
}
