package com.local.carcarecenter.model;

import com.local.carcarecenter.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Car extends AbstractBaseEntity {

    @Column(nullable = false, length = 50)
    private String VIN;

    @Column(nullable = false, length = 50)
    private String Manufacturer;

    @Column(nullable = false, length = 50)
    private String Model;

    @Column(nullable = false)
    private Integer Year;

    @Column(nullable = false)
    private EngineType type;

    @OneToMany
    private Collection<Repair> repairments;
}
