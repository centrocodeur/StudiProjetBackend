package com.marien.backend.services.admin.category;


import com.marien.backend.dto.TicketCategoryDto;
import com.marien.backend.entity.TicketCategory;
import com.marien.backend.repository.TicketCategoryRepository;
import com.marien.backend.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketCategoryServiceImpl implements TicketCategoryService {

    private final TicketCategoryRepository ticketCategoryRepository;

    public TicketCategory createTicketCategory (TicketCategoryDto ticketCategoryDto){

        TicketCategory ticketCategory = new TicketCategory();

        ticketCategory.setName(ticketCategoryDto.getName());
        ticketCategory.setDescription(ticketCategory.getDescription());

        return ticketCategoryRepository.save(ticketCategory);
    }

    public List<TicketCategory> getAllTicketCategories(){
        return ticketCategoryRepository.findAll();
    }


}
