package addiktio.sovellus.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.*;

public interface DailyEntryRepository extends CrudRepository<DailyEntry, Long> {
    List<DailyEntry> findByAppUserOrderByPvmDesc(AppUser appUser);

    Optional<DailyEntry> findByAppUserAndPvm(AppUser appUser, LocalDate pvm);

}
