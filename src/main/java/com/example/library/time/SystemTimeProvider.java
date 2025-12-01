//package com.example.library.time;
//
//import java.time.LocalDate;
//
//public class SystemTimeProvider implements TimeProvider {
//
//    @Override
//    public LocalDate today() {
//        return LocalDate.now();
//    }
//}
package com.example.library.time;

import java.time.LocalDate;

/**
 * Provides the real system date using LocalDate.now().
 */
public class SystemTimeProvider implements TimeProvider {

    @Override
    public LocalDate today() {
        return LocalDate.now();
    }
}
