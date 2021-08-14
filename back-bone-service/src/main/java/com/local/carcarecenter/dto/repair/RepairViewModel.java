package com.local.carcarecenter.dto.repair;

import com.local.carcarecenter.dto.item.ItemViewModel;
import com.local.carcarecenter.model.enums.Status;
import lombok.*;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairViewModel {
    private Integer id;
    private Long mileage;
    private Status status;
    private Date createdAt;
    private Set<ItemViewModel> items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepairViewModel that = (RepairViewModel) o;
        return id.equals(that.id) && mileage.equals(that.mileage) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mileage, status);
    }
}
