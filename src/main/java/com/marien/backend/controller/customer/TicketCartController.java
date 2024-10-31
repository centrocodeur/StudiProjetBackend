package com.marien.backend.controller.customer;


import com.marien.backend.dto.*;
import com.marien.backend.exceptions.ValidationException;
import com.marien.backend.services.customer.cart.CartService;
import com.marien.backend.services.customer.ticketCart.TicketCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class TicketCartController {


    private final TicketCartService cartService;

    @PostMapping("/ticket_cart")
    public ResponseEntity<?> addTicketToCart(@RequestBody AddTicketInCartDto addTicketInCartDto){
        return cartService.addTicketToCart(addTicketInCartDto);
    }

    @GetMapping("/ticket_cart/{userId}")
    public ResponseEntity<?>getCartByUserId(@PathVariable Long userId){
        TicketOrderDto orderDto = cartService.getCartByUSerId(userId);

        return ResponseEntity.status(HttpStatus.OK).body(orderDto);
    }

    @GetMapping("/ticket/coupon/{userId}/{code}")
    public ResponseEntity<?> applyCoupon(@PathVariable Long userId, @PathVariable String code){
        try {
            TicketOrderDto orderDto= cartService.applyCoupon(userId, code);
            return ResponseEntity.ok(orderDto);
        }catch (ValidationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/ticket/addition")
    public ResponseEntity<TicketOrderDto> increaseProductQuantity(@RequestBody AddTicketInCartDto addTicketInCartDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.increaseProductQuantity(addTicketInCartDto));
    }


    @PostMapping("/ticket/deduction")
    public ResponseEntity<TicketOrderDto> decreaseProductQuantity(@RequestBody AddTicketInCartDto addTicketInCartDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.decreaseProductQuantity(addTicketInCartDto));
    }


    @PostMapping("/ticket/placeOrder")
    public ResponseEntity<TicketOrderDto> placeOrder(@RequestBody TicketPlaceOrderDto placeOrderDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.placeOrder(placeOrderDto));
    }


    @GetMapping("/cart/myOrders/{userId}")
    public ResponseEntity<List<TicketOrderDto>> getMyPlacedOrders(@PathVariable Long userId){
        return ResponseEntity.ok(cartService.getMyPlacedOrders(userId));
    }
}
