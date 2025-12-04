//package com.example.library.strategy;
//
//public class CDFineStrategy implements FineStrategy {
//    private final double ratePerDay;
//    public CDFineStrategy(double ratePerDay) { this.ratePerDay = ratePerDay; }
//    @Override public double calculateFine(int overdueDays) {
//        if (overdueDays <= 0) return 0.0;
//        return overdueDays * ratePerDay;
//    }
//}
package com.example.library.strategy;

/**
 * Fine calculation strategy for CDs.
 */
public class CDFineStrategy implements FineStrategy {

    private final double ratePerDay;

    /**
     * Creates a CDFineStrategy with a fixed daily rate.
     *
     * @param ratePerDay daily fine amount
     */
    public CDFineStrategy(double ratePerDay) {
        this.ratePerDay = ratePerDay;
    }

    @Override
    public double calculateFine(int overdueDays) {
        if (overdueDays <= 0) return 0.0;
        return overdueDays * ratePerDay;
    }
}
