package az.ingress.service.abstraction;

import az.ingress.model.request.TicketRequest;
import az.ingress.model.response.TicketResponse;

import java.util.List;

public interface TicketService {
    void saveTicket(TicketRequest request);

    List<TicketResponse> getTicketsByAccountId(Long accountId);
}
