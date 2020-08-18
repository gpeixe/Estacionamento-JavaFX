package DTO.Funcionarios;

import DTO.Relatorio.RelatorioDTO;
import DTO.Ticket.TicketClienteDTO;
import DTO.Ticket.TicketDTO;
import DTO.Ticket.TicketMensalistaDTO;

import java.util.List;

public class AdministradorDTO {
    public AdministradorDTO() {}

    //Implementação da geração de ticket do cliente comum será feita aqui
    public TicketClienteDTO generateClienteTicket(TicketDTO ticketDTO, boolean isNight){
        return new TicketClienteDTO();
    }

    //Implementação da geração de ticket do mensalista comum será feita aqui
    public TicketMensalistaDTO generateMensalistaTicket(TicketDTO ticketDTO){
        return new TicketMensalistaDTO();
    }

    //Implementação da mundança de preço será feita aqui
    public void updatePrices( double newHourPrice, double newNightPrice){

    }

    //Implementação da geração de relatorio vai aqui
    public RelatorioDTO generateRelatorio(List<String> filters){
        return new RelatorioDTO();
    }

}
