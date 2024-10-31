package com.marien.backend.repository;


import com.marien.backend.entity.CartItems;
import com.marien.backend.entity.TicketCartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketCartItemsRepository extends JpaRepository<TicketCartItems, Long> {

   // Optional<TicketCartItems> findByTicketIdAndTicketOrderIdAndUserId(Long ticketId, Long ticketOrderId, Long userId);
    Optional<TicketCartItems> findByTicketIdAndOrderIdAndUserId(Long ticketId, Long orderId, Long userId);

}
