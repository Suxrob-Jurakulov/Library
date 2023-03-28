package com.company.repository;

import com.company.enums.StudentBookStatus;
import com.company.model.StudentBook;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class StudentBookRepository {
    public void takeBook(StudentBook studentbook) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "insert into student_book(sid, bid, status, taken_date ) values(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, studentbook.getStudentId());
            statement.setInt(2, studentbook.getBookId());
            statement.setString(3, StudentBookStatus.TAKEN.name());
            statement.setDate(4, Date.valueOf(LocalDate.now()));

            statement.execute();
            System.out.println("success take book");
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public List<StudentBook> getStudentBookList(StudentBookStatus status, Integer sId) {
        List<StudentBook> studentBookList = new LinkedList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "select * from student_book where  status='" + status + "'and id = " + sId;

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                StudentBook studentBook = new StudentBook();
                studentBook.setId(resultSet.getInt(1));
                studentBook.setStudentId(resultSet.getInt(2));
                studentBook.setBookId(resultSet.getInt(3));
                studentBook.setStatus(StudentBookStatus.valueOf(resultSet.getString(4)));

                Date date = resultSet.getDate(5);
                studentBook.setTakenDate(date.toLocalDate());
                studentBookList.add(studentBook);
            }

            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return studentBookList;
    }

    public List<StudentBook> getStudentBookList(StudentBookStatus status) {
        List<StudentBook> studentBookList = new LinkedList<>();
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "select * from student_book where  status='" + status + "'";

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                StudentBook studentBook = new StudentBook();
                studentBook.setId(resultSet.getInt(1));
                studentBook.setStudentId(resultSet.getInt(2));
                studentBook.setBookId(resultSet.getInt(3));
                studentBook.setStatus(StudentBookStatus.valueOf(resultSet.getString(4)));

                Date date = resultSet.getDate(5);
                studentBook.setTakenDate(date.toLocalDate());
                studentBookList.add(studentBook);
            }

            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return studentBookList;
    }

    public StudentBook getStudentBook(int bId, int sId) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "Select * from student_book where bid=? and sid=? and status = 'TAKEN'";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, bId);
            statement.setInt(2, sId);

            /*int n = statement.executeUpdate();
            ResultSet resultSet = statement.executeQuery(); // RE
             */
            boolean resultB = statement.execute();
            if (resultB) {
                ResultSet resultSet = statement.getResultSet();
                if (resultSet.next()) {
                    StudentBook studentBook = new StudentBook();
                    studentBook.setId(resultSet.getInt(1));
                    studentBook.setStudentId(resultSet.getInt(2));
                    studentBook.setBookId(resultSet.getInt(3));
                    studentBook.setStatus(StudentBookStatus.valueOf(resultSet.getString(4)));

                    Date date = resultSet.getDate(5);
                    studentBook.setTakenDate(date.toLocalDate());
                    return studentBook;
                }
            }
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public void returnedBook(StudentBook studentBook) {
        try {
            Connection connection = DBConnection.getConnection();
            String sql = "update student_book set status= ? and returned_date = ? Where id =?";

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, studentBook.getStatus().name());
            statement.setDate(2, Date.valueOf(studentBook.getReturnedDate()));
            statement.setInt(3, studentBook.getId());

            statement.execute();
            System.out.println("success");
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public void history() {

        try {

            Connection connection = DBConnection.getConnection();
            String sql = "select * from student_book ";

            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                System.out.print(" " + resultSet.getInt(1));
                System.out.print(" " + resultSet.getInt(2));
                System.out.print(" " + resultSet.getInt(3));
                System.out.print(" " + resultSet.getString(4));
                System.out.print(" " + resultSet.getDate(5));
                System.out.println(" " + resultSet.getDate(6));

            }

            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }


    }
}
