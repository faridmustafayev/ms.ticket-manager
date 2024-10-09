package az.ingress.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeetupCriteria {
    private String name;
    private Integer priceFrom;
    private Integer priceTo;
    private LocalDateTime eventDate;
}