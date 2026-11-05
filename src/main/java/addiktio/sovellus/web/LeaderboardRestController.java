package addiktio.sovellus.web;

import org.springframework.web.bind.annotation.RestController;

import addiktio.sovellus.domain.AppUser;
import addiktio.sovellus.domain.AppUserRepository;
import addiktio.sovellus.domain.LeaderboardStreak;
import addiktio.sovellus.service.StreakService;
import ch.qos.logback.core.model.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController 


public class LeaderboardRestController {

    private final AppUserRepository appUserRepository;
    private final StreakService streakService;

    public LeaderboardRestController(AppUserRepository appUserRepository, StreakService streakService) {
        this.appUserRepository = appUserRepository;
        this.streakService = streakService;
    }

    @GetMapping("/api/leaderboard")
    public List<LeaderboardStreak> findAllStreak() {

        List<LeaderboardStreak> streaks = new  ArrayList<>();

         for(AppUser k : appUserRepository.findAll()){
            streaks.add(new LeaderboardStreak(k.getKayttajanimi(), k.getSubstance().getNimi(), 
            streakService.calculateStreak(k)));
         }
         streaks.sort(Comparator.comparingInt(LeaderboardStreak::getPutki).reversed());
        return streaks;
        
    }
    
    
}
