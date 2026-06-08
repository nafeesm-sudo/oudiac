package com.app.oudiac.services.orderService;

import com.app.oudiac.dtos.OrderDtos.OrderItemDTO;
import com.app.oudiac.dtos.OrderDtos.OrderRequestDto;
import com.app.oudiac.models.*;
import com.app.oudiac.repositories.OrderRepository;
import com.app.oudiac.repositories.ProductVariantRepository;
import com.app.oudiac.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private ProductVariantRepository productVariantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public ResponseEntity<?> placeOrder(OrderRequestDto dto, String username) {

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Order order = new Order();
        order.setUser(user);
        order.setShippingAddress(dto.getShippingAddress());
        order.setPaymentMethod(dto.getPaymentMethod());

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemDTO itemDTO : dto.getItems()) {

            ProductVariant product = productVariantRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemDTO.getQuantity());

            BigDecimal price = product.getSellingPrice(); // always from DB
            item.setPrice(price);

            totalAmount =totalAmount.add(price.multiply(BigDecimal.valueOf(itemDTO.getQuantity())));

            item.setOrder(order);
            orderItems.add(item);
        }

        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

         orderRepository.save(order);  //create order repo

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
