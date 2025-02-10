package controllers;

import models.Book;
import repositories.BookRepository;
import repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class BookController {
    private BookRepository bookRepository;
    private UserRepository userRepository;

    public BookController(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public void searchBooks(String title, String author, String category) {
        try {
            List<Book> books = bookRepository.searchBooks(title, author, category);
            if (books.isEmpty()) {
                System.out.println("No books found.");
            } else {
                books.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Error while searching for books: " + e.getMessage());
        }
    }


    public void filterBooksByStatus(String status) {
        try {
            List<Book> books = bookRepository.filterBooksByStatus(status);
            if (books.isEmpty()) {
                System.out.println("No books with status: " + status);
            } else {
                books.forEach(System.out::println);
            }
        } catch (SQLException e) {
            System.out.println("Error while filtering books: " + e.getMessage());
        }
    }

    public void addNewBook(String title, String author, int year, int quantity) throws SQLException {
        bookRepository.addNewBook(title, author, year, quantity);
    }

    public void deleteBook(String title) throws SQLException {
        bookRepository.deleteBook(title);
    }

    public void showAllBooks() {
        try {
            List<Book> books = bookRepository.getAllBooks();
            if (books.isEmpty()) {
                System.out.println("No books available in the library.");
            } else {
                for (Book book : books) {
                    System.out.println(book);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching books: " + e.getMessage());
        }
    }

    public void takeBook(String name, String surname, String bookTitle) throws SQLException {

        if (!bookRepository.isBookAvailable(bookTitle)) {
            System.out.println("This book is not available.");
            return;
        }
        addUserBook(name, surname, bookTitle);

        bookRepository.takeBook(bookTitle);

        System.out.println("Book taken successfully!");
    }

    private void addUserBook(String name, String surname, String bookTitle) throws SQLException {
    }

    public void returnBook(String name, String surname, String bookTitle) throws SQLException {
        if (!userRepository.userExists(name, surname)) {
            System.out.println("User not found.");
            return;
        }


        String existingBook = userRepository.getBookTitleByUser(name, surname);
        if (existingBook == null || !existingBook.equals(bookTitle)) {
            System.out.println("This user does not have this book.");
            return;
        }

  
        userRepository.deleteBookTitle(name, surname);
        bookRepository.returnBook(bookTitle);
        System.out.println("Book successfully returned!");
    }


    public boolean isBookAvailable(String bookTitle) throws SQLException {
        return bookRepository.isBookAvailable(bookTitle);
    }
}

