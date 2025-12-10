//package com.example.library;
//
//import com.example.library.model.CD;
//import com.example.library.model.User;
//import com.example.library.repository.BookRepository;
//import com.example.library.repository.BorrowRepository;
//import com.example.library.repository.UserRepository;
//import com.example.library.service.BookService;
//import com.example.library.service.BorrowService;
//import com.example.library.service.UserService;
//import com.example.library.LibraryMenu;
//
//public class Main {
//    public static void main(String[] args) {
//
//        BookRepository bookRepo = new BookRepository();
//        UserRepository userRepo = new UserRepository();
//        BorrowRepository borrowRepo = new BorrowRepository();
//
//        BookService bookService = new BookService(bookRepo);
//        UserService userService = new UserService(userRepo);
//        BorrowService borrowService = new BorrowService(bookService);
//
//        bookService.addBook("Java Basics", "111", "husam");
//        bookService.addBook("python", "222", "amjad");
//
//        CD cd1 = new CD("CD1", "Music Collection", "1");
//        cd1.setAvailable(true);
//        CD cd2 = new CD("CD2", "Learning English", "2");
//        cd2.setAvailable(true);
//
//        User user = new User("u1");
//        userService.addUser(user);
//
//        LibraryMenu menu = new LibraryMenu(bookService, borrowService, user);
//        menu.start(cd1, cd2);    }
//}
