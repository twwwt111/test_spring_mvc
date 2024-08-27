package fa.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.football.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
	List<Team> findByLeague_LeagueId(int leagueId);
	List<Team> findByLeagueIsNull();
	@Query("SELECT t FROM Team t WHERE t.league.leagueId = :leagueId AND t.teamId NOT IN (SELECT s.team.teamId FROM Standing s WHERE s.league.leagueId = :leagueId)")
    List<Team> findTeamsWithoutStanding(@Param("leagueId") int leagueId);
}
