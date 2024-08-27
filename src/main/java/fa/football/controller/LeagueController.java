package fa.football.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.web.server.header.XFrameOptionsServerHttpHeadersWriter.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.football.entity.Account;
import fa.football.entity.FootballMatch;
import fa.football.entity.League;
import fa.football.entity.LeagueInvitation;
import fa.football.entity.LeagueInvitationPK;
import fa.football.entity.LeagueStage;
import fa.football.entity.Standing;
import fa.football.entity.Team;
import fa.football.entity.Teamsize;
import fa.football.services.AccountService;
import fa.football.services.FootballMatchService;
import fa.football.services.LeagueInvitationService;
import fa.football.services.LeagueService;
import fa.football.services.LeagueStageService;
import fa.football.services.StandingService;
import fa.football.services.TeamService;
import fa.football.services.TeamsizeService;

@Controller
@RequestMapping("/league")
public class LeagueController {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private LeagueService leagueService;
	
	@Autowired
	private TeamsizeService teamsizeService;
	
	@Autowired
	private LeagueStageService leagueStageService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private FootballMatchService footballMatchService;
	
	@Autowired
	private LeagueInvitationService leagueInvitationService;
	
	@Autowired
	private StandingService standingService;
	
	@GetMapping("/create")
	public String showFromCreateLeague(Model model) {
		List<Teamsize> teamsizes = teamsizeService.getAllTeamsizes();
		List<LeagueStage> leagueStage = leagueStageService.getAllStages();
		model.addAttribute("league", new League());
		model.addAttribute("stages", leagueStage);
		model.addAttribute("teamsizes", teamsizes);
		return "league/create";
	}
	
	@PostMapping("/save")
    public String saveLeague(@ModelAttribute("league") League league) {
        leagueService.saveLeague(league);
        return "redirect:/league/view-detail?leagueId="+ league.getLeagueId();
    }

	// Hiển thị danh sách các giải đấu
	@GetMapping("/list")
	public String showListOfLeague(
			@RequestParam(value = "format", required = false) String format,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "keyword", required = false) String keyword,
			Model model
	) {
		List<League> leagues;

		if (keyword != null && !keyword.isEmpty()) {
			// Tìm kiếm các giải đấu theo từ khóa
			leagues = leagueService.searchLeagues(keyword);
		} else {
			// Nếu không có từ khóa, tìm kiếm các giải đấu dựa trên các điều kiện còn lại
			leagues = leagueService.getAllLeagues();
		}

		// Lọc theo format nếu có
		if (format != null && !format.isEmpty()) {
			leagues = leagues.stream()
					.filter(league -> format.equals(league.getFormat().toLowerCase()))
					.collect(Collectors.toList());
			model.addAttribute("formatName", format);
		}

		// Lọc theo status nếu có
		if (status != null && !status.isEmpty()) {
			int statusInt = Integer.parseInt(status);
			leagues = leagues.stream()
					.filter(league -> league.getStage() != null && statusInt == league.getStage().getStageId())
					.collect(Collectors.toList());
			model.addAttribute("status", status);
		}

		model.addAttribute("leagues", leagues);
		return "league/list";
	}

	@GetMapping("/view-detail")
	public String viewDetailLeague(@RequestParam("leagueId") int leagueId, Model model) {
		League league = leagueService.getLeagueById(leagueId);
		List<LeagueStage> stageList = leagueStageService.getAllStages(); 
		List<Team> teams = teamService.getTeamsByLeagueId(leagueId);
		List<FootballMatch> footballMatchs = footballMatchService.getMatchesByLeagueId(leagueId);
		// chỗ này là đang lấy danh sách các vòng đấu từ danh sách trận đấu
	    Map<LeagueStage, List<FootballMatch>> matchesByStage = footballMatchs.stream().collect(Collectors.groupingBy(FootballMatch::getStage));
	    // sắp xếp các trận đấu theo thời gian
	    List<FootballMatch> sortedMatches = footballMatchs.stream()
	        .sorted(Comparator.comparing(FootballMatch::getMatchDatetime))
	        .collect(Collectors.toList());
	    List<LeagueStage> sortedStages = matchesByStage.keySet().stream()
	            .sorted(Comparator.comparing(LeagueStage::getStageId))
	            .collect(Collectors.toList());
	    
	    List<LeagueInvitation> pendingRequests = leagueInvitationService.getPendingRequestsForLeague(league);
        model.addAttribute("pendingRequests", pendingRequests);
        
        List<Team> availableTeams = teamService.findTeamsWithoutStanding(leagueId);
        List<String> existingGroups = standingService.findDistinctStandingsByLeague(leagueId);
  
        model.addAttribute("matchesByGroup", footballMatchService.getMatchesByGroup(leagueId));
		model.addAttribute("league", league);
		model.addAttribute("teams", teams);
		model.addAttribute("availableTeams", availableTeams);
		model.addAttribute("existingGroups", existingGroups);
		model.addAttribute("canCreateMatches", availableTeams.isEmpty());
		model.addAttribute("footballMatchs", footballMatchs);
		model.addAttribute("matchesByStage", matchesByStage);
		model.addAttribute("sortedMatches", sortedMatches);
		model.addAttribute("sortedStages", sortedStages);
		model.addAttribute("stageList", stageList);

		return "league/view-detail";
	}
	
	@GetMapping("/edit")
	public String showEditLeague(@PathVariable("leagueId") int leagueId, Model model) {
		League league = leagueService.getLeagueById(leagueId);
		if (league != null) {
			model.addAttribute("league", league);
			return "league/edit";
		} else {
			return "redirect:/league/list";
		}
	}
	
	@GetMapping("/delete")
	public String deleteLeague(@PathVariable("leagueId") int leagueId) {
		return "redirect:league/list";
	}
		
	// league manager chấp nhận yêu cầu
    @PostMapping("/accept-request")
    public String acceptRequest(@RequestParam("leagueId") int leagueId, @RequestParam("teamManagerId") String email) {
        LeagueInvitationPK id = new LeagueInvitationPK(leagueId, email);
        leagueInvitationService.acceptInvitation(id);
        return "redirect:/league/view-detail?leagueId=" + leagueId;
    }
    
 // League manager từ chối yêu cầu
    @PostMapping("/reject-request")
    public String rejectRequest(@RequestParam("leagueId") int leagueId, @RequestParam("teamManagerId") String email) {
    	LeagueInvitationPK id = new LeagueInvitationPK(leagueId, email);
        leagueInvitationService.rejectInvitation(id);
        return "redirect:/league/view-detail?leagueId=" + leagueId;
    }
    
    @PostMapping("/addGroup")
    public String saveGroup(@RequestParam("groupName") String groupName, @RequestParam("teamIds") List<Integer> teamIds, @RequestParam("leagueId") int leagueId) {
    	standingService.addTeamsToGroup(groupName, teamIds, leagueId);
    	return "redirect:/league/view-detail?leagueId=" + leagueId;
    }
    
    @PostMapping("/activate-league")
    public String activeLeague(@RequestParam("leagueId") int leagueId, @RequestParam("stageId") int stageId) {
    	League league = leagueService.getLeagueById(leagueId);
    	LeagueStage leagueStage = leagueStageService.getStageById(stageId);
    	league.setStage(leagueStage);
    	leagueService.saveLeague(league);
        return "redirect:/league/view-detail?leagueId=" + leagueId;
    }
    
    @PostMapping("/add-match")
    public String addMatch(@RequestParam("stageId") int stageId,
                           @RequestParam("firstTeamId") int firstTeamId,
                           @RequestParam("secondTeamId") int secondTeamId,
                           @RequestParam("matchDatetime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime matchDatetime,
                           @RequestParam("location") String location,
                           @RequestParam("leagueId") int leagueId) {

        FootballMatch match = new FootballMatch();
        match.setStage(leagueStageService.getStageById(stageId));
        match.setFirstTeam(teamService.findTeamById(firstTeamId));
        match.setSecondTeam(teamService.findTeamById(secondTeamId));
        match.setMatchDatetime(matchDatetime);
        match.setLocation(location);
        match.setLeague(leagueService.getLeagueById(leagueId));
        
        footballMatchService.saveMatch(match);

        return "redirect:/league/view-detail?leagueId=" + leagueId;
    }
    

	@GetMapping("/dashboard")
	public String viewDashBoard() {
		return "league/dashboard";
	}
	
	@GetMapping("/update-tournament")
	public String updateLeague() {
		return "league/update";
	}

}
