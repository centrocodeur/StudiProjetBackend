package com.marien.backend.repository;

import com.marien.backend.entity.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Long> {
}
