package addiktio.sovellus.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import addiktio.sovellus.domain.AppUser;
import addiktio.sovellus.domain.DailyEntry;
import addiktio.sovellus.domain.DailyEntryRepository;

@Service 

public class StreakService {
    private final DailyEntryRepository dailyEntryRepository;

    public StreakService(DailyEntryRepository dailyEntryRepository) {
        this.dailyEntryRepository = dailyEntryRepository;
    }

    public int calculateStreak(AppUser user){
        List<DailyEntry> streak = dailyEntryRepository.findByAppUserOrderByPvmDesc(user);
        if (streak.isEmpty()) {
            return 0;
        }
        LocalDate etsittyPaiva = LocalDate.now().minusDays(1);

        if (streak.get(0).getPvm().equals(LocalDate.now())) {
            etsittyPaiva = LocalDate.now();
        }

         int putki = 0;

        for (DailyEntry s : streak){

           
            if (etsittyPaiva.equals(s.getPvm()) && s.isOnnistuminen()) {

               putki++;

              etsittyPaiva= etsittyPaiva.minusDays(1); 
            }
            else{
                break;
            }
        }

        

        return putki;
    }

}
