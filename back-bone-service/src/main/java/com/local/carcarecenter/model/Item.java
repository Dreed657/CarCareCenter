package com.local.carcarecenter.model;

import com.local.carcarecenter.model.enums.Metric;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Item {

    public Item() {
        super();
        this.createdAt = new Date();
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Metric metric;

    @Column
    private Date createdAt;
}
