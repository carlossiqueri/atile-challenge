package com.atile_challenge.api.services.ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.atile_challenge.api.dtos.ticket.TicketCreateDTO;
import com.atile_challenge.api.dtos.ticket.TicketMapper;
import com.atile_challenge.api.dtos.ticket.TicketResponseDTO;
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


    public void listTickets(){

    }

    public void listTicketById(){

    }

    public void updateTicket(){

    }

    public void deleteTicket(){

    }

}
