//package com.example.library.time;
//
//import java.time.LocalDate;
//
//public interface TimeProvider {
//    LocalDate today();
//}
package com.example.library.time;

import java.time.LocalDate;

/**
 * Abstraction for retrieving the current system date.
 * Useful for mocking time in tests.
 */
public interface TimeProvider {

    /**
     * Returns today's date.
     *
     * @return current date
     */
    LocalDate today();
}
