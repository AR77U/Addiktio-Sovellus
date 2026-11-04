package addiktio.sovellus.web;

import java.security.Principal;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import addiktio.sovellus.domain.AppUser;
import addiktio.sovellus.domain.AppUserRepository;
import addiktio.sovellus.domain.DailyEntryRepository;
import addiktio.sovellus.domain.LeaderboardStreak;
import addiktio.sovellus.service.StreakService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 

public class ProfiiliController {
private final AppUserRepository appUserRepository;
private final DailyEntryRepository dailyEntryRepository;
private final StreakService streakService;

public ProfiiliController(AppUserRepository appUserRepository, DailyEntryRepository dailyEntryRepository,
        StreakService streakService) {
    this.appUserRepository = appUserRepository;
    this.dailyEntryRepository = dailyEntryRepository;
    this.streakService = streakService;
}

@GetMapping("/profiili")
public String profiili(Model model, Principal principal) {
    String kayttaja = principal.getName();
    AppUser user = appUserRepository.findByKayttajanimi(kayttaja).orElseThrow(()
    -> new UsernameNotFoundException("Kayttajaa ei loydy"));
    model.addAttribute("streakit", streakService.calculateStreak( user));
    model.addAttribute("kirjaukset", dailyEntryRepository.findByAppUserOrderByPvmDesc(user));
    int streakit = streakService.calculateStreak(user);
    return "profiili";
}



}
