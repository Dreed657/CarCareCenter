package com.Impl.DTOs;

import com.Impl.DTOs.Enums.Metric;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long id;
    private String description;
    private Integer quantity;
    private Metric metric;
    private Date createdAt;
    private BigDecimal price = new BigDecimal("0.0");
}
