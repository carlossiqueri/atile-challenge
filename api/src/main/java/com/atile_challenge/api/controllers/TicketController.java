package com.atile_challenge.api.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atile_challenge.api.dtos.ticket.TicketCreateDTO;
import com.atile_challenge.api.dtos.ticket.TicketResponseDTO;
import com.atile_challenge.api.services.ticket.TicketService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Void> createTicket(@RequestBody @Valid TicketCreateDTO dto){
        ticketService.createTicket(dto);

        return ResponseEntity.created(null).build();
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> listTickets() {
        List<TicketResponseDTO> tickets = ticketService.listTickets();

        return ResponseEntity.ok().body(tickets);
    }

    @GetMapping("/{id}")
    public String listTicketById(@RequestParam String param) {
        return new String();
    }

    @PutMapping("/{id}")
    public String updateTicket(@PathVariable String id, @RequestBody String entity) {
        //TODO: process PUT request
        
        return entity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long id) {
        // Delete logic here
        return ResponseEntity.ok("Ticket with ID " + id + " deleted successfully");
    }

}
