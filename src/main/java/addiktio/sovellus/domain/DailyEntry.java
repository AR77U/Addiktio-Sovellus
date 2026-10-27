package addiktio.sovellus.domain;

import jakarta.persistence.Entity;


import java.time.LocalDate;

import org.springframework.cglib.core.Local;

import jakarta.persistence.*;

@Entity 

public class DailyEntry {

    @Id 
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long Id;

    private LocalDate pvm;
    private boolean onnistuminen;
    private int hankaluusaste;

    @Column (length = 1000)
    private String mietteet;

    @ManyToOne 
    @JoinColumn (name = "app_user_id")
    private AppUser appUser;

    public DailyEntry() {
    }

    public DailyEntry(Long id, LocalDate pvm, boolean onnistuminen, int hankaluusaste, String mietteet, AppUser appUser) {
        Id = id;
        
        this.pvm = pvm;
        this.onnistuminen = onnistuminen;
        this.hankaluusaste = hankaluusaste;
        this.mietteet = mietteet;
        this.appUser = appUser;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public LocalDate getPvm() {
        return pvm;
    }

    public void setPvm(LocalDate pvm) {
        this.pvm = pvm;
    }

    public boolean isOnnistuminen() {
        return onnistuminen;
    }

    public void setOnnistuminen(boolean onnistuminen) {
        this.onnistuminen = onnistuminen;
    }

    public int getHankaluusaste() {
        return hankaluusaste;
    }

    public void setHankaluusaste(int hankaluusaste) {
        this.hankaluusaste = hankaluusaste;
    }

    public String getMietteet() {
        return mietteet;
    }

    public void setMietteet(String mietteet) {
        this.mietteet = mietteet;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public String toString() {
        return "DailyEntry [Id=" + Id + ", pvm=" + pvm + ", onnistuminen=" + onnistuminen + ", hankaluusaste="
                + hankaluusaste + ", mietteet=" + mietteet + ", appUser=" + appUser + "]";
    }

    
}
