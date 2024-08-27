package fa.football.services;

import java.util.List;
import java.util.Optional;

import fa.football.entity.League;

public interface LeagueService {
	List<League> getAllLeagues();
	League getLeagueById(int leagueId);
	League saveLeague(League league);
	void deleteLeague(int leagueId);
	List<League> getLeaguesByFormat(String format);
	List<League> getLeaguesByStage(int teamNumber);
	public List<League> searchLeagues(String keyword);
}
