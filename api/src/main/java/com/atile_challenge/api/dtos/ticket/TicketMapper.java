package com.atile_challenge.api.dtos.ticket;

import com.atile_challenge.api.models.ticket.TicketModel;

public class TicketMapper {
    
    public static TicketResponseDTO ticketResponse(TicketModel ticket){
        return new TicketResponseDTO(
            ticket.getId(),
            ticket.getTitle(),
            ticket.getDescription(),
            ticket.getStatus(),
            ticket.getCreatedAt()
        );
    }
}
