package addiktio.sovellus.domain;


import java.util.Optional;

import org.springframework.data.repository.*;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {
    Optional <AppUser> findByKayttajanimi(String kayttajanimi);

}
