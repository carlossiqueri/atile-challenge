package com.atile_challenge.api.dtos.ticket;

import com.atile_challenge.api.models.ticket.TicketStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketUpdateDTO {
    
    private String title;

    private String description;

    private TicketStatus status;

}
