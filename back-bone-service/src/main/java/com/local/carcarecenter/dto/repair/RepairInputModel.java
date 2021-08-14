package com.local.carcarecenter.dto.repair;

import com.local.carcarecenter.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairInputModel {
    private Long mileage;
    private Status status;
    private Long carId;
}
