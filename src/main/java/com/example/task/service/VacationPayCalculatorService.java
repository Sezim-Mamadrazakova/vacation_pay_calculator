package com.example.task.service;

import com.example.task.entity.VacationPayCalculate;
import com.example.task.exception.IncorrectDataException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.MonthDay;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Service

public class VacationPayCalculatorService {
    private static final Double AVG_NUMBER_DAYS_IN_MONTH = 29.3;

    public String getVacationPay(Double avgSalary, LocalDate dateStartOfVacation, LocalDate dateEndOffVacation){
        long countDays = 1;
        if(avgSalaryIsValid(avgSalary)
                && dateStartOfVacationIsValid(dateStartOfVacation)
                && dateEndOfVacationIsValid(dateEndOffVacation)){
            countDays= Stream.iterate(dateStartOfVacation, date->date.plusDays(1))
                    .limit(dateEndOffVacation.getDayOfMonth()- dateStartOfVacation.getDayOfMonth())
                    .filter((day)->!isHoliday(day))
                    .count();
        }
        else {
            throw new IncorrectDataException("The data is not correct");
        }
        return new DecimalFormat("#0.00").format(avgSalary/AVG_NUMBER_DAYS_IN_MONTH*countDays);
    }
    private boolean avgSalaryIsValid(Double avgSalary){
        return avgSalary!=null && avgSalary>0;
    }
    private boolean dateStartOfVacationIsValid(LocalDate dateStartOfVacation){
        return dateStartOfVacation!=null;
    }
    private boolean dateEndOfVacationIsValid(LocalDate dateEndOffVacation){
        return dateEndOffVacation!=null;
    }
    private MonthDay getDate(LocalDate date){
        return MonthDay.of(date.getMonth(), date.getDayOfMonth());
    }
    private boolean isWeekend(LocalDate date){
        DayOfWeek dayOfWeek=date.getDayOfWeek();
        return dayOfWeek==DayOfWeek.SATURDAY || dayOfWeek==DayOfWeek.SUNDAY;
    }
    private boolean isHoliday(LocalDate date){
        return isWeekend(date) || Holidays.holidays.contains(getDate(date));
    }
    private static class Holidays{
        public final static Set<MonthDay> holidays = new HashSet<>();

        static {
            holidays.add(MonthDay.of(Month.JANUARY, 1));
            holidays.add(MonthDay.of(Month.JANUARY, 2));
            holidays.add(MonthDay.of(Month.JANUARY, 3));
            holidays.add(MonthDay.of(Month.JANUARY, 4));
            holidays.add(MonthDay.of(Month.JANUARY, 5));
            holidays.add(MonthDay.of(Month.JANUARY, 6));
            holidays.add(MonthDay.of(Month.JANUARY, 7));
            holidays.add(MonthDay.of(Month.JANUARY, 8));
            holidays.add(MonthDay.of(Month.FEBRUARY, 23));
            holidays.add(MonthDay.of(Month.MARCH, 8));
            holidays.add(MonthDay.of(Month.MAY, 1));
            holidays.add(MonthDay.of(Month.MAY, 9));
            holidays.add(MonthDay.of(Month.JUNE, 12));
            holidays.add(MonthDay.of(Month.NOVEMBER, 4));
        }
    }

}
