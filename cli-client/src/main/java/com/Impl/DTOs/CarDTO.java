package com.Impl.DTOs;

import com.Impl.DTOs.Enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class CarDTO {
    private Long id;
    private String vin;
    private String Manufacturer;
    private String Model;
    private Integer Year;
    private EngineType type;
    private Date createdAt;
    private Collection<RepairDTO> repairments;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        var dateFormat = new SimpleDateFormat("dd/mm/YYYY");
        var formattedDate = dateFormat.format(createdAt);

        sb.append("id=").append(id);
        sb.append(", vin='").append(vin).append('\'');
        sb.append(", Manufacturer='").append(Manufacturer).append('\'');
        sb.append(", Model='").append(Model).append('\'');
        sb.append(", Year=").append(Year);
        sb.append(", type=").append(type);
        sb.append(", createdAt=").append(formattedDate);

        return sb.toString();
    }
}
