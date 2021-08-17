package com.local.carcarecenter.dto.car;

import com.local.carcarecenter.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

// TODO: ADD VALIDATION
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarInputModel {

    @NotNull(message = "You must provide VIN number!")
//    @Pattern(regexp = "^[A-HJ-NPR-Za-hj-npr-z\\d]{8}[\\dX][A-HJ-NPR-Za-hj-npr-z\\d]{2}\\d{6}$", message = "Must provide valid VIN number")
    private String VIN;

    @NotNull(message = "You must provide car's manufacturer!")
    private String Manufacturer;

    @NotNull(message = "You must provide car's model!")
    private String Model;

    @NotNull(message = "You must provide year of manufacturing!")
    private Integer Year;

    @NotNull(message = "You must provide engine type!")
    private EngineType type;
}
