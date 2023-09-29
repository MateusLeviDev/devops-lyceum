package nvoip.api.repositories;

import nvoip.api.domains.Mistakes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MistakesRepository extends JpaRepository<Mistakes, Integer> {

}
