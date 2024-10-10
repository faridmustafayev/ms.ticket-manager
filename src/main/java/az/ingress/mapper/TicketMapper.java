package az.ingress.mapper;

import az.ingress.dao.entity.TicketEntity;
import az.ingress.model.client.AccountResponse;
import az.ingress.model.client.PaymentRequest;
import az.ingress.model.request.TicketRequest;
import az.ingress.model.response.TicketResponse;

public enum TicketMapper {
    TICKET_MAPPER;

    public TicketEntity buildTicketEntity(TicketRequest request) {
        return TicketEntity.builder()
                .customerFullName(request.getCustomerFullName())
                .accountId(request.getAccountId())
                .meetupId(request.getMeetupId())
                .paymentId(request.getPaymentId())
                .price(request.getPrice())
                .reserveDate(request.getReserveDate())
                .build();
    }

    public TicketResponse buildTicketResponse(TicketEntity ticket) {
        return TicketResponse.builder()
                .id(ticket.getId())
                .customerFullName(ticket.getCustomerFullName())
                .accountId(ticket.getAccountId())
                .meetupId(ticket.getMeetupId())
                .paymentId(ticket.getPaymentId())
                .price(ticket.getPrice())
                .reserveDate(ticket.getReserveDate())
                .createdAt(ticket.getCreatedAt())
                .updatedAt(ticket.getUpdatedAt())
                .build();
    }

}
