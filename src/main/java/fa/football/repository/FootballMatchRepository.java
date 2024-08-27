package fa.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.football.entity.FootballMatch;
import fa.football.entity.MatchEvent;

@Repository
public interface FootballMatchRepository extends JpaRepository<FootballMatch, Integer> {
	List<FootballMatch> findByLeague_LeagueId(int leagueId);

	List<FootballMatch> findByLeague_LeagueIdAndStage_StageId(int leagueId, int stageId);

	FootballMatch findById(int id);

}
