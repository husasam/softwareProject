package com.example.library.repository;

import com.example.library.model.BorrowRecord;
import com.example.library.model.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BorrowRepositoryTes{

    @Test
    void testAddAndGetAll() {
        BorrowRepository repo = new BorrowRepository();
        BorrowRecord r = new BorrowRecord("u1", new Book("T", "A", "1"));
        repo.add(r);

        assertEquals(1, repo.getAll().size());
    }
}
