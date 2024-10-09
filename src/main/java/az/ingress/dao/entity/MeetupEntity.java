package az.ingress.dao.entity;

import az.ingress.annotation.GeneratedUUID;
import az.ingress.model.enums.MeetupStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Entity
@Table(name = "meetups")
@Where(clause = "status <> 'CLOSED'")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class MeetupEntity {
    @Id
    @GeneratedUUID
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime eventDate;

    @Enumerated(STRING)
    private MeetupStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var meetupEntity = (MeetupEntity) o;
        return Objects.equals(id, meetupEntity.id);
    }

    public int hashCode() {
        return Objects.hashCode(id);
    }
}