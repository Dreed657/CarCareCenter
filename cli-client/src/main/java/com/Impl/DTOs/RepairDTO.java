package com.Impl.DTOs;

import com.Impl.DTOs.Enums.Status;
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
public class RepairDTO {

    private Long id;
    private Long mileage;
    private Status status;
    private Date createdAt;
    private Collection<ItemDTO> itemDTOS;
}
