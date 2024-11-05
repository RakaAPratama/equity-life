package com.equality.employee.service;

import com.equality.employee.entity.Employee;
import com.equality.employee.response.EmployeeResponseDto;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeResponseDto> getALlEmployee();
}
