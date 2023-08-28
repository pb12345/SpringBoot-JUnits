package com.jpatest.springunittest.repository;

import com.jpatest.springunittest.model.Employee;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;

    @BeforeEach
    public void setup() {
        employee = Employee.builder()
                .firstName("ABC")
                .lastName("Test")
                .email("abc@test.com")
                .build();
    }

    //JUnit test for save Employee operation
    //BDD style naming convention given_when_then()
    @DisplayName("JUnit test for save Employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {
        //given - precondition or set up
        /*Employee employee = Employee.builder()
                .firstName("ABC")
                .lastName("Test")
                .email("abc@test.com")
                .build();*/

        //when - action or behaviour to be tested
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verifies the output
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
    }


    //JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {
        //given
        /*Employee employee1 = Employee.builder()
                .firstName("ABC")
                .lastName("Test")
                .email("abc@test.com")
                .build();*/

        Employee employee2 = Employee.builder()
                .firstName("XYZ")
                .lastName("Test")
                .email("xyz@test.com")
                .build();
        employeeRepository.save(employee);
        employeeRepository.save(employee2);

        //when
        List<Employee> employeeList = employeeRepository.findAll();

        //then
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    //JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindByID_thenReturnEmployeeObject() {
        //given
        /*Employee employee1 = Employee.builder()
                .firstName("ABC")
                .lastName("Test")
                .email("abc@test.com")
                .build();*/

        employeeRepository.save(employee);

        //when
        Employee employee1 = employeeRepository.findById(employee.getId()).get();

        //then
        assertThat(employee1).isNotNull();
    }

    //JUnit test for custom query method
    @DisplayName(("JUnit test for custom query method"))
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEMployeeObject() {
        //given
        /*Employee employee1 = Employee.builder()
                .firstName("ABC")
                .lastName("testing")
                .email("abcd@test.com")
                .build();*/
        employeeRepository.save(employee);

        //when
        Employee employee1 = employeeRepository.findByEmail(employee.getEmail()).get();

        //then
        assertThat(employee1).isNotNull();
    }

    //JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEMployee_thenReturnUpdatedEmployee() {
        //given
        /*Employee employee = Employee.builder()
                .firstName("ABC")
                .lastName("testing")
                .email("abcd@test.com")
                .build();*/
        employeeRepository.save(employee);

        //when
        Employee savedEmployee = employeeRepository.findById(employee.getId()).get();
        savedEmployee.setFirstName("Test");
        savedEmployee.setEmail("testing@gmail.com");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        //then
        assertThat(updatedEmployee.getEmail()).isEqualTo("testing@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Test");

    }

    //JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {
        //given
        /*Employee employee1 = Employee.builder()
                .firstName("ABC")
                .lastName("testing")
                .email("abcd@test.com")
                .build();*/
        employeeRepository.save(employee);

        //when
        /*employeeRepository.delete(employee1);*/
        employeeRepository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        //then
        assertThat(employeeOptional).isEmpty();
    }

    //JUnit test for custom query JPQL with index
    @DisplayName("JUnit test for custom query JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQL_thenReturnEmployee() {
        //given
        /*Employee employee = Employee.builder()
                .firstName("ABC")
                .lastName("testing")
                .email("abcd@test.com")
                .build();*/
        employeeRepository.save(employee);

        String firstName = "ABC";
        String lastName = "testing";

        //when
        Employee savedEmployee = employeeRepository.findByJPQL(firstName, lastName);

        //then
        assertThat(savedEmployee).isNotNull();
    }

    //JUnit test for custom JPQL query using named params
    @DisplayName("JUnit test for custom JPQL query using named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployee() {
        //given
        /*Employee employee = Employee.builder()
                .firstName("ABC")
                .lastName("testing")
                .email("abcd@test.com")
                .build();*/
        employeeRepository.save(employee);

        String firstName = "ABC";
        String lastName = "testing";

        //when
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName, lastName);

        //then
        assertThat(savedEmployee).isNotNull();
    }

    //JUnit test for custom Native SQL query using index params
    @DisplayName("JUnit test for custom Native SQL query using index params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQL_thenReturnEmployee() {
        //given
        /*Employee employee = Employee.builder()
                .firstName("ABC")
                .lastName("testing")
                .email("abcd@test.com")
                .build();*/
        employeeRepository.save(employee);

        String firstName = "ABC";
        String lastName = "testing";

        //when
        Employee savedEmployee = employeeRepository.findByNativeSQL(firstName, lastName);

        //then
        assertThat(savedEmployee).isNotNull();
    }

    //JUnit test for custom Native SQL query using named params
    @DisplayName("JUnit test for custom Native SQL query using named params")
    @Test
    public void givenFirstNameAndLastName_whenFindByNativeSQLNamedParams_thenReturnEmployee() {
        //given
        /*Employee employee = Employee.builder()
                .firstName("ABC")
                .lastName("testing")
                .email("abcd@test.com")
                .build();*/
        employeeRepository.save(employee);

        String firstName = "ABC";
        String lastName = "testing";

        //when
        Employee savedEmployee = employeeRepository.findByNativeSQLNamedParams(firstName, lastName);

        //then
        assertThat(savedEmployee).isNotNull();
    }

}
