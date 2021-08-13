package com.local.carcarecenter.dto.car;

import com.local.carcarecenter.dto.repair.RepairViewModel;
import com.local.carcarecenter.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarViewModel {

    // May should be hidden
    private Integer id;

    private String VIN;

    private String Manufacturer;

    private String Model;

    private Integer Year;

    private EngineType type;

    private Date createdAt;

    private Collection<RepairViewModel> repairments;
}
