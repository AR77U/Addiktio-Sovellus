package addiktio.sovellus.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

import jakarta.persistence.*;

@Entity 

public class AppUser {
    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
        private Long id;

        private String kayttajanimi;
        private String salasana;
        private String rooli;

        @ManyToOne 
        @JoinColumn (name = "substance_id")
        private Substance substance;


        @OneToMany (mappedBy = "appUser")
        private List<DailyEntry> dailyEntries;

        public AppUser() {
        }

        public AppUser(Long id, String kayttajanimi, String salasana, String rooli, Substance substance) {
            this.id = id;
            this.kayttajanimi = kayttajanimi;
            this.salasana = salasana;
            this.rooli = rooli;
            this.substance = substance;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getKayttajanimi() {
            return kayttajanimi;
        }

        public void setKayttajanimi(String kayttajanimi) {
            this.kayttajanimi = kayttajanimi;
        }

        public String getSalasana() {
            return salasana;
        }

        public void setSalasana(String salasana) {
            this.salasana = salasana;
        }

        public String getRooli() {
            return rooli;
        }

        public void setRooli(String rooli) {
            this.rooli = rooli;
        }

        public Substance getSubstance() {
            return substance;
        }

        public void setSubstance(Substance substance) {
            this.substance = substance;
        }

        @Override
        public String toString() {
            return "AppUser [id=" + id + ", kayttajanimi=" + kayttajanimi + ", salasana=" + salasana + ", rooli="
                    + rooli + ", substance=" + substance + "]";
        }

        
}
