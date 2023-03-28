package com.company.controller;

import com.company.container.ComponentContainer;
import com.company.model.Book;
import com.company.service.BookService;
import com.company.service.StudentBookService;
import com.company.service.StudentService;

import java.util.Scanner;

public class AdminController {
    private BookService bookService = new BookService();
    private StudentService studentService = new StudentService();
    private StudentBookService studentBookService = new StudentBookService();

    public void show_menu() {
        System.out.println("** Admin Menu **");
        System.out.println("1. Book add");
        System.out.println("2. Book list");
        System.out.println("3. Book update");
        System.out.println("4. Book deleted");
        System.out.println("5. Profiles show");
        System.out.println("6. Change profile's status");
        System.out.println("7. Taken book");
        System.out.println("0. Logout");
    }

    public void adminMenu() {
        while (true){
            show_menu();
            System.out.print("Enter action: ");
            int action = ComponentContainer.scannerNum.nextInt();

            switch (action) {
                case 1:
                    addBook();
                    break;
                case 2:
                    bookList();
                    break;
                case 3:
                    update();
                    break;
                case 4:
                    deleteBook();
                    break;
                case 5:
                    studentList();
                    break;
                case 6:
                    changeStatusProfile();
                    break;
                case 7:
                    takenBook();
                    break;
            }
        }

    }

    private void bookList() {
        bookService.bookList();
    }

    private void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book name: ");
       String title = scanner.nextLine();

        System.out.print("Enter author: ");
        String author = scanner.nextLine();

        Book book = new Book();
        book.setName(title);
        book.setAuthor(author);

        bookService.addBook(book);

    }

    public void update() {

        System.out.print("Enter book id: ");
        int id = ComponentContainer.scannerNum.nextInt();

        System.out.print("Enter book name: ");
        String title = ComponentContainer.scannerStr.next();

        System.out.print("Enter book author: ");
        String author = ComponentContainer.scannerStr.next();

        bookService.updateBookService(id, title, author);
    }

    public void deleteBook() {
        System.out.print("Enter book id: ");
        int id = ComponentContainer.scannerNum.nextInt();
        bookService.deleteBook(id);
    }

    public void changeStatusProfile() {
        System.out.print("Enter phone: ");
        String phone = ComponentContainer.scannerStr.next();
        studentService.changeStatus(phone);
    }

    private void studentList() {
        studentService.userList();
    }

    public void takenBook() {
        studentBookService.allTakkenBook();
    }

}
