package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.model.Student;
import com.company.service.MainService;

public class MainController {
    private MainService mainService = new MainService();


    public void showMenu() {
        System.out.println("*** Menu ***");
        System.out.println("1. Registration");
        System.out.println("2. Authorization");
        System.out.println("0. Exit");
    }

    public void start() {

        while (true) {
            showMenu();
            System.out.print("Enter action : ");
            int action = ComponentContainer.scannerNum.nextInt();

            switch (action) {

                case 1:
                    getStudentDetail();
                    break;
                case 2:
                    authorization();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Mazgi norm son tanla");
            }
        }
    }

    public void authorization() {
        System.out.print("Enter phone : ");
        String phone = ComponentContainer.scannerStr.next();

        System.out.print("Enter password : ");
        String password = ComponentContainer.scannerStr.next();
        mainService.aut(phone, password);
    }

    public void getStudentDetail() {
        System.out.print("Enter name : ");
        String name = ComponentContainer.scannerStr.next();

        System.out.print("Enter surname : ");
        String surname = ComponentContainer.scannerStr.next();

        System.out.print("Enter phone : ");
        String phone = ComponentContainer.scannerStr.next();

        System.out.print("Enter password : ");
        String password = ComponentContainer.scannerStr.next();

        Student student = new Student();
        student.setName(name);
        student.setSurname(surname);
        student.setPhone(phone);
        student.setPassword(password);

        mainService.registration(student);
    }
}
