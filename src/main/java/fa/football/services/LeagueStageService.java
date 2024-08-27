package fa.football.services;

import java.util.List;

import fa.football.entity.LeagueStage;

public interface LeagueStageService {
	List<LeagueStage> getAllStages();

	LeagueStage getStageById(int stageId);
}
