package addiktio.sovellus.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import addiktio.sovellus.domain.AppUserRepository;
import addiktio.sovellus.domain.DailyEntryRepository;
import addiktio.sovellus.domain.SubstanceRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 

public class LeaderboardController {
    private final DailyEntryRepository dailyEntryRepository;
    private final AppUserRepository appUserRepository;
    private final SubstanceRepository substanceRepository;

    public LeaderboardController(DailyEntryRepository dailyEntryRepository, AppUserRepository appUserRepository,
            SubstanceRepository substanceRepository) {
        this.dailyEntryRepository = dailyEntryRepository;
        this.appUserRepository = appUserRepository;
        this.substanceRepository = substanceRepository;
    }
    @GetMapping("/leaderboard")
    public String leaderBoard(Model model) {
        model.addAttribute("kayttajat", appUserRepository.findAll());
        return new String();
    }
    
    
    

}
