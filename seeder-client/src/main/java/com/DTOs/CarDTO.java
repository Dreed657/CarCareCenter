package com.DTOs;

import com.Enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
}
