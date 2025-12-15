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
public class Purchase {
    private String item;
    private BigDecimal price;
    private LocalDate date;
    private String seller;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Purchase purchase) {
            return getItem().equals(purchase.getItem()) &&
                   getPrice().equals(purchase.getPrice()) &&
                   getDate().equals(purchase.getDate()) &&
                   getSeller().equals(purchase.getSeller());
        }
        return false;
    }
}
