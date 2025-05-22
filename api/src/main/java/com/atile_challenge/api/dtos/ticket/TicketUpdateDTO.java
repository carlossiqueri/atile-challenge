package com.atile_challenge.api.dtos.ticket;

import com.atile_challenge.api.models.ticket.TicketStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketUpdateDTO {
    
    @NotBlank(message = "Title is mandatory.")
    private String title;

    @NotBlank(message = "Description is mandatory.")
    private String description;

    @NotBlank(message = "Status is mandatory.")
    private TicketStatus status;

}
