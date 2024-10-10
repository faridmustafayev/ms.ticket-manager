package az.ingress.service.specification;

import az.ingress.dao.entity.MeetupEntity;
import az.ingress.model.criteria.MeetupCriteria;
import az.ingress.util.PredicateUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static az.ingress.dao.entity.MeetupEntity.Fields.*;
import static az.ingress.dao.entity.MeetupEntity.Fields.price;
import static az.ingress.util.PredicateUtil.applyLikePattern;

@AllArgsConstructor
public class MeetupSpecification implements Specification<MeetupEntity> {
    private MeetupCriteria meetupCriteria;

    public Predicate toPredicate(@NonNull Root<MeetupEntity> root,
                                 @NonNull CriteriaQuery<?> query,
                                 @NonNull CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .addNullSafety(meetupCriteria.getName(),
                        name -> cb.like(cb.lower(root.get(name)), applyLikePattern(name.toLowerCase())))
                .addNullSafety(meetupCriteria.getPriceFrom(),
                        priceFrom -> cb.greaterThanOrEqualTo(root.get(price), priceFrom))
                .addNullSafety(meetupCriteria.getPriceTo(),
                        priceTo -> cb.lessThanOrEqualTo(root.get(price), priceTo))
                .addNullSafety(meetupCriteria.getEventDate(),
                        eventDate -> cb.lessThan(root.get(MeetupEntity.Fields.eventDate), eventDate))
                .build();
        return cb.and(predicates);
    }
}