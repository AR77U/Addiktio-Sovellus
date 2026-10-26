package addiktio.sovellus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

import java.util.List;

import jakarta.persistence.*;

@Entity 

public class aine {
    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
        private Long id;

        private String nimi;

        @OneToMany (mappedBy = "aine")
        private  List<kayttaja> kayttajat;

        public aine() {
        }

        public aine(Long id, String nimi) {
            this.id = id;
            this.nimi = nimi;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNimi() {
            return nimi;
        }

        public void setNimi(String nimi) {
            this.nimi = nimi;
        }

        @Override
        public String toString() {
            return "aine [id=" + id + ", nimi=" + nimi + ", aineet=" + aineet + "]";
        }

        

}
