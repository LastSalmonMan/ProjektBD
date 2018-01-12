package Main.Models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Firm
{
    private StringProperty NIP = new SimpleStringProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    private StringProperty adres = new SimpleStringProperty();
    private StringProperty kodPocztowy = new SimpleStringProperty();
    private StringProperty miejscowosc = new SimpleStringProperty();

    public Firm(String NIP, String nazwa, String adres, String kodPocztowy, String miejscowosc)
    {
        setNIP(NIP);
        setNazwa(nazwa);
        setAdres(adres);
        setKodPocztowy(kodPocztowy);
        setMiejscowosc(miejscowosc);
    }

    public String getNIP()
    {
        return NIP.get();
    }

    public StringProperty NIPProperty()
    {
        return NIP;
    }

    public void setNIP(String NIP)
    {
        this.NIP.set(NIP);
    }

    public String getNazwa()
    {
        return nazwa.get();
    }

    public StringProperty nazwaProperty()
    {
        return nazwa;
    }

    public void setNazwa(String nazwa)
    {
        this.nazwa.set(nazwa);
    }

    public String getAdres()
    {
        return adres.get();
    }

    public StringProperty adresProperty()
    {
        return adres;
    }

    public void setAdres(String adres)
    {
        this.adres.set(adres);
    }

    public String getKodPocztowy()
    {
        return kodPocztowy.get();
    }

    public StringProperty kodPocztowyProperty()
    {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy)
    {
        this.kodPocztowy.set(kodPocztowy);
    }

    public String getMiejscowosc()
    {
        return miejscowosc.get();
    }

    public StringProperty miejscowoscProperty()
    {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc)
    {
        this.miejscowosc.set(miejscowosc);
    }
}
