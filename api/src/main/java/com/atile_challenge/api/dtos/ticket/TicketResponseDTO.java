package com.atile_challenge.api.dtos.ticket;

import java.time.LocalDateTime;
import com.atile_challenge.api.models.ticket.TicketStatus; 
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketResponseDTO {

    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private LocalDateTime createdAt;

}