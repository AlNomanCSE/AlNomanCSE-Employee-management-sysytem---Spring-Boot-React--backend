package com.noman.ems.mapper;

import com.noman.ems.dto.EmployeeDto;
import com.noman.ems.entity.Employee;

//Use:
//------
//to map --> Employee entity ------to------ EmployeeDto
//       --> EmployeeDto     ------to-----  Employee entity

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getId()
        );
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        return employee;
    }
}
