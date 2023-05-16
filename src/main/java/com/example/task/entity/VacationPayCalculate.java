package com.example.task.entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
@Component
@Data
@Getter
@Setter
public class VacationPayCalculate {
    private Double avgSalary;
    private LocalDate dateStartOfVacation;
    private LocalDate dateEndOffVacation;
}
