package com.example.library;
import java.util.List;
import java.util.Scanner;

import com.example.library.model.Book;
import com.example.library.model.BorrowRecord;
import com.example.library.model.CD;
import com.example.library.model.Media;
import com.example.library.model.User;
import com.example.library.service.BookService;
import com.example.library.service.BorrowService;
import java.util.logging.Logger;


public class LibraryMenu {
	private static final Logger logger = Logger.getLogger(LibraryMenu.class.getName());

    private final BookService bookService;
    private final BorrowService borrowService;
    private final User user;
    private final CD cd1;
    private final CD cd2;

    public LibraryMenu(BookService bookService, BorrowService borrowService, User user, CD cd1, CD cd2) {
        this.bookService = bookService;
        this.borrowService = borrowService;
        this.user = user;
        this.cd1 = cd1;
        this.cd2 = cd2;
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();
            handleChoice(choice, sc);
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
        System.out.print("Choose: ");
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1 : viewBooks();
            case 2 : borrowBook(sc);
            case 3 : returnBook(sc);
            case 4 : borrowCd(sc);
            case 5 :returnCd(sc);
            case 6 : viewOverdue();
            case 0 :System.exit(0);
            default : System.out.println("Invalid choice");
        }
    }

    private void viewBooks() {
        for (Book b : bookService.getAllBooks()) {
        	logger.info(b.getTitle() + " | ISBN: " + b.getIsbn() + " | Available: " + b.isAvailable());
        }
    }

    private void borrowBook(Scanner sc) {
    	logger.info("Enter ISBN to borrow: ");
        String isbn = sc.nextLine();
        boolean ok = borrowService.borrowBook(user, isbn);
        logger.info(ok ? "Borrowed successfully." : "Borrow failed.");
    }

    private void returnBook(Scanner sc) {
    	logger.info("Enter ISBN to return: ");
        String isbn = sc.nextLine();
        boolean ok = borrowService.returnBook(isbn);
        logger.info(ok ? "Returned successfully." : "Return failed.");
    }

    private void borrowCd(Scanner sc) {
    	logger.info("Available CDs:");
    	logger.info(cd1.getId() + " - " + cd1.getTitle());
    	logger.info(cd2.getId() + " - " + cd2.getTitle());

    	logger.info("Enter CD ID: ");
        String id = sc.nextLine();

        CD selected = id.equals(cd1.getId()) ? cd1 :
                      id.equals(cd2.getId()) ? cd2 : null;

        if (selected == null) {
        	logger.info("CD not found");
            return;
        }

        boolean ok = borrowService.borrowCD(user, selected);
        logger.info(ok ? "CD Borrowed" : "CD Borrow failed");
    }

    private void returnCd(Scanner sc) {
    	logger.info("Enter CD ID to return: ");
        String id = sc.nextLine();
        boolean ok = borrowService.returnCD(id);
        logger.info(ok ? "CD Returned" : "CD Return failed");
    }

    private void viewOverdue() {
        List<BorrowRecord> overdue = borrowService.getOverdueBooks();

        if (overdue.isEmpty()) {
        	logger.info("No overdue items.");
            return;
        }

        for (BorrowRecord r : overdue) {
            Media m = r.getMedia();
            System.out.println("User: " + r.getUserId() +
                    " | Item: " + m.getTitle() +
                    " | Due: " + r.getDueDate());
        }
    }
}
