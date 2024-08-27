package fa.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.football.entity.Standing;
import fa.football.entity.StandingPK;

@Repository
public interface StandingRepository extends JpaRepository<Standing, StandingPK> {
//	List<Standing> findByLeague_LeagueId(int leagueId);
	@Query("SELECT DISTINCT s.standingName FROM Standing s WHERE s.league.leagueId = :leagueId")
	List<String> findDistinctStandingNamesByLeagueId(@Param("leagueId") int leagueId);

	List<Standing> findByLeague_LeagueId(int leagueId);
}
