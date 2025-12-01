package com.example.library.strategy;


public class BookFineStrategy implements FineStrategy {
    private final double ratePerDay;

    public BookFineStrategy(double ratePerDay) { this.ratePerDay = ratePerDay; }

    @Override
    public double calculateFine(int overdueDays) {
        if (overdueDays <= 0) return 0.0;
        return overdueDays * ratePerDay;
    }
}
