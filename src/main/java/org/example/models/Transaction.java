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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Transaction transaction) {
            return getThing().equals(transaction.getThing()) &&
                   getAmount().equals(transaction.getAmount()) &&
                   getDate().equals(transaction.getDate()) &&
                   getDestinator().equals(transaction.getDestinator());
        }
        return false;
    }
}
