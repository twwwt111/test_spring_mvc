package fa.football.services;

import java.util.List;

public interface StandingService {
	List<String> findDistinctStandingsByLeague(int leagueId);
    void addTeamsToGroup(String groupName, List<Integer> teamIds, int leagueId);
    
}
