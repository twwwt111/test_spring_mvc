package fa.football.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fa.football.entity.Account;
import fa.football.entity.League;
import fa.football.entity.LeagueInvitation;
import fa.football.entity.LeagueInvitationPK;

public interface LeagueInvitationRepository extends JpaRepository<LeagueInvitation, LeagueInvitationPK> {
	// Lấy tất cả lời mời đang chờ (PENDING) cho một giải đấu cụ thể
    List<LeagueInvitation> findByLeagueAndStatus(League league, String status);

    // Lấy tất cả lời mời đang chờ (PENDING) cho một team manager cụ thể
    List<LeagueInvitation> findByTeamManagerAndStatus(Account teamManager, String status);
}
