package com.local.carcarecenter.model;

import com.local.carcarecenter.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Car {

    public Car() {
        super();
        this.createdAt = new Date();
    }

    public Car(String VIN, String manufacturer, String model, Integer year, EngineType type) {
        this.VIN = VIN;
        this.Manufacturer = manufacturer;
        this.Model = model;
        this.Year = year;
        this.type = type;
        this.repairments = new HashSet<>();
        this.createdAt = new Date();
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 17)
    private String VIN;

    @Column(nullable = false, length = 50)
    private String Manufacturer;

    @Column(nullable = false, length = 50)
    private String Model;

    @Column(nullable = false)
    private Integer Year;

    @Column(nullable = false)
    private EngineType type;

    @Column
    private Date createdAt;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "repair_id", foreignKey = @ForeignKey(name = "fk_car2rep"))
    private Set<Repair> repairments = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id.equals(car.id) && VIN.equals(car.VIN) && Manufacturer.equals(car.Manufacturer) && Model.equals(car.Model) && Year.equals(car.Year) && type == car.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, VIN, Manufacturer, Model, Year, type);
    }
}
