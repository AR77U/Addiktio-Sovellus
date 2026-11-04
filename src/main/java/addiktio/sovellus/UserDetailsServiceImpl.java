package addiktio.sovellus;

import java.util.Optional;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import addiktio.sovellus.domain.AppUser;
import addiktio.sovellus.domain.AppUserRepository;



@Service  

public class UserDetailsServiceImpl implements UserDetailsService {

    private final AppUserRepository repository;

    

    public UserDetailsServiceImpl(AppUserRepository repository) {
        this.repository = repository;
    }



    @Override 

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       AppUser curruser = repository.findByKayttajanimi(username)
       .orElseThrow(() -> new UsernameNotFoundException("Käyttäjää ei löydy"));
       UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getSalasana(),
    AuthorityUtils.createAuthorityList(curruser.getRooli()));

    return user;
    }

}
