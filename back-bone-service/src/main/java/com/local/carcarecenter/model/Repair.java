package com.local.carcarecenter.model;

import com.local.carcarecenter.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Repair extends AbstractBaseEntity {

    @Column(nullable = false)
    private Long mileage;

    @Column(nullable = false)
    private Status status;

    @ManyToOne
    private Car car;

    @OneToMany
    private Collection<Item> items;
}
