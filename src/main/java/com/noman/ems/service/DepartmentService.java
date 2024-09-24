package com.noman.ems.service;

import com.noman.ems.dto.DepartmentDto;


import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto getDepartmentById(Long id);
    List<DepartmentDto> getAllDepartments();
    DepartmentDto updateDepartmentById(Long id, DepartmentDto departmentDto);
    void deleteDepartmentById(Long id);
}
