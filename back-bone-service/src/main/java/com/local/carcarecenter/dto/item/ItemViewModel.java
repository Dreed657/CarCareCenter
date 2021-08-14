package com.local.carcarecenter.dto.item;

import com.local.carcarecenter.model.enums.Metric;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemViewModel {
    private Integer id;
    private String description;
    private Integer quantity;
    private Metric metric;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemViewModel that = (ItemViewModel) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(quantity, that.quantity) && metric == that.metric;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, quantity, metric);
    }
}
