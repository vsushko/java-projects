package net.javaguides.departmentservice.service;

import net.javaguides.departmentservice.dto.DepartmentDto;

/**
 * @author vsushko
 */
public interface DepartmentService {

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentByCode(String code);
}
