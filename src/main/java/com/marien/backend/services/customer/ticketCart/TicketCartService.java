package com.marien.backend.services.customer.ticketCart;

import com.marien.backend.dto.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface TicketCartService {


    ResponseEntity<?> addTicketToCart(AddTicketInCartDto addTicketInCartDto);

    TicketOrderDto getCartByUSerId(Long userId);

    TicketOrderDto  applyCoupon(Long userId, String code);

    TicketOrderDto increaseProductQuantity(AddTicketInCartDto addTicketInCartDto);

    TicketOrderDto decreaseProductQuantity(AddTicketInCartDto addTicketInCartDto);
    TicketOrderDto  placeOrder(TicketPlaceOrderDto placeOrderDto);

    List<TicketOrderDto> getMyPlacedOrders(Long userId);

    TicketOrderDto  searchOrderByTrackingId(UUID trackingId);
}
