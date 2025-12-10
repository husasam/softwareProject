package com.example.library.notifier;
import com.example.library.model.Book;
import com.example.library.model.CD;
import com.example.library.model.BorrowRecord;
import com.example.library.strategy.FineCalculator;
import com.example.library.strategy.FineStrategy;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FineCalculatorTest {

	@Test
	void testBookFine() {
	    FineStrategy bookStrategy = overdueDays -> overdueDays * 1.0;
	    FineStrategy cdStrategy = overdueDays -> overdueDays * 2.0;

	    FineCalculator calculator = new FineCalculator(bookStrategy, cdStrategy);

	    Book book = new Book("Title", "123", "Author");

	    BorrowRecord record = new BorrowRecord("user1", book, LocalDate.now().minusDays(3));

	    record.setDueDate(LocalDate.now().minusDays(3));

	    double fine = calculator.calculate(record, LocalDate.now());
	    assertEquals(3.0, fine, 0.01);
	}

}
