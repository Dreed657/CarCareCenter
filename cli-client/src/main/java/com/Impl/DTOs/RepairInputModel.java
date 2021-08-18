package com.Impl.DTOs;

import com.Impl.DTOs.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairInputModel {
    private Long mileage;
    private Status status;
    private Long carId;

    @Override
    public String toString() {
        return "RepairInputModel{" +
                "mileage=" + mileage +
                ", status=" + status +
                ", carId=" + carId +
                '}';
    }
}
