package com.example.task;

import com.example.task.exception.IncorrectDataException;
import com.example.task.service.VacationPayCalculatorService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VacationPayCalculatorTests  {
    VacationPayCalculatorService vacationPayCalculatorService=new VacationPayCalculatorService();
    @Test
    public void getVacationPayWithValidParametersWithHolidaysTest(){
        //VacationPayCalculatorService vacationPayCalculatorService=new VacationPayCalculatorService();
        String actual=vacationPayCalculatorService.getVacationPay(360.0,
                LocalDate.of(2023,01,10),
                LocalDate.of(2023,1,20));
        String expected="98,29";
        assertEquals(expected,actual);
    }
    @Test
    public void inputInvalidSalaryTest(){
        VacationPayCalculatorService vacationPayCalculatorService=new VacationPayCalculatorService();
        Throwable throwable=assertThrows(IncorrectDataException.class,()->{
            vacationPayCalculatorService.getVacationPay(-360.0,
                    LocalDate.of(2023,01,10),
                    LocalDate.of(2023,1,20));
        });
        assertNotNull(throwable.getMessage());
    }
    @Test
    public void inputInvalidDateTest(){
        VacationPayCalculatorService vacationPayCalculatorService=new VacationPayCalculatorService();
        Throwable throwable=assertThrows(IncorrectDataException.class,()->{
            vacationPayCalculatorService.getVacationPay(-360.0,
                    null,
                    LocalDate.of(2023,1,20));
        });
        assertNotNull(throwable.getMessage());
    }


}
