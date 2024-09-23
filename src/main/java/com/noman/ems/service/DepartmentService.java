package com.noman.ems.service;

import com.noman.ems.dto.DepartmentDto;


import java.util.List;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    List<DepartmentDto> getAllDepartments();
}
