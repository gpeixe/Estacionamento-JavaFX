package DTO.Relatorio;

import DTO.Ticket.TicketDTO;

import java.util.List;

public class RelatorioDTO {
    private List<TicketDTO> ticketDTOs;

    public List<TicketDTO> getTicketDTOs() {
        return ticketDTOs;
    }

    public void setTicketDTOs(List<TicketDTO> ticketDTOs) {
        this.ticketDTOs = ticketDTOs;
    }

    public RelatorioDTO() {}

    public void generateRelatorio(List<String> filters){

    }
}
