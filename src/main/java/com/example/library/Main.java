package com.example.library;

import com.example.library.model.*;
import com.example.library.repository.*;
import com.example.library.service.*;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        BookRepository bookRepo = new BookRepository();
        UserRepository userRepo = new UserRepository();
        BorrowRepository borrowRepo = new BorrowRepository();

        BookService bookService = new BookService(bookRepo);
        UserService userService = new UserService(userRepo);
        BorrowService borrowService = new BorrowService(bookService);

        bookService.addBook("Java Basics", "111", "husam");
        bookService.addBook("python ", "222", "amjad");

        CD cd1 = new CD("CD1", "Music Collection", null);
        cd1.setAvailable(true);

        CD cd2 = new CD("CD2", "Learning English", null);
        cd2.setAvailable(true);

        User user = new User("u1");
        userService.addUser(user);

        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n===== Library System =====");
            System.out.println("1. View Books");
            System.out.println("2. Borrow Book");
            System.out.println("3. Return Book");
            System.out.println("4. Borrow CD");
            System.out.println("5. Return CD");
            System.out.println("6. View Overdue Items");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice){

                case 1:
                    System.out.println("\n--- Books ---");
                    for (Book b : bookService.getAllBooks()) {
                        System.out.println(b.getTitle() + " | ISBN: " + b.getIsbn() +
                                " | Available: " + b.isAvailable());
                    }
                    break;
                case 2:
                    System.out.print("Enter ISBN to borrow: ");
                    String isbn = sc.nextLine();
                    boolean ok = borrowService.borrowBook(user, isbn);
                    System.out.println(ok ? "Borrowed successfully." : "Borrow failed.");
                    break;

                case 3:
                    System.out.print("Enter ISBN to return: ");
                    String rIsbn = sc.nextLine();
                    boolean r1 = borrowService.returnBook(rIsbn);
                    System.out.println(r1 ? "Returned successfully." : "Return failed.");
                    break;

                case 4:
                    System.out.println("Available CDs:");
                    System.out.println(cd1.getId() + " - " + cd1.getTitle());
                    System.out.println(cd2.getId() + " - " + cd2.getTitle());

                    System.out.print("Enter CD ID to borrow: ");
                    String cid = sc.nextLine();

                    CD selectedCD =
                            cid.equals(cd1.getId()) ? cd1 :
                            cid.equals(cd2.getId()) ? cd2 : null;

                    if (selectedCD == null) {
                        System.out.println("CD not found!");
                        break;
                    }

                    boolean ok2 = borrowService.borrowCD(user, selectedCD);
                    System.out.println(ok2 ? "CD Borrowed." : "CD Borrow failed.");
                    break;

                case 5:
                    System.out.print("Enter CD ID to return: ");
                    String rcid = sc.nextLine();
                    boolean rc = borrowService.returnCD(rcid);
                    System.out.println(rc ? "CD Returned." : "CD Return failed.");
                    break;

                case 6:
                    System.out.println("\n--- Overdue Items ---");
                    List<BorrowRecord> overdue = borrowService.getOverdueBooks();
                    if (overdue.isEmpty()) {
                        System.out.println("No overdue items.");
                    } else {
                        for (BorrowRecord r : overdue) {
                            Media m = r.getMedia();
                            System.out.println("User: " + r.getUserId() +
                                    " | Item: " + m.getTitle() +
                                    " | Due Date: " + r.getDueDate());
                        } }
                    break;

                case 0:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
