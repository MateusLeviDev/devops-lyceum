package nvoip.api.repositories;

import nvoip.api.domains.LogsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogsTypeRepository extends JpaRepository<LogsType, Long> {
    Optional<LogsType> findByType(String type);
}
