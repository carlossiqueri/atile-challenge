package com.atile_challenge.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.atile_challenge.api.controllers.TicketController;
import com.atile_challenge.api.dtos.ticket.TicketCreateDTO;
import com.atile_challenge.api.dtos.ticket.TicketResponseDTO;
import com.atile_challenge.api.dtos.ticket.TicketUpdateDTO;
import com.atile_challenge.api.exceptions.ResourceNotFoundException;
import com.atile_challenge.api.models.ticket.TicketStatus;
import com.atile_challenge.api.services.ticket.TicketService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDateTime;
import java.util.Collections;

@WebMvcTest(TicketController.class)
@Import(LocalValidatorFactoryBean.class) 
class TicketUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketService ticketService;

    @Autowired
    private ObjectMapper objectMapper;

    private TicketCreateDTO createDTO;
    private TicketResponseDTO responseDTO;
    private TicketUpdateDTO updateDTO;

    @BeforeEach
    void setup() {
        createDTO = new TicketCreateDTO("Test Ticket", "Test Description");
        responseDTO = new TicketResponseDTO(1L, "Test Ticket", "Test Description", 
            TicketStatus.OPEN, LocalDateTime.now());
        updateDTO = new TicketUpdateDTO("Updated Title", "Updated Description", 
            TicketStatus.IN_PROGRESS);
    }

    @Test
    @DisplayName("POST /tickets - Should return 201 Created when ticket is valid.")
    void createTicket_ShouldReturnCreatedResponse() throws Exception {
        doNothing().when(ticketService).createTicket(any(TicketCreateDTO.class));

        mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createDTO)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message").value("Ticket created successfully."));

        verify(ticketService, times(1)).createTicket(any(TicketCreateDTO.class));
    }

    @Test
    @DisplayName("POST /tickets - Should return 400 Bad Request when missing title.")
    void createTicket_ShouldReturnBadRequest_WhenMissingTitle() throws Exception {
        TicketCreateDTO invalidDTO = new TicketCreateDTO("", "Test Description");

        mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDTO)))
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error").exists());
        
        verify(ticketService, never()).createTicket(any());
    }

    @Test
    @DisplayName("GET /tickets - Should return 200 OK when returning tickets.")
    void listTickets_ShouldReturnOkWithList() throws Exception {
        when(ticketService.listTickets()).thenReturn(Collections.singletonList(responseDTO));

        mockMvc.perform(get("/tickets"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(responseDTO.getId()))
            .andExpect(jsonPath("$[0].title").value(responseDTO.getTitle()));

        verify(ticketService, times(1)).listTickets();
    }

    @Test
    @DisplayName("GET /tickets/{id} - Should return 200 OK when returning a ticket by id.")
    void listTicketById_ShouldReturnOkWithTicket() throws Exception {
        when(ticketService.listTicketById(1L)).thenReturn(responseDTO);

        mockMvc.perform(get("/tickets/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(responseDTO.getId()))
            .andExpect(jsonPath("$.title").value(responseDTO.getTitle()));

        verify(ticketService, times(1)).listTicketById(1L);
    }

    @Test
    @DisplayName("GET /tickets/{id} - Should return 404 Not Found when returning a ticket with invalid id.")
    void listTicketById_ShouldReturnNotFound_WhenTicketNotExists() throws Exception {
        when(ticketService.listTicketById(99L))
            .thenThrow(new ResourceNotFoundException("Ticket not found"));

        mockMvc.perform(get("/tickets/99"))
            .andExpect(status().isNotFound());

        verify(ticketService, times(1)).listTicketById(99L);
    }

    @Test
    @DisplayName("PUT /tickets/{id} - Should return 200 OK with updated ticket data.")
    void updateTicket_ShouldReturnUpdatedTicket() throws Exception {
        TicketResponseDTO updatedResponse = new TicketResponseDTO(1L,
            updateDTO.getTitle(),         
            updateDTO.getDescription(),   
            updateDTO.getStatus(),        
            responseDTO.getCreatedAt()); 

        when(ticketService.updateTicket(eq(1L), any(TicketUpdateDTO.class)))
            .thenReturn(updatedResponse);

        mockMvc.perform(put("/tickets/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(updatedResponse.getId()))
            .andExpect(jsonPath("$.title").value(updatedResponse.getTitle()))
            .andExpect(jsonPath("$.status").value(updatedResponse.getStatus().toString()));

        verify(ticketService, times(1)).updateTicket(eq(1L), any(TicketUpdateDTO.class));
    }

    @Test
    @DisplayName("PUT /tickets/{id} - Should return 404 Not Found when ticket to update does not exist")
    void updateTicket_ShouldReturnNotFound_WhenTicketNotExists() throws Exception {
        when(ticketService.updateTicket(eq(99L), any(TicketUpdateDTO.class)))
            .thenThrow(new ResourceNotFoundException("Ticket with id 99 not found"));

        mockMvc.perform(put("/tickets/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateDTO)))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("DELETE /tickets/{id} - Should return 200 OK when deleting a ticket.")
    void deleteTicket_ShouldReturnSuccessMessage() throws Exception {
        doNothing().when(ticketService).deleteTicket(1L);

        mockMvc.perform(delete("/tickets/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message").exists());

        verify(ticketService, times(1)).deleteTicket(1L);
    }

    @Test
    @DisplayName("DELETE /tickets/{id} - Should return 404 Not Found when ticket to delete does not exist")
    void deleteTicket_ShouldReturnNotFound_WhenTicketNotExists() throws Exception {
        doThrow(new ResourceNotFoundException("Ticket with id 99 not found"))
            .when(ticketService).deleteTicket(99L);

        mockMvc.perform(delete("/tickets/99"))
            .andExpect(status().isNotFound());
    }
}