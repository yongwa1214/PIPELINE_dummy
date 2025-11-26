package com.green.pipeline_dummy.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class RandomDate {
    private int startYear;
    private int startMonth;
    private int startDate;
    private int endMonth;
    private int endDate;
}
