package addiktio.sovellus.web;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import addiktio.sovellus.domain.AppUser;
import addiktio.sovellus.domain.AppUserRepository;
import addiktio.sovellus.domain.DailyEntryRepository;
import addiktio.sovellus.domain.LeaderboardStreak;
import addiktio.sovellus.domain.SubstanceRepository;
import addiktio.sovellus.service.StreakService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 

public class LeaderboardController {
    private final DailyEntryRepository dailyEntryRepository;
    private final AppUserRepository appUserRepository;
    private final SubstanceRepository substanceRepository;
    private final StreakService streakService;

    public LeaderboardController(DailyEntryRepository dailyEntryRepository, AppUserRepository appUserRepository,
            SubstanceRepository substanceRepository, StreakService streakService) {
        this.dailyEntryRepository = dailyEntryRepository;
        this.appUserRepository = appUserRepository;
        this.substanceRepository = substanceRepository;
        this.streakService = streakService;
    }
    @GetMapping("/leaderboard")
    public String leaderBoard(Model model) {

        List<LeaderboardStreak> rivit = new ArrayList<>();
        for(AppUser kayttaja : appUserRepository.findAll()){
            rivit.add(new LeaderboardStreak(kayttaja.getKayttajanimi(), kayttaja.getSubstance().getNimi(),
             streakService.calculateStreak(kayttaja)));
        }
        rivit.sort(Comparator.comparingInt(LeaderboardStreak::getPutki).reversed());
        model.addAttribute("kayttajat", rivit);
        return "leaderboard";
    }
    
    
    

}
