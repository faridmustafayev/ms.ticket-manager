package az.ingress.dao.repository;

import az.ingress.dao.entity.MeetupEntity;
import az.ingress.model.enums.MeetupStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface MeetupRepository extends JpaRepository<MeetupEntity, UUID>, JpaSpecificationExecutor<MeetupEntity> {

    Page<MeetupEntity> findAll(Specification<MeetupEntity> specification, @NonNull Pageable pageable);

    List<MeetupEntity> findAllByEventDateBeforeAndStatus(LocalDateTime eventDate, MeetupStatus status);
}