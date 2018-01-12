package Main.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PackingView
{
    private IntegerProperty idZamowienia = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    private IntegerProperty ilosc = new SimpleIntegerProperty();
    private IntegerProperty iloscPaczek = new SimpleIntegerProperty();
    private StringProperty imie = new SimpleStringProperty();
    private StringProperty nazwisko = new SimpleStringProperty();
    private StringProperty adres = new SimpleStringProperty();
    private StringProperty kodPocztowy = new SimpleStringProperty();
    private StringProperty miejscowosc = new SimpleStringProperty();
    private StringProperty dostawca = new SimpleStringProperty();
    private StringProperty typDostawy = new SimpleStringProperty();

    public PackingView(int idZamowienia, String nazwa, int ilosc, int iloscPaczek, String imie, String nazwisko, String adres, String kodPocztowy, String miejscowosc, String dostawca, String typDostawy)
    {
        setIdZamowienia(idZamowienia);
        setNazwa(nazwa);
        setIlosc(ilosc);
        setIloscPaczek(iloscPaczek);
        setImie(imie);
        setNazwisko(nazwisko);
        setAdres(adres);
        setKodPocztowy(kodPocztowy);
        setMiejscowosc(miejscowosc);
        setDostawca(dostawca);
        setTypDostawy(typDostawy);
    }

    public int getIdZamowienia()
    {
        return idZamowienia.get();
    }

    public IntegerProperty idZamowieniaProperty()
    {
        return idZamowienia;
    }

    public void setIdZamowienia(int idZamowienia)
    {
        this.idZamowienia.set(idZamowienia);
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

    public int getIlosc()
    {
        return ilosc.get();
    }

    public IntegerProperty iloscProperty()
    {
        return ilosc;
    }

    public void setIlosc(int ilosc)
    {
        this.ilosc.set(ilosc);
    }

    public int getIloscPaczek()
    {
        return iloscPaczek.get();
    }

    public IntegerProperty iloscPaczekProperty()
    {
        return iloscPaczek;
    }

    public void setIloscPaczek(int iloscPaczek)
    {
        this.iloscPaczek.set(iloscPaczek);
    }

    public String getImie()
    {
        return imie.get();
    }

    public StringProperty imieProperty()
    {
        return imie;
    }

    public void setImie(String imie)
    {
        this.imie.set(imie);
    }

    public String getNazwisko()
    {
        return nazwisko.get();
    }

    public StringProperty nazwiskoProperty()
    {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko)
    {
        this.nazwisko.set(nazwisko);
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

    public String getDostawca()
    {
        return dostawca.get();
    }

    public StringProperty dostawcaProperty()
    {
        return dostawca;
    }

    public void setDostawca(String dostawca)
    {
        this.dostawca.set(dostawca);
    }

    public String getTypDostawy()
    {
        return typDostawy.get();
    }

    public StringProperty typDostawyProperty()
    {
        return typDostawy;
    }

    public void setTypDostawy(String typDostawy)
    {
        this.typDostawy.set(typDostawy);
    }
}
