package com.equality.employee.response;

import jakarta.persistence.Column;
import jakarta.persistence.Transient;
import lombok.*;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDto {
    private Integer employeeId;
    private String employeeName;
    private String managerName;
    private Integer pathLevel;
    private String employeeFormat;
    private String pathHierarchy;
}
