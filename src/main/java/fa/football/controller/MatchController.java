package fa.football.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fa.football.entity.FootballMatch;
import fa.football.entity.MatchEvent;
import fa.football.entity.Player;
import fa.football.services.FootballMatchService;

@Controller
@RequestMapping("/match")
public class MatchController {
	
	@Autowired
    private FootballMatchService footballMatchService;

	@GetMapping("/detail/{id}")
    public String detailMatch(@PathVariable("id")int matchId, Model model){
        FootballMatch footballMatch = footballMatchService.findMatchById(matchId);
        List<MatchEvent> eventMatch1 = footballMatchService.getEventsByMatchIdFirstTeam(matchId);
        List<MatchEvent> eventMatch2 = footballMatchService.getEventsByMatchIdSecondTeam(matchId);
        List<MatchEvent> eventMatchTime = footballMatchService.getEventsByMatchIdOrderByTime(matchId);
        List<Player> players1 = footballMatchService.findByTeam(footballMatch.getFirstTeam().getTeamId());
        List<Player> players2 = footballMatchService.findByTeam(footballMatch.getSecondTeam().getTeamId());

        model.addAttribute("match", footballMatch);
        model.addAttribute("eventMatch1", eventMatch1);
        model.addAttribute("eventMatch2", eventMatch2);
        model.addAttribute("eventMatchTime", eventMatchTime);
        model.addAttribute("players1",players1);
        model.addAttribute("players2",players2);
        model.addAttribute("events",footballMatchService.findAllEvent());

        return "match/match-detail";
    }

    @PostMapping("addEvent/{id}")
    public String addEvent(@PathVariable(name = "id")int matchId, @RequestParam(name = "playerId")int playerId, @RequestParam(name = "eventId")int eventId,
                           @RequestParam(name = "minute")int minute, @RequestParam(name = "description")String description){
    	footballMatchService.addEvent(matchId, playerId, eventId, minute, description);
        return "redirect:/match/detail/"+matchId;
    }
      
    @GetMapping("/edit/{id}")
    public String viewEditMatch(@PathVariable("id")int matchId, Model model){
        FootballMatch footballMatch = footballMatchService.findMatchById(matchId);
        List<MatchEvent> eventMatch1 = footballMatchService.getEventsByMatchIdFirstTeam(matchId);
        List<MatchEvent> eventMatch2 = footballMatchService.getEventsByMatchIdSecondTeam(matchId);
        List<MatchEvent> eventMatchTime = footballMatchService.getEventsByMatchIdOrderByTime(matchId);
        List<Player> players1 = footballMatchService.findByTeam(footballMatch.getFirstTeam().getTeamId());
        List<Player> players2 = footballMatchService.findByTeam(footballMatch.getSecondTeam().getTeamId());

        model.addAttribute("match", footballMatch);
        model.addAttribute("eventMatch1", eventMatch1);
        model.addAttribute("eventMatch2", eventMatch2);
        model.addAttribute("eventMatchTime", eventMatchTime);
        model.addAttribute("players1",players1);
        model.addAttribute("players2",players2);
        model.addAttribute("events",footballMatchService.findAllEvent());

        return "match/match-edit";
    }
    
    @PostMapping("/ edit/{id}")
    public String editMatch(@PathVariable(name = "id")int matchId, @RequestParam(name = "playerId")int playerId, @RequestParam(name = "eventId")int eventId,
                           @RequestParam(name = "minute")int minute, @RequestParam(name = "description")String description){
    	footballMatchService.addEvent(matchId, playerId, eventId, minute, description);
        return "redirect:/match/detail/"+matchId;
    }
}
