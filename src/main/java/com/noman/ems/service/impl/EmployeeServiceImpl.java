package com.noman.ems.service.impl;

import com.noman.ems.dto.EmployeeDto;
import com.noman.ems.entity.Employee;
import com.noman.ems.exception.ResourceNotFoundException;
import com.noman.ems.mapper.EmployeeMapper;
import com.noman.ems.repository.EmployeeRepository;
import com.noman.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Map<String, String> errors = validateEmployeeDto(employeeDto);
        if (!errors.isEmpty()) {
            throw new ResourceNotFoundException(errors);
        }
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee save = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(save);
    }
    private Map<String, String> validateEmployeeDto(EmployeeDto employeeDto) {
        Map<String, String> errors = new HashMap<>();

        if (employeeDto.getFirstName() == null || employeeDto.getFirstName().trim().isEmpty()) {
            errors.put("firstName", "First name is required");
        }

        if (employeeDto.getLastName() == null || employeeDto.getLastName().trim().isEmpty()) {
            errors.put("lastName", "Last name is required");
        }

        if (employeeDto.getEmail() == null || employeeDto.getEmail().trim().isEmpty()) {
            errors.put("email", "Email is required");
        } else if (!isValidEmail(employeeDto.getEmail())) {
            errors.put("email", "Invalid email format");
        }

        return errors;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
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

    @Override
    public EmployeeDto updateEmployeeById(Long id, EmployeeDto employeeDto) {
        Employee employee= employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("\uD83D\uDFE5 Employee is not exist with the given id : " + id));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee update = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(update);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("\uD83D\uDFE5 Employee is not exist with the given id : " + id));
        employeeRepository.delete(employee);
    }
}
