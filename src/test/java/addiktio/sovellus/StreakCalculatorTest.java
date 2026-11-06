package addiktio.sovellus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import addiktio.sovellus.domain.AppUser;
import addiktio.sovellus.domain.AppUserRepository;
import addiktio.sovellus.domain.DailyEntry;
import addiktio.sovellus.domain.DailyEntryRepository;
import addiktio.sovellus.service.StreakService;

@DataJpaTest 

public class StreakCalculatorTest {

    @Autowired 
    
    private DailyEntryRepository dailyEntryRepository;

    @Autowired 
    
    private AppUserRepository appUserRepository;

    @Test 
    public void streakkiAntaaNollanKunKirjauksiaEiOle(){
        AppUser appUser = appUserRepository.save(new AppUser());
        StreakService ss = new StreakService(dailyEntryRepository);
        assertThat(ss.calculateStreak(appUser)).isEqualTo(0);
    }
    @Test 
    public void kolmeOnnistumistaLaskeeTulokseksiKolme(){
        AppUser appUser = appUserRepository.save(new AppUser());
        StreakService ss = new StreakService(dailyEntryRepository);
        dailyEntryRepository.save(new DailyEntry(null, LocalDate.now(), true, 1, null, appUser));
        dailyEntryRepository.save(new DailyEntry(null, LocalDate.now().minusDays(1), true, 2, null, appUser));
        dailyEntryRepository.save(new DailyEntry(null, LocalDate.now().minusDays(2), true, 3, null, appUser));

        assertThat(ss.calculateStreak(appUser)).isEqualTo(3);
    }   
    @Test 
    public void streakEiKatkeaVaikkaTanaanEiKirjausta(){
        AppUser appUser = appUserRepository.save(new AppUser());
        StreakService ss = new StreakService(dailyEntryRepository);
        dailyEntryRepository.save(new DailyEntry(null, LocalDate.now().minusDays(1), true, 1, null, appUser));
        dailyEntryRepository.save(new DailyEntry(null, LocalDate.now().minusDays(2), true, 2, null, appUser));
        dailyEntryRepository.save(new DailyEntry(null, LocalDate.now().minusDays(3), true, 3, null, appUser));

        assertThat(ss.calculateStreak(appUser)).isEqualTo(3);

}
}
