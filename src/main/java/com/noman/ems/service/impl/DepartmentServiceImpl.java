package com.noman.ems.service.impl;

import com.noman.ems.dto.DepartmentDto;
import com.noman.ems.entity.Department;
import com.noman.ems.exception.ResourceNotFoundException;
import com.noman.ems.mapper.DepartmentMapper;
import com.noman.ems.repository.DepartmentRepository;
import com.noman.ems.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Map<String, String> errors = validateDepartmentDto(departmentDto);
        if (!errors.isEmpty()) {
            throw new ResourceNotFoundException(errors);
        }

        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department save = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(save);
    }

    private Map<String, String> validateDepartmentDto(DepartmentDto departmentDto) {
        Map<String, String> errors = new HashMap<>();

        // Check if department name is null or empty
        if (departmentDto.getDepartmentName() == null || departmentDto.getDepartmentName().trim().isEmpty()) {
            errors.put("departmentName", "Department name is required");
        }

        // Check if department description is null, empty, or less than 15 characters
        if (departmentDto.getDepartmentDescription() == null || departmentDto.getDepartmentDescription().trim().isEmpty()) {
            errors.put("departmentDescription", "Department description is required");
        } else if (departmentDto.getDepartmentDescription().length() < 15) {
            errors.put("departmentDescription", "Department description must be at least 15 characters long");
        }

        return errors;
    }


    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments
                .stream()
                .map(DepartmentMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }
}
