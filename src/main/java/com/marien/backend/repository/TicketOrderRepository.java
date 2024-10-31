package com.marien.backend.repository;


import com.marien.backend.entity.Order;
import com.marien.backend.entity.TicketOrder;
import com.marien.backend.enums.OrderStatus;
import com.marien.backend.enums.TicketStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketOrderRepository extends JpaRepository<TicketOrder, Long> {



    TicketOrder findByUserIdAndTicketOrderStatus(Long userId, TicketStatus ticketStatus);
    List<TicketOrder> findAllByTicketOrderStatusIn(List<TicketStatus> orderStatuses);

    List<TicketOrder> findByUserIdAndTicketOrderStatusIn(Long userId, List<TicketStatus> orderStatus);

    Optional<TicketOrder> findByTrackingId(UUID trackingId);

    //List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);
    List<TicketOrder> findByDateBetweenAndTicketOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);

    Long countByTicketOrderStatus(TicketStatus status);
}
