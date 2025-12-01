package com.example.library.repository;

import com.example.library.model.BorrowRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * In-memory repository for {@link BorrowRecord} objects.
 * Stores and retrieves records of borrowed media items.
 *
 * No database is used; data resets when the program ends.
 *
 * @version 1.0
 */
public class BorrowRepository {

    /** Internal storage for borrow records. */
    private final List<BorrowRecord> records = new ArrayList<>();

    /**
     * Adds a borrow record.
     *
     * @param r the record to add
     */
    public void add(BorrowRecord r) { 
        records.add(r); 
    }

    /**
     * Returns all borrow records.
     *
     * @return list of borrow records
     */
    public List<BorrowRecord> getAll() { 
        return records; 
    }
}
