package fa.football.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.football.entity.League;
import fa.football.repository.LeagueRepository;

@Service
public class LeagueServiceImpl implements LeagueService {

	@Autowired
	private LeagueRepository leagueRepository;
	
	@Override
	public List<League> getAllLeagues() {
		return leagueRepository.findAll();
	}

	@Override
	public League getLeagueById(int leagueId) {
		return leagueRepository.findById(leagueId).orElse(null);
	}

	@Override
	public League saveLeague(League league) {
		return leagueRepository.save(league);
	}

	@Override
	public void deleteLeague(int leagueId) {
		leagueRepository.deleteById(leagueId);
	}

	@Override
	public List<League> getLeaguesByFormat(String format) {
		return leagueRepository.findByFormat(format);
	}

	@Override
	public List<League> getLeaguesByStage(int teamNumber) {
		return leagueRepository.findByStageId(teamNumber);
	}

	@Override
	public List<League> searchLeagues(String keyword) {
		return leagueRepository.findByLeagueNameContaining(keyword);
	}

}
