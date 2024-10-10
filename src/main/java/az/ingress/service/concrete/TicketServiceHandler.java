package az.ingress.service.concrete;

import az.ingress.client.mock.MockAccountClient;
import az.ingress.client.mock.MockPaymentClient;
import az.ingress.dao.entity.TicketEntity;
import az.ingress.dao.repository.TicketRepository;
import az.ingress.mapper.PaymentMapper;
import az.ingress.model.client.AccountResponse;
import az.ingress.model.request.TicketRequest;
import az.ingress.model.response.MeetupResponse;
import az.ingress.model.response.TicketResponse;
import az.ingress.service.abstraction.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static az.ingress.mapper.TicketMapper.TICKET_MAPPER;

@Service
@RequiredArgsConstructor
public class TicketServiceHandler implements TicketService {
    private final TicketRepository ticketRepository;

    private final MockAccountClient accountClient;

    private final MockPaymentClient paymentClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveTicket(TicketRequest request) {
        AccountResponse account = accountClient.getAccount(request.getAccountId());
        paymentClient.processPayment(PaymentMapper.PAYMENT_MAPPER.buildPaymentRequest(account));
        TicketEntity ticket = TICKET_MAPPER.buildTicketEntity(request);
        ticketRepository.save(ticket);
    }

    @Override
    public List<TicketResponse> getTicketsByAccountId(Long accountId) {
        List<TicketEntity> tickets = ticketRepository.findTicketsByAccountId(accountId);
        return tickets.stream()
                .map(TICKET_MAPPER::buildTicketResponse)
                .collect(Collectors.toList());
    }



}
