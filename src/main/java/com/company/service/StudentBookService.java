package com.company.service;

import com.company.container.ComponentContainer;
import com.company.enums.StudentBookStatus;
import com.company.model.StudentBook;
import com.company.repository.BookRepository;
import com.company.repository.StudentBookRepository;
import com.company.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;

public class StudentBookService {

    private BookRepository bookRepository = new BookRepository();
    private StudentRepository studentRepository = new StudentRepository();
    private StudentBookRepository studentBookRepository = new StudentBookRepository();

    public void takeBook(StudentBook studentbook) {
        if (!bookRepository.isExistBookId(studentbook.getBookId())) {
            System.out.println("Book Not Found. Mazgi.");
            return;
        }
        studentBookRepository.takeBook(studentbook);
    }

    public void takkenBook() {
        List<StudentBook> studentBookList = studentBookRepository.getStudentBookList(StudentBookStatus.TAKEN,
                ComponentContainer.currentUser.getId());

        for (StudentBook studentBook : studentBookList) {
            System.out.println(studentBook);
        }
    }

    public void allTakkenBook() {
        List<StudentBook> studentBookList = studentBookRepository.getStudentBookList(StudentBookStatus.TAKEN);

        for (StudentBook studentBook : studentBookList) {
            System.out.println(studentBook);
        }
    }

    public void returnedBook(int bId, int sid) {

        StudentBook studentBook = studentBookRepository.getStudentBook(bId, sid);
        if (studentBook == null) {
            System.out.println("Mazgi.");
            return;
        }

        studentBook.setReturnedDate(LocalDate.now());
        studentBook.setStatus(StudentBookStatus.RETURNED);

        studentBookRepository.returnedBook(studentBook);

    }

    public void history() {
        studentBookRepository.getStudentBookList(StudentBookStatus.RETURNED, ComponentContainer.currentUser.getId());
    }
}
