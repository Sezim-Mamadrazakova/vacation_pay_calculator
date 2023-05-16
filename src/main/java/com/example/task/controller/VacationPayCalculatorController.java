package com.example.task.controller;

import com.example.task.entity.VacationPayCalculate;
import com.example.task.service.VacationPayCalculatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

//@Controller
//@RequestMapping("/calculate")
@RequiredArgsConstructor
@RestController

public class VacationPayCalculatorController {
    private final VacationPayCalculatorService vacationPayCalculatorService;
    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "avgSalary", required = false) Double avgSalary,
                             @RequestParam(value = "dateStartOfVacation", required = false)
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate dateStartOfVacation,
                             @RequestParam(value = "dateEndOfVacation", required = false)
                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEndOfVacation
                             ){
        var vacationPay=vacationPayCalculatorService.getVacationPay(avgSalary,
                dateStartOfVacation, dateEndOfVacation);

        return vacationPay;
    }


}
