package com.atile_challenge.api.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atile_challenge.api.dtos.ticket.TicketCreateDTO;
import com.atile_challenge.api.dtos.ticket.TicketResponseDTO;
import com.atile_challenge.api.dtos.ticket.TicketUpdateDTO;
import com.atile_challenge.api.services.ticket.TicketService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    final TicketService ticketService;
    
    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createTicket(@RequestBody @Valid TicketCreateDTO dto){
        ticketService.createTicket(dto);
        
        Map<String, String> response = new HashMap<>();

        response.put("message", "Ticket created successfully.");


        return ResponseEntity.created(null).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> listTickets() {
        List<TicketResponseDTO> tickets = ticketService.listTickets();

        return ResponseEntity.ok().body(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> listTicketById(@PathVariable Long id) {
        TicketResponseDTO ticketById = ticketService.listTicketById(id);

        return ResponseEntity.ok().body(ticketById);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(@PathVariable Long id, @RequestBody @Valid TicketUpdateDTO dto) {
        TicketResponseDTO updatedTicket = ticketService.updateTicket(id, dto);
        
        return ResponseEntity.ok().body(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);

        Map<String, String> response = new HashMap<>();
        
        response.put("message", "Ticket with ID: " + id + " deleted successfully");
        return ResponseEntity.ok().body(response);
    }

}
