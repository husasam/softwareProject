package com.example.library.strategy;

import com.example.library.model.BorrowRecord;
import com.example.library.model.Media;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * FineCalculator picks appropriate strategy by media type.
 */
public class FineCalculator {
    private final FineStrategy bookStrategy;
    private final FineStrategy cdStrategy;

    public FineCalculator(FineStrategy bookStrategy, FineStrategy cdStrategy) {
        this.bookStrategy = bookStrategy;
        this.cdStrategy = cdStrategy;
    }

    public double calculate(BorrowRecord record, LocalDate now) {
        long overdueDays = ChronoUnit.DAYS.between(record.getDueDate(), now);
        if (overdueDays <= 0) return 0.0;

        Media m = record.getMedia();
        if (m instanceof com.example.library.model.Book) {
            return bookStrategy.calculateFine((int) overdueDays);
        } else if (m instanceof com.example.library.model.CD) {
            return cdStrategy.calculateFine((int) overdueDays);
        } else {
            return bookStrategy.calculateFine((int) overdueDays);
        }
    }
}
