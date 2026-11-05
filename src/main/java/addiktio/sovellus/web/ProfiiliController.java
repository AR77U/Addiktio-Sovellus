package addiktio.sovellus.web;

import java.security.Principal;
import java.time.LocalDate;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import addiktio.sovellus.domain.AppUser;
import addiktio.sovellus.domain.AppUserRepository;
import addiktio.sovellus.domain.DailyEntry;
import addiktio.sovellus.domain.DailyEntryRepository;
import addiktio.sovellus.domain.LeaderboardStreak;
import addiktio.sovellus.service.StreakService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



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

@GetMapping("/add")
public String addOnnistuminen(Model model) {
    model.addAttribute("uusiKirjaus", new DailyEntry());
    return "uusiKirjaus";
}
@PostMapping("/save")
public String saveOnnistuminen(@Valid @ModelAttribute ("uusiKirjaus") DailyEntry dailyEntry, BindingResult bindingResult, Principal principal) {
    if (bindingResult.hasErrors()) {
        return "uusiKirjaus";
    }
    AppUser appUser = appUserRepository.findByKayttajanimi(principal.getName())
    .orElseThrow(()-> new UsernameNotFoundException("Ei löydy"));
    dailyEntry.setAppUser(appUser);
    dailyEntry.setPvm(LocalDate.now());
    dailyEntryRepository.save(dailyEntry);
    //TODO: process POST request
    
    return "redirect:/profiili";
}
@GetMapping("/edit/{id}")
public String muokkaaKirjaus(@PathVariable  ("id") Long id, Model model, Principal principal ) {
    DailyEntry dailyEntry = dailyEntryRepository.findById(id)
    .orElseThrow(() -> new IllegalArgumentException("Ei löydy"));

    if (!dailyEntry.getAppUser().getKayttajanimi().equals(principal.getName())) {
        return "redirect:/profiili";
    }
    model.addAttribute("dailyentry", dailyEntry);
    return "editKirjaus";
}

@PostMapping("/edit/save")
public String tallennaMuokattuKirjaus( DailyEntry lomake, Principal principal ) {
    DailyEntry vanha = dailyEntryRepository.findById(lomake.getId())
    .orElseThrow(() -> new IllegalArgumentException("Ei löydy"));

    if (!vanha.getAppUser().getKayttajanimi().equals(principal.getName())) {
        return "redirect:/profiili";  // Tarkistetaan että muokattava kirjaus kuuluu kirjautuneelle käyttäjälle
    }
    vanha.setOnnistuminen(lomake.isOnnistuminen());
    vanha.setHankaluusaste(lomake.getHankaluusaste());
    vanha.setMietteet(lomake.getMietteet());
    dailyEntryRepository.save(vanha);

    return "redirect:/profiili";
}

@PostMapping("/delete/{id}")
public String poistaKirjaus(@PathVariable  ("id") Long id, Principal principal ) {
    DailyEntry dailyEntry = dailyEntryRepository.findById(id)
    .orElseThrow(() -> new IllegalArgumentException("Ei löydy"));

    if (dailyEntry.getAppUser().getKayttajanimi().equals(principal.getName())) {
        dailyEntryRepository.deleteById(id);
    }
    
    return "redirect:../profiili";
}
}






