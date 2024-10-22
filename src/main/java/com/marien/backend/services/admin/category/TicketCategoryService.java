package com.marien.backend.services.admin.category;

import com.marien.backend.dto.TicketCategoryDto;
import com.marien.backend.entity.TicketCategory;

import java.util.List;

public interface TicketCategoryService {

    TicketCategory createTicketCategory (TicketCategoryDto ticketCategoryDto);

    List<TicketCategory> getAllTicketCategories();


}
