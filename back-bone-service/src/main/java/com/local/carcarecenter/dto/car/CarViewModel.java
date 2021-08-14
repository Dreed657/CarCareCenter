package com.local.carcarecenter.dto.car;

import com.local.carcarecenter.dto.repair.RepairViewModel;
import com.local.carcarecenter.model.enums.EngineType;
import lombok.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

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

    private Set<RepairViewModel> repairments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarViewModel that = (CarViewModel) o;
        return id.equals(that.id) && VIN.equals(that.VIN) && Manufacturer.equals(that.Manufacturer) && Model.equals(that.Model) && Year.equals(that.Year) && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, VIN, Manufacturer, Model, Year, type);
    }
}
