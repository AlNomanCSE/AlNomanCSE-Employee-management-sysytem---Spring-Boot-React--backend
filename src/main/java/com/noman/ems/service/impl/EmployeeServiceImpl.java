package com.noman.ems.service.impl;

import com.noman.ems.dto.EmployeeDto;
import com.noman.ems.entity.Employee;
import com.noman.ems.exception.ResourceNotFoundException;
import com.noman.ems.mapper.EmployeeMapper;
import com.noman.ems.repository.EmployeeRepository;
import com.noman.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee save = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(save);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("\uD83D\uDFE5 Employee is not exist with the given id : " + id));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees
                .stream()
                .map(EmployeeMapper::mapToEmployeeDto)
//       or --->.map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toList());

    }

}
