package com.Impl.DTOs;

import com.Impl.DTOs.Enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepairDTO {

    private Long id;
    private Long mileage;
    private Status status;
    private Date createdAt;
    private BigDecimal totalPrice;
    private Collection<ItemDTO> itemDTOS;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        var dateFormat = new SimpleDateFormat("dd/mm/YYYY");
        var formattedDate = dateFormat.format(createdAt);

        sb.append("id=").append(id);
        sb.append(", mileage=").append(mileage);
        sb.append(", status=").append(status);
        sb.append(", createdAt=").append(formattedDate);
        sb.append(", totalPrice=").append(totalPrice);

        return sb.toString();
    }
}
