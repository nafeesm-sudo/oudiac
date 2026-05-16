package com.app.oudiac.dtos.OrderDtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemDTO {
    @NotNull
    private Long productId;

    @Min(1)
    private int quantity;
}
