package com.company.model;

import com.company.enums.StudentRole;
import com.company.enums.StudentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private StudentRole role;
    private StudentStatus status;
}
