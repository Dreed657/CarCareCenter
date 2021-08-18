package com.Impl.DTOs;

import com.Impl.DTOs.Enums.Metric;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemInputModel {
    private String description;
    private Integer quantity;
    private Metric metric;
    private BigDecimal price;
    private Long repairId;

    @Override
    public String toString() {
        return "ItemInputModel{" +
                "description='" + description + '\'' +
                ", quantity=" + quantity +
                ", metric=" + metric +
                ", price=" + price +
                ", repairId=" + repairId +
                '}';
    }
}
