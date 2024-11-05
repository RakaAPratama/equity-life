package com.equality.employee.service.impl;

import com.equality.employee.repository.EmployeeRepository;
import com.equality.employee.response.EmployeeResponseDto;
import com.equality.employee.service.EmployeeService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.StoredProcedureQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public List<EmployeeResponseDto> getALlEmployee() {
        List<EmployeeResponseDto> employeeResponses = new ArrayList<>();
        StoredProcedureQuery procedureQuery = entityManager.createStoredProcedureQuery("getEmployeeHierarchy");

        procedureQuery.execute();

        List<Object[]> results = procedureQuery.getResultList();

        results.forEach(row -> {
            Integer employeeId = (Integer) row[0];
            String employeeName = (String) row[1];
            String managerName = (String) row[2];
            Integer pathLevel = (Integer) row[3];
            String employeeFormat = (String) row[4];
            String pathHierarchy = (String) row[5];

            EmployeeResponseDto employeeResponse = new EmployeeResponseDto(employeeId, employeeName, managerName, pathLevel, employeeFormat, pathHierarchy);

            employeeResponses.add(employeeResponse);
        });
        log.info("End get all employee");
        return employeeResponses;
    }
}
