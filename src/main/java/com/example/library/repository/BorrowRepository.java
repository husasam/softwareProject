package com.example.library.repository;

import com.example.library.model.BorrowRecord;
import java.util.ArrayList;
import java.util.List;

/**
 * In-memory storage for borrow records.
 */
public class BorrowRepository{
    private final List<BorrowRecord> records = new ArrayList<>();

    public void add(BorrowRecord r) { records.add(r); }
    public List<BorrowRecord> getAll() { return records; }
}
