package az.ingress.controller;

import az.ingress.model.request.TicketRequest;
import az.ingress.model.response.TicketResponse;
import az.ingress.service.abstraction.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void reserveTicket(@RequestBody TicketRequest request) {
        ticketService.saveTicket(request);
    }

    @GetMapping
    public List<TicketResponse> getAllTicketsByAccountId(@RequestParam Long accountId) {
        return ticketService.getTicketsByAccountId(accountId);
    }

}