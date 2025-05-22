package com.atile_challenge.api.models.ticket;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketModel {
    
    private Long id;
    private String title;
    private String description;
    private TicketStatus status;
    private LocalDateTime createdAt;

}
