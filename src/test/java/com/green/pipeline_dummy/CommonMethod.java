package com.green.pipeline_dummy;

import com.green.pipeline_dummy.model.RandomDate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

public class CommonMethod {
    public static LocalDateTime randomDateFuture(RandomDate value) {
        //LocalDate startDate = LocalDate.of(2020, 2, 1);
        LocalDate startDate = LocalDate.of(value.getStartYear(), value.getStartMonth(), value.getStartDate());
        LocalDate endDate = LocalDate.of(2025, value.getEndMonth(), value.getEndDate());

        LocalDateTime start = startDate.atStartOfDay(); // 2024-02-01 00:00
        LocalDateTime end = endDate.atTime(23, 59, 59); // 2025-11-01 23:59:59

        long startEpoch = start.toEpochSecond(java.time.ZoneOffset.UTC);
        long endEpoch = end.toEpochSecond(java.time.ZoneOffset.UTC);

        long randomEpoch = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch + 1);
        LocalDateTime randomDateTime = LocalDateTime.ofEpochSecond(randomEpoch, 0, java.time.ZoneOffset.UTC);
        return randomDateTime;
    }
}
