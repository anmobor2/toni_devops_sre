package org.toni.service;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DateServiceTest {

    private final DateService dateService = new DateService();

    @Test
    void testGetDaysUntilNextBirthday() {
        LocalDate today = LocalDate.now();

        // Case 1: Birthday is today
        LocalDate birthdayToday = today;
        long daysUntilBirthday = dateService.getDaysUntilNextBirthday(birthdayToday);
        assertEquals(0, daysUntilBirthday);

        // Case 2: Birthday is tomorrow
        LocalDate birthdayTomorrow = today.plusDays(1);
        daysUntilBirthday = dateService.getDaysUntilNextBirthday(birthdayTomorrow);
        assertEquals(1, daysUntilBirthday);

        // Case 3: Birthday was yesterday
        LocalDate birthdayYesterday = today.minusDays(1);
        daysUntilBirthday = dateService.getDaysUntilNextBirthday(birthdayYesterday);
        assertEquals(364, daysUntilBirthday);  // Assuming no leap year handling for simplicity

        // Case 4: Birthday is exactly one year from today
        LocalDate birthdayNextYear = today.plusYears(1);
        daysUntilBirthday = dateService.getDaysUntilNextBirthday(birthdayNextYear);
        assertEquals(365, daysUntilBirthday);  // Assuming no leap year handling for simplicity

        // Case 5: Birthday is in six months
        LocalDate birthdayInSixMonths = today.plusMonths(6);
        daysUntilBirthday = dateService.getDaysUntilNextBirthday(birthdayInSixMonths);
        assertEquals(today.until(birthdayInSixMonths).getDays(), daysUntilBirthday);
    }
}