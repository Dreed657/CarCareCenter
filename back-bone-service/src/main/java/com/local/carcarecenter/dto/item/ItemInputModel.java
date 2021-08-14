package com.local.carcarecenter.dto.item;

import com.local.carcarecenter.model.enums.Metric;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemInputModel {
    private String description;
    private Integer quantity;
    private Metric metric;
    private Long repairId;
}
