package nvoip.api.repositories;

import nvoip.api.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByNumbersip(String numbersip);
    Optional<User> findIdByNumbersip(String numbersip);
    Optional<User> deleteByNumbersip(String numbersip);
    boolean existsByEmail(String email);
}
