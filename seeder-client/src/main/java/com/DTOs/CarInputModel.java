package com.DTOs;

import com.Enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarInputModel {

    private String vin;
    private String manufacturer;
    private String model;
    private Integer year;
    private EngineType type;

    @Override
    public String toString() {
        return "CarInputModel{" +
                "vin='" + vin + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", type=" + type +
                '}';
    }
}
