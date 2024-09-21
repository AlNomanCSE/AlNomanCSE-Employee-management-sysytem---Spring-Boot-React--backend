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
                employee.getEmail()
        );
    }
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
