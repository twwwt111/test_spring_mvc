package fa.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fa.football.entity.LeagueStage;

@Repository
public interface LeagueStageRepository extends JpaRepository<LeagueStage, Integer> {
	
}
