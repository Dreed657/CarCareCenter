package com.local.carcarecenter.model;

import com.local.carcarecenter.model.enums.Metric;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item extends AbstractBaseEntity {

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Metric metric;

    @ManyToOne
    private Repair repair;
}
