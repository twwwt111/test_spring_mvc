package fa.football.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.football.entity.LeagueStage;
import fa.football.repository.LeagueStageRepository;

@Service
public class LeagueStageServiceImpl implements LeagueStageService {

	@Autowired
	private LeagueStageRepository leagueStageRepository;
	
	@Override
	public List<LeagueStage> getAllStages() {
		return leagueStageRepository.findAll();
	}

	@Override
	public LeagueStage getStageById(int stageId) {
		return leagueStageRepository.findById(stageId).orElse(null);
	}

}
