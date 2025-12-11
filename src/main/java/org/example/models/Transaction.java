package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Transaction {
    private String thing;
    private BigDecimal amount;
    private LocalDate date;
    private String destinator;
}
