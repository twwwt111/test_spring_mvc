package fa.football.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fa.football.entity.League;

import java.util.List;

@Repository
public interface LeagueRepository extends JpaRepository<League, Integer> {
    List<League> findByFormat(String format);
    @Query("SELECT l FROM League l WHERE l.stage.stageId = :stageId")
    List<League> findByStageId(@Param("stageId") Integer stageId);
    List<League> findByLeagueNameContaining(String keyword);
}
