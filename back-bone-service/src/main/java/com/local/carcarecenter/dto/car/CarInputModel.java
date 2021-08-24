package com.local.carcarecenter.dto.car;

import com.local.carcarecenter.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

// TODO: ADD VALIDATION
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarInputModel {

    // Pattern throws an warning
    //@Pattern(regexp = "^[A-HJ-NPR-Za-hj-npr-z\\d]{8}[\\dX][A-HJ-NPR-Za-hj-npr-z\\d]{2}\\d{6}$", message = "Must provide valid VIN number")
    @NotNull(message = "You must provide VIN number!")
    @Length(max = 17, message = "Must provide valid VIN number")
    private String VIN;

    @Length(min = 2, message = "Manufacturer must be at least 2 chars long")
    @NotNull(message = "You must provide car's manufacturer!")
    private String Manufacturer;

    @Length(min = 2, message = "Model must be must be at least 2 chars long")
    @NotNull(message = "You must provide car's model!")
    private String Model;

    @Range(min = 1900, max = 2021, message = "Year must be between 1900 and 2021")
    @NotNull(message = "You must provide year of manufacturing!")
    private Integer Year;

    @NotNull(message = "You must provide engine type!")
    private EngineType type;
}
