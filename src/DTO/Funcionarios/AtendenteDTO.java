package DTO.Funcionarios;

import DTO.Ticket.TicketClienteDTO;
import DTO.Ticket.TicketDTO;
import DTO.Ticket.TicketMensalistaDTO;

public class AtendenteDTO extends FuncionarioDTO {
    public AtendenteDTO() {}

    //Implementação da geração de ticket do cliente comum será feita aqui
    public TicketClienteDTO generateClienteTicket(TicketDTO ticketDTO, boolean isNight){
        return new TicketClienteDTO();
    }

    //Implementação da geração de ticket do mensalista comum será feita aqui
    public TicketMensalistaDTO generateMensalistaTicket(TicketDTO ticketDTO){
        return new TicketMensalistaDTO();
    }
}
