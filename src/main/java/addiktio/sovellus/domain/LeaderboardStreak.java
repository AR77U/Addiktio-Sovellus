package addiktio.sovellus.domain;

public class LeaderboardStreak {
    private String kayttajanimi;
    private String aine;
    private int putki;
    public LeaderboardStreak(String kayttajanimi, String aine, int putki) {
        this.kayttajanimi = kayttajanimi;
        this.aine = aine;
        this.putki = putki;
    }
    public String getKayttajanimi() {
        return kayttajanimi;
    }
    public void setKayttajanimi(String kayttajanimi) {
        this.kayttajanimi = kayttajanimi;
    }
    public String getAine() {
        return aine;
    }
    public void setAine(String aine) {
        this.aine = aine;
    }
    public int getPutki() {
        return putki;
    }
    public void setPutki(int putki) {
        this.putki = putki;
    }

    

}
