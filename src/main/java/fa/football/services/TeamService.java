package fa.football.services;

import java.util.List;

import fa.football.entity.Team;

public interface TeamService {
	List<Team> getAllTeam();
	Team findTeamById(int teamId);
	Team saveTeam(Team team);
	void addTeamToLeague(int leagueId, int teamId);
	
	void addTeamToLeague(int leagueId, Team team);
	void addMultipleTeamsToLeague(int leagueId, List<Team> teams);
	List<Team> getTeamWithoutLeague();
	List<Team> getTeamsByLeagueId(int leagueId);
	List<Team> findTeamsWithoutStanding(int leagueId);
}
