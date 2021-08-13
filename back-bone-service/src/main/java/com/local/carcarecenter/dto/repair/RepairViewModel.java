package com.local.carcarecenter.dto.repair;

import com.local.carcarecenter.dto.item.ItemViewModel;
import com.local.carcarecenter.model.enums.Status;
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
public class RepairViewModel {
    private Integer id;
    private Long mileage;
    private Status status;
    private Date createdAt;
    private Collection<ItemViewModel> items;
}
