package fa.football.services;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.football.entity.League;
import fa.football.entity.Team;
import fa.football.repository.LeagueRepository;
import fa.football.repository.TeamRepository;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private LeagueRepository leagueRepository;
	
	@Override
	public List<Team> getTeamsByLeagueId(int leagueId) {
		return teamRepository.findByLeague_LeagueId(leagueId);
	}

	@Override
	public List<Team> getAllTeam() {
		return teamRepository.findAll();
	}

	@Override
	public Team findTeamById(int teamId) {
		return teamRepository.findById(teamId).orElse(null);
	}

	@Override
	public Team saveTeam(Team team) {
		return teamRepository.save(team);
	}

	@Override
	public void addTeamToLeague(int leagueId, int teamId) {
		Team team = teamRepository.findById(teamId).orElse(null);
		League league = leagueRepository.findById(leagueId).orElse(null);
		team.setLeague(league);
		teamRepository.save(team);
	}

	@Override
	public List<Team> getTeamWithoutLeague() {
		return teamRepository.findByLeagueIsNull();
	}

	@Override
	public void addTeamToLeague(int leagueId, Team team) {
		League league = leagueRepository.findById(leagueId).orElse(null);
		team.setLeague(league);
		teamRepository.save(team);
	}

	@Override
	public void addMultipleTeamsToLeague(int leagueId, List<Team> teams) {
		League league = leagueRepository.findById(leagueId).orElse(null);
		for (Team team : teams) {
			team.setLeague(league);
			teamRepository.save(team);	
		}
	}

	@Override
	public List<Team> findTeamsWithoutStanding(int leagueId) {
		return teamRepository.findTeamsWithoutStanding(leagueId);
	}

}
