package org.example.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Purchase {
    private Long id;
    private String item;
    private BigDecimal price;
    private LocalDate date;
    private String seller;

    public Purchase(Long id, String item, BigDecimal price, LocalDate date, String seller) {
        this.id = id;
        this.item = item;
        this.price = price;
        this.date = date;
        this.seller = seller;
    }

    public Purchase(String item, BigDecimal price, LocalDate date, String seller) {
        this.item = item;
        this.price = price;
        this.date = date;
        this.seller = seller;
    }

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
