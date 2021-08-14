package com.local.carcarecenter.model;

import com.local.carcarecenter.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Repair {

    public Repair() {
        super();
        this.createdAt = new Date();
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long mileage;

    @Column(nullable = false)
    private Status status;

    @Column
    private Date createdAt;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id", foreignKey = @ForeignKey(name = "fk_rep2item"))
    private Set<Item> items = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repair repair = (Repair) o;
        return id.equals(repair.id) && mileage.equals(repair.mileage) && status == repair.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mileage, status);
    }
}
