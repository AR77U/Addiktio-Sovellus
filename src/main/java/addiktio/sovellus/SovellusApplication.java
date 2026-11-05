package addiktio.sovellus;



import java.time.LocalDate;

import org.slf4j.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import addiktio.sovellus.domain.AppUser;
import addiktio.sovellus.domain.AppUserRepository;
import addiktio.sovellus.domain.DailyEntry;
import addiktio.sovellus.domain.DailyEntryRepository;
import addiktio.sovellus.domain.Substance;
import addiktio.sovellus.domain.SubstanceRepository;

@SpringBootApplication
public class SovellusApplication {
	public static final Logger log = LoggerFactory.getLogger(SovellusApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SovellusApplication.class, args);
	}

	@Bean 
	public CommandLineRunner addiktioDemo (DailyEntryRepository deRepo, AppUserRepository aRepo, SubstanceRepository seRepo){
		return (args) ->{
			log.info("Esimerkki dataa ");
			Substance substance1 = new Substance(null, "Nikotiini");
			seRepo.save(substance1);
			Substance substance2 = new Substance(null, "Alkoholi");
			seRepo.save(substance2);
			Substance substance3 = new Substance(null, "Kofeiini");
			seRepo.save(substance3); 

			AppUser user1 = new AppUser(null, "siili", "$2a$10$bTn.3eDULYlAfwTCABvPZe1OFVYwcyAiep85Dkyw4Uhf09rxwN44u", "user", substance3); //salasana: siili123 
			aRepo.save(user1);
			AppUser user2 = new AppUser(null, "sonni", "$2a$10$UMMD/tdCxoW5BaiQojXIwuv1x64sBWUQfwKbUp607GOKILxTDN52W", "admin", substance1); //salasana: sonni123
			aRepo.save(user2);

			DailyEntry entry1 = new DailyEntry(null, LocalDate.now().minusDays(1), true, 3, null, user1);
			deRepo.save(entry1);
			DailyEntry entry2 = new DailyEntry(null, LocalDate.now().minusDays(2), true, 4, null, user1);
			deRepo.save(entry2);
			DailyEntry entry3 = new DailyEntry(null, LocalDate.now().minusDays(3), true, 5, null, user1);
			deRepo.save(entry3);
			DailyEntry entry4 = new DailyEntry(null, LocalDate.now().minusDays(4), true, 2, "Olipa helpon tuntuista", user1);
			deRepo.save(entry4);
			DailyEntry entry5 = new DailyEntry(null, LocalDate.now().minusDays(5), false, 5, null, user1);
			deRepo.save(entry5);

			DailyEntry sonniEntry1 = new DailyEntry(null, LocalDate.now().minusDays(1), true, 2, null, user2);
			deRepo.save(sonniEntry1);
			DailyEntry sonniEntry2 = new DailyEntry(null, LocalDate.now().minusDays(2), true, 4, null, user2);
			deRepo.save(sonniEntry2);
			DailyEntry sonniEntry3 = new DailyEntry(null, LocalDate.now().minusDays(3), true, 5, "Hyvin raskas päivä", user2);
			deRepo.save(sonniEntry3);
			DailyEntry sonniEntry4 = new DailyEntry(null, LocalDate.now().minusDays(4), true, 2, null, user2);
			deRepo.save(sonniEntry4);
			DailyEntry sonniEntry5 = new DailyEntry(null, LocalDate.now().minusDays(5), true, 5, null, user2);
			deRepo.save(sonniEntry5);
		};
	}
	

}
