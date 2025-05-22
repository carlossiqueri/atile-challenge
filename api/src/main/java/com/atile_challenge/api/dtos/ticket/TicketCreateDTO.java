package com.atile_challenge.api.dtos.ticket;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketCreateDTO {
    
    @NotBlank(message = "Title is mandatory.")
    private String title;

    @NotBlank(message = "Description is mandatory.")
    private String description;
    
}
