package com.app.oudiac.controllers;

import com.app.oudiac.dtos.OrderDtos.OrderRequestDto;
import com.app.oudiac.services.orderService.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place_order")
    public ResponseEntity<?> placeOrder(
            @RequestBody @Valid OrderRequestDto orderRequestDTO,
            Principal principal
    ) {
        return ResponseEntity.ok(
                orderService.placeOrder(orderRequestDTO, principal.getName())
        );
    }
}
