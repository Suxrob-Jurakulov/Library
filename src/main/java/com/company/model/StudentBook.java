package com.company.model;

import com.company.enums.StudentBookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentBook {
    private int id;
    private int studentId;
    private int bookId;
    private StudentBookStatus status;
    private LocalDate takenDate;
    private LocalDate returnedDate;
}
