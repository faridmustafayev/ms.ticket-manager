package az.ingress.dao.entity;

import az.ingress.annotation.GeneratedUUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {
    @Id
    @GeneratedUUID
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String customerFullName;

    private Long accountId;

    private Long meetupId;

    private Long paymentId;

    private BigDecimal price;

    private LocalDateTime reserveDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var ticketEntity = (TicketEntity) o;
        return Objects.equals(id, ticketEntity.id);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }
}