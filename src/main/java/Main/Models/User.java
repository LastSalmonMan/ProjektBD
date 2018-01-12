package Main.Models;

public class User
{
    private String nazwa;
    private String hasloHash;
    private String hasloSalt;
    private String imie;
    private String nazwisko;
    private boolean uprBiuro;
    private boolean uprMagazyn;
    private boolean uprPakowanie;
    private boolean uprAdmin;

    public User(String nazwa, String hasloHash, String hasloSalt, String imie, String nazwisko, boolean uprBiuro, boolean uprMagazyn, boolean uprPakowanie, boolean uprAdmin)
    {
        this.nazwa = nazwa;
        this.hasloHash = hasloHash;
        this.hasloSalt = hasloSalt;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.uprBiuro = uprBiuro;
        this.uprMagazyn = uprMagazyn;
        this.uprPakowanie = uprPakowanie;
        this.uprAdmin = uprAdmin;
    }

    public String getNazwa()
    {
        return nazwa;
    }

    public void setNazwa(String nazwa)
    {
        this.nazwa = nazwa;
    }

    public String getHasloHash()
    {
        return hasloHash;
    }

    public void setHasloHash(String hasloHash)
    {
        this.hasloHash = hasloHash;
    }

    public String getHasloSalt()
    {
        return hasloSalt;
    }

    public void setHasloSalt(String hasloSalt)
    {
        this.hasloSalt = hasloSalt;
    }

    public String getImie()
    {
        return imie;
    }

    public void setImie(String imie)
    {
        this.imie = imie;
    }

    public String getNazwisko()
    {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko)
    {
        this.nazwisko = nazwisko;
    }

    public boolean isUprBiuro()
    {
        return uprBiuro;
    }

    public void setUprBiuro(boolean uprBiuro)
    {
        this.uprBiuro = uprBiuro;
    }

    public boolean isUprMagazyn()
    {
        return uprMagazyn;
    }

    public void setUprMagazyn(boolean uprMagazyn)
    {
        this.uprMagazyn = uprMagazyn;
    }

    public boolean isUprPakowanie()
    {
        return uprPakowanie;
    }

    public void setUprPakowanie(boolean uprPakowanie)
    {
        this.uprPakowanie = uprPakowanie;
    }

    public boolean isUprAdmin()
    {
        return uprAdmin;
    }

    public void setUprAdmin(boolean uprAdmin)
    {
        this.uprAdmin = uprAdmin;
    }
}
