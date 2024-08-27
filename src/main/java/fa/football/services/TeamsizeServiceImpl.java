package fa.football.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.football.entity.Teamsize;
import fa.football.repository.TeamsizeRepository;

@Service
public class TeamsizeServiceImpl implements TeamsizeService {

	@Autowired
	private TeamsizeRepository teamsizeRepository;
	
	@Override
	public List<Teamsize> getAllTeamsizes() {
		return teamsizeRepository.findAll();
	}

}
