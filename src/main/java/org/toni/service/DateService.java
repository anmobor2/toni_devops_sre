package org.toni.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class DateService {
    public long getDaysUntilNextBirthday(LocalDate dateOfBirth) {
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = dateOfBirth.withYear(today.getYear());
        if (nextBirthday.isBefore(today) || nextBirthday.isEqual(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }
        return ChronoUnit.DAYS.between(today, nextBirthday);
    }
}