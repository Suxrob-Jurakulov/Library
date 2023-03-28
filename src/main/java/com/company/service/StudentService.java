package com.company.service;

import com.company.enums.StudentStatus;
import com.company.model.Student;
import com.company.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public void userList() {
        List<Student> studentList = studentRepository.selectAll();

        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    public void changeStatus(String phone) {
        Student student = studentRepository.getStudentByPhone(phone);
        if (student == null) {
            System.out.println("Kalla No Student");
            return;
        }

        if (student.getStatus().equals(StudentStatus.ACTIVE)) {
            student.setStatus(StudentStatus.BLOCK);
        } else {
            student.setStatus(StudentStatus.ACTIVE);
        }
        studentRepository.changeStatus(student.getId(), student.getStatus());
        System.out.println("Success");
    }
}
