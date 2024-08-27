package fa.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.football.entity.Teamsize;

@Repository
public interface TeamsizeRepository extends JpaRepository<Teamsize, Integer> {

}
