package com.company.service;

import com.company.container.ComponentContainer;
import com.company.controller.AdminController;
import com.company.controller.UserController;
import com.company.enums.StudentRole;
import com.company.enums.StudentStatus;
import com.company.model.Student;
import com.company.repository.StudentRepository;

import java.util.regex.Pattern;

public class MainService {

    private StudentRepository studentRepository = new StudentRepository();
    private AdminController adminController = new AdminController();
    private UserController userController = new UserController();


    public void aut(String phone, String password) {
        Student student = studentRepository.auth(phone, password);
        if (student == null) {
            System.out.println("Phone or password is wrong!");
            return;
        }
        if (student.getStatus().equals(StudentStatus.BLOCK)) {
            System.out.println("You are blocked! ");
            return;
        }
        ComponentContainer.currentUser = student;

        if (student.getRole().equals(StudentRole.USER)) {
            userController.userMenu();
        }else {
            adminController.adminMenu();
        }

    }

    public void registration(Student student) {

        if (!isValid(student)) {
            return;
        }

        if (studentRepository.isPhoneExist(student.getPhone())) {
            System.out.println("Mazgi bu raqam borku !");
        } else {
            studentRepository.saveStudent(student);

        }
    }

    private boolean isValid(Student student) {

        if (student.getName() == null || student.getName().length() < 2) {
            System.out.println("Name is wrong!");
            return false;
        }

        if (student.getSurname() == null || student.getSurname().length() < 2) {
            System.out.println("Surname is wrong!");
            return false;
        }

        if (student.getPhone() == null || !Pattern.matches("[0-9]{12}", student.getPhone())) {
            System.out.println("Phone is wrong !");
            return false;
        }
        if (student.getPassword() == null || student.getPassword().length() < 3) {
            System.out.println("Password is wrong");
            return false;
        }

        if (studentRepository.isPhoneExist(student.getPhone())) {
            System.out.println("Mazgi bu raqam bor");
            return false;
        }
        return true;
    }
}
