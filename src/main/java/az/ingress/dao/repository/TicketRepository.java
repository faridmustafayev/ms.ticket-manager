package az.ingress.dao.repository;

import az.ingress.dao.entity.TicketEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends CrudRepository<TicketEntity, UUID> {

    List<TicketEntity> findTicketsByAccountId(Long accountId);
}