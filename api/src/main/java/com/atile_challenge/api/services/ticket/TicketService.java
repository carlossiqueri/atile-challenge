package com.atile_challenge.api.services.ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.atile_challenge.api.dtos.ticket.TicketCreateDTO;
import com.atile_challenge.api.dtos.ticket.TicketMapper;
import com.atile_challenge.api.dtos.ticket.TicketResponseDTO;
import com.atile_challenge.api.exceptions.ResourceNotFoundException;
import com.atile_challenge.api.models.ticket.TicketModel;
import com.atile_challenge.api.models.ticket.TicketStatus;

@Service
public class TicketService {

    private final List<TicketResponseDTO> tickets = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    
    public void createTicket(TicketCreateDTO dto){
        
        TicketModel ticket = new TicketModel(
            idCounter.getAndIncrement(),
            dto.getTitle(),
            dto.getDescription(),
            TicketStatus.OPEN,
            LocalDateTime.now()
        );

        TicketResponseDTO responseDTO = TicketMapper.ticketResponse(ticket);

        tickets.add(responseDTO);      

    }

    public List<TicketResponseDTO> listTickets(){
        return tickets;
    }

    public TicketResponseDTO listTicketById(Long id){

        TicketResponseDTO ticketById = tickets.stream()
            .filter(ticket -> ticket.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Ticket with id %d not found.", id)));

            return ticketById;
    }

    public void updateTicket(){

    }

    public void deleteTicket(){

    }

}
