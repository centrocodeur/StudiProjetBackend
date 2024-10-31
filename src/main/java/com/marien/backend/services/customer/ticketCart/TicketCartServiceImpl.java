package com.marien.backend.services.customer.ticketCart;

import com.marien.backend.dto.*;
import com.marien.backend.entity.*;
import com.marien.backend.enums.OrderStatus;
import com.marien.backend.enums.TicketStatus;
import com.marien.backend.exceptions.ValidationException;
import com.marien.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketCartServiceImpl implements TicketCartService {



    @Autowired
    private TicketOrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketCartItemsRepository cartItemsRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CouponRepository couponRepository;

    public ResponseEntity<?> addTicketToCart(AddTicketInCartDto addTicketInCartDto){
        TicketOrder activeOrder = orderRepository.findByUserIdAndTicketOrderStatus(addTicketInCartDto.getUserId(), TicketStatus.RESERVE);
        Optional<TicketCartItems> optionalCartItems = cartItemsRepository.findByTicketIdAndOrderIdAndUserId(addTicketInCartDto.getTicketId(),
                activeOrder.getId(), addTicketInCartDto.getUserId());

        if(optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else {
            Optional<Ticket> optionalTicket= ticketRepository.findById(addTicketInCartDto.getTicketId());
            Optional<User> optionalUser= userRepository.findById(addTicketInCartDto.getUserId());

            if( optionalTicket.isPresent() && optionalUser.isPresent()){
                TicketCartItems cart = new TicketCartItems();
                cart.setTicket( optionalTicket.get());
                cart.setPrice( optionalTicket.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                TicketCartItems updateCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount()+ cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount()+ cart.getPrice());

                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }

    public TicketOrderDto getCartByUSerId(Long userId){
        TicketOrder activeOrder  = orderRepository.findByUserIdAndTicketOrderStatus(userId, TicketStatus.RESERVE);
        List<TicketCartItemsDto> cartItemsDtoList =activeOrder.getCartItems().stream().map(TicketCartItems::getCartDto).collect(Collectors.toList());

        TicketOrderDto orderDto= new TicketOrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setTicketOrderStatus(activeOrder.getTicketOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        orderDto.setTicketCartItems(cartItemsDtoList);

        if (activeOrder.getCoupon()!=null){
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDto;
    }


    public TicketOrderDto applyCoupon(Long userId, String code){
        TicketOrder activeOrder = orderRepository.findByUserIdAndTicketOrderStatus(userId, TicketStatus.RESERVE);
        Coupon coupon = couponRepository.findByCode(code).orElseThrow(() -> new ValidationException("Coupon not found."));

        if (couponIsExpired(coupon)){
            throw new ValidationException("Coupon has expired.");
        }

        double discountAmount = ((coupon.getDiscount()/100.0)* activeOrder.getTotalAmount());
        double netAmount = activeOrder.getTotalAmount()-discountAmount;

        activeOrder.setAmount(netAmount);
        activeOrder.setDiscount(discountAmount);
        activeOrder.setCoupon(coupon);

        orderRepository.save(activeOrder);

        return activeOrder.getOrderDto();
    }

    private boolean couponIsExpired(Coupon coupon){

        Date currentDate = new Date();
        Date expirationDate= coupon.getExpirationDate();

        return expirationDate !=null && currentDate.after(expirationDate);
    }

    public TicketOrderDto increaseProductQuantity(AddTicketInCartDto addTicketInCartDto){
       // Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        TicketOrder activeOrder = orderRepository.findByUserIdAndTicketOrderStatus(addTicketInCartDto.getUserId(), TicketStatus.RESERVE);
        Optional<Ticket> optionalTicket= ticketRepository.findById(addTicketInCartDto.getTicketId());

        Optional<TicketCartItems> optionalCartItem= cartItemsRepository.findByTicketIdAndOrderIdAndUserId(
                addTicketInCartDto.getTicketId(), activeOrder.getId(), addTicketInCartDto.getUserId()
        );

        if (optionalTicket.isPresent() && optionalCartItem.isPresent()){
            TicketCartItems cartItem =optionalCartItem.get();
            Ticket ticket= optionalTicket.get();

            activeOrder.setAmount(activeOrder.getAmount()+ ticket.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount()+ ticket.getPrice());

            cartItem.setQuantity(cartItem.getQuantity()+1);

            if (activeOrder.getCoupon()!=null){
                double discountAmount = ((activeOrder.getCoupon().getDiscount()/100.0)* activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount()-discountAmount;
                /*
                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);

                 */
                activeOrder.setAmount(netAmount);
                activeOrder.setDiscount(discountAmount);

            }
            cartItemsRepository.save(cartItem);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }

    public TicketOrderDto decreaseProductQuantity(AddTicketInCartDto addTicketInCartDto){
        //TicketOrder activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        TicketOrder activeOrder = orderRepository.findByUserIdAndTicketOrderStatus(addTicketInCartDto.getUserId(), TicketStatus.RESERVE);
        Optional<Ticket> optionalTicket= ticketRepository.findById(addTicketInCartDto.getTicketId());

        Optional<TicketCartItems> optionalCartItem= cartItemsRepository.findByTicketIdAndOrderIdAndUserId(
                addTicketInCartDto.getTicketId(), activeOrder.getId(), addTicketInCartDto.getUserId()
        );

        if (optionalTicket.isPresent() && optionalCartItem.isPresent()){
            TicketCartItems cartItem =optionalCartItem.get();
            Ticket ticket= optionalTicket.get();

            activeOrder.setAmount(activeOrder.getAmount() - ticket.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - ticket.getPrice());

            cartItem.setQuantity(cartItem.getQuantity()-1);

            if (activeOrder.getCoupon()!=null){
                double discountAmount = ((activeOrder.getCoupon().getDiscount()/100.0)* activeOrder.getTotalAmount());
                double netAmount = activeOrder.getTotalAmount()-discountAmount;

                activeOrder.setAmount(netAmount);
                activeOrder.setDiscount(discountAmount);

            }
            cartItemsRepository.save(cartItem);
            orderRepository.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }

    public TicketOrderDto placeOrder(TicketPlaceOrderDto placeOrderDto){
        TicketOrder activeOrder = orderRepository.findByUserIdAndTicketOrderStatus(placeOrderDto.getUserId(), TicketStatus.RESERVE);

        Optional<User> optionalUser = userRepository.findById(placeOrderDto.getUserId());
        if (optionalUser.isPresent()){
            activeOrder.setOrdersDescription(placeOrderDto.getOrderDescription());
            activeOrder.setEmail(placeOrderDto.getEmail());
            activeOrder.setDate(new Date());
            activeOrder.setTicketOrderStatus(TicketStatus.VENDU);
            activeOrder.setTrackingId(UUID.randomUUID());

            orderRepository.save(activeOrder);

            // Create a new cart for the user
            TicketOrder order= new TicketOrder();
            order.setAmount(0D);
            order.setTotalAmount(0D);
            order.setDiscount(0D);
            order.setUser(optionalUser.get());
            order.setTicketOrderStatus(TicketStatus.RESERVE);
            orderRepository.save(order);

            return activeOrder.getOrderDto();
        }

        return null;
    }

    public List<TicketOrderDto> getMyPlacedOrders(Long userId){

        return orderRepository.findByUserIdAndTicketOrderStatusIn(userId, List.of(TicketStatus.RESERVE, TicketStatus.VENDU))
                .stream().map(TicketOrder::getOrderDto).collect(Collectors.toList());

    }

    public TicketOrderDto searchOrderByTrackingId(UUID trackingId){
        Optional<TicketOrder> optionalOrder = orderRepository.findByTrackingId(trackingId);
        if (optionalOrder.isPresent()){
            return optionalOrder.get().getOrderDto();
        }

        return null;
    }

}
