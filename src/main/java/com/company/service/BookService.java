package com.company.service;

import com.company.model.Book;
import com.company.repository.BookRepository;

import java.util.List;

public class BookService {
    private BookRepository bookRepository = new BookRepository();

    public void addBook(Book book) {

        if (validBook(book)) {
            return;
        }

        if (bookRepository.isExistBook(book.getName(), book.getAuthor())) {
            System.out.println("This book already exist! ");
        } else {
            bookRepository.saveBook(book);
            System.out.println("Success");
        }
    }

    public boolean validBook(Book book) {
        if (book.getName() == null) {
            return false;
        }
        if (book.getAuthor() == null) {
            return false;
        }
        return true;
    }

    public void bookList() {
        List<Book> bookList = bookRepository.selectAllBook();
        int index = 1;
        for (Book book : bookList) {
            System.out.println(index + ". " + book);
            index++;
        }
    }
    public void updateBookService(int id, String name, String author) {
        // validation
        if (!bookRepository.isExistBookId(id)) {
            System.out.println("not found id");
            return;
        }

        bookRepository.updateBook(id, name, author);
    }

    public void deleteBook(int id) {
        if (!bookRepository.isExistBookId(id)) {
            System.out.println("not found id");
            return;
        }
        bookRepository.deleteBook(id);
    }
}
