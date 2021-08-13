package com.local.carcarecenter.model;

import com.local.carcarecenter.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Car {

    public Car(){
        super();
        this.createdAt = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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

    @OneToMany
    private Collection<Repair> repairments;
}
