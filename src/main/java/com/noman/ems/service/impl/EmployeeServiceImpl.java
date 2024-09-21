package com.noman.ems.service.impl;

import com.noman.ems.dto.EmployeeDto;
import com.noman.ems.entity.Employee;
import com.noman.ems.mapper.EmployeeMapper;
import com.noman.ems.repository.EmployeeRepository;
import com.noman.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
