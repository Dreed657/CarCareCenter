package com.local.carcarecenter.model;

import com.local.carcarecenter.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Repair {

    public Repair(){
        super();
        this.createdAt = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Long mileage;

    @Column(nullable = false)
    private Status status;

    @Column
    private Date createdAt;

    @ManyToOne
    private Car car;

    @OneToMany
    private Collection<Item> items;
}
