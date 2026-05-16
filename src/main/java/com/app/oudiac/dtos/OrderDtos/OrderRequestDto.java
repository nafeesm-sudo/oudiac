package com.app.oudiac.dtos.OrderDtos;

import com.app.oudiac.models.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDto {
    @NotNull
    private String shippingAddress;

    @NotNull
    private PaymentMethod paymentMethod;

    private String notes;

    @NotEmpty
    private List<OrderItemDTO> items;

}
