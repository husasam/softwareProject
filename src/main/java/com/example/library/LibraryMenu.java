package com.example.library;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import com.example.library.model.CD;
import com.example.library.model.Media;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;

public class LibraryMenu {

    private static final Logger logger = Logger.getLogger(LibraryMenu.class.getName());

    private final BookService bookService;
    private final BorrowService borrowService;
    private final User user;

    public LibraryMenu(BookService bookService, BorrowService borrowService, User user) {
        this.bookService = bookService;
        this.borrowService = borrowService;
        this.user = user;
    }

    public void start(CD cd1, CD cd2) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                String line;
                if (!sc.hasNextLine()) {
                    logger.info("No input available. Exiting.");
                    break;
                }
                line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    logger.info("Empty input. Please enter a choice.");
                    continue;
                }
                int choice;
                try {
                    choice = Integer.parseInt(line);
                } catch (NumberFormatException nfe) {
                    logger.warning("Invalid number: '" + line + "'. Please enter a valid integer.");
                    continue;
                }
                try {
                    running = handleChoice(choice, sc, cd1, cd2);
                } catch (Exception e) {
                    logger.log(Level.SEVERE, "Unexpected error while handling choice " + choice, e);
                    running = false;
                }
            }
        }
    }

    private void printMenu() {
        logger.info("\n===== Library System =====");
        logger.info("1. View Books");
        logger.info("2. Borrow Book");
        logger.info("3. Return Book");
        logger.info("4. Borrow CD");
        logger.info("5. Return CD");
        logger.info("6. View Overdue Items");
        logger.info("0. Exit");
        logger.info("Choose: ");
    }

    private boolean handleChoice(int choice, Scanner sc, CD cd1, CD cd2) {
        switch (choice) {
            case 1:
                viewBooks();
                break;
            case 2:
                borrowBook(sc);
                break;
            case 3:
                returnBook(sc);
                break;
            case 4:
                borrowCd(sc, cd1, cd2);
                break;
            case 5:
                returnCd(sc);
                break;
            case 6:
                viewOverdue();
                break;
            case 0:
                logger.info("Exiting application by user choice.");
                return false;
            default:
                logger.warning("Invalid choice: " + choice);
                break;
        }
        return true;
    }

    private void viewBooks() {
        List<Book> books = bookService.getAllBooks();
        if (books == null || books.isEmpty()) {
            logger.info("No books available.");
            return;
        }
        for (Book b : books) {
            logger.info(b.getTitle() + " | ISBN: " + b.getIsbn() + " | Available: " + b.isAvailable());
        }
    }

    private void borrowBook(Scanner sc) {
        logger.info("Enter ISBN to borrow: ");
        String isbn = sc.nextLine().trim();
        if (isbn.isEmpty()) {
            logger.warning("Empty ISBN provided.");
            return;
        }
        try {
            boolean ok = borrowService.borrowBook(user, isbn);
            logger.info(ok ? "Borrowed successfully." : "Borrow failed.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while borrowing book with ISBN: " + isbn, e);
        }
    }

    private void returnBook(Scanner sc) {
        logger.info("Enter ISBN to return: ");
        String isbn = sc.nextLine().trim();
        if (isbn.isEmpty()) {
            logger.warning("Empty ISBN provided.");
            return;
        }
        try {
            boolean ok = borrowService.returnBook(isbn);
            logger.info(ok ? "Returned successfully." : "Return failed.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while returning book with ISBN: " + isbn, e);
        }
    }

    private void borrowCd(Scanner sc, CD cd1, CD cd2) {
        logger.info("Available CDs:");
        logger.info(cd1.getId() + " - " + cd1.getTitle());
        logger.info(cd2.getId() + " - " + cd2.getTitle());
        logger.info("Enter CD ID: ");

        String id = sc.nextLine().trim();
        if (id.isEmpty()) {
            logger.warning("Empty CD ID provided.");
            return;
        }

        CD selectedCd = null;
        if (id.equals(cd1.getId())) {
            selectedCd = cd1;
        } else if (id.equals(cd2.getId())) {
            selectedCd = cd2;
        }

        if (selectedCd == null) {
            logger.info("CD not found");
            return;
        }

        try {
            boolean ok = borrowService.borrowCD(user, selectedCd);
            logger.info(ok ? "CD Borrowed" : "CD Borrow failed");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while borrowing CD: " + id, e);
        }
    }

    private void returnCd(Scanner sc) {
        logger.info("Enter CD ID to return: ");
        String id = sc.nextLine().trim();
        if (id.isEmpty()) {
            logger.warning("Empty CD ID provided.");
            return;
        }
        try {
            boolean ok = borrowService.returnCD(id);
            logger.info(ok ? "CD Returned" : "CD Return failed");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error while returning CD: " + id, e);
        }
    }

    private void viewOverdue() {
        List<BorrowRecord> overdue = borrowService.getOverdueBooks();
        if (overdue == null || overdue.isEmpty()) {
            logger.info("No overdue items.");
            return;
        }
        for (BorrowRecord r : overdue) {
            Media m = r.getMedia();
            logger.info("User: " + r.getUserId() +
                        " | Item: " + (m == null ? "<unknown>" : m.getTitle()) +
                        " | Due: " + r.getDueDate());
        }
    }
}
