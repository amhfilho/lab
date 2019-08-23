package com.amhfilho.ow.winemanager;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class StockEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    private BigDecimal price;
    private StockEntryType type;
    @ManyToOne
    private Stock stock;

    protected  StockEntry(){ }

    public StockEntry(Integer amount, BigDecimal price, StockEntryType type, Stock stock) {
        this.amount = amount;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }
}
