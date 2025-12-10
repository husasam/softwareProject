
package com.example.library.strategy;

/**
 * Strategy interface for computing overdue fines.
 */
public interface FineStrategy {

    /**
     * Calculates the fine based on overdue days.
     *
     * @param overdueDays number of days past due date
     * @return calculated fine amount
     */
    double calculateFine(int overdueDays);
}
