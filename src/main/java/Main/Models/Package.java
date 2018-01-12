package Main.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Package
{
    private IntegerProperty idZamowienia = new SimpleIntegerProperty();
    private IntegerProperty iloscPaczek = new SimpleIntegerProperty();
    private StringProperty statusPrzesylki = new SimpleStringProperty();
    private StringProperty imie = new SimpleStringProperty();
    private StringProperty nazwisko = new SimpleStringProperty();
    private StringProperty adres = new SimpleStringProperty();
    private StringProperty kodPocztowy = new SimpleStringProperty();
    private StringProperty miejscowosc = new SimpleStringProperty();
    private StringProperty telefon = new SimpleStringProperty();
    private StringProperty mail = new SimpleStringProperty();
    private StringProperty dodatkoweInformacje = new SimpleStringProperty();
    private StringProperty dataUtworzenia = new SimpleStringProperty();
    private StringProperty dostawca = new SimpleStringProperty();
    private StringProperty typDostawy = new SimpleStringProperty();

    public Package(int idZamowienia, int iloscPaczek, String statusPrzesylki, String imie, String nazwisko, String adres, String kodPocztowy, String miejscowosc, String telefon, String mail, String dodatkoweInformacje, String dataUtworzenia, String dostawca, String typDostawy)
    {
        setIdZamowienia(idZamowienia);
        setIloscPaczek(iloscPaczek);
        setStatusPrzesylki(statusPrzesylki);
        setImie(imie);
        setNazwisko(nazwisko);
        setAdres(adres);
        setKodPocztowy(kodPocztowy);
        setMiejscowosc(miejscowosc);
        setTelefon(telefon);
        setMail(mail);
        setDodatkoweInformacje(dodatkoweInformacje);
        setDataUtworzenia(dataUtworzenia);
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

    public String getStatusPrzesylki()
    {
        return statusPrzesylki.get();
    }

    public StringProperty statusPrzesylkiProperty()
    {
        return statusPrzesylki;
    }

    public void setStatusPrzesylki(String statusPrzesylki)
    {
        this.statusPrzesylki.set(statusPrzesylki);
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

    public String getTelefon()
    {
        return telefon.get();
    }

    public StringProperty telefonProperty()
    {
        return telefon;
    }

    public void setTelefon(String telefon)
    {
        this.telefon.set(telefon);
    }

    public String getMail()
    {
        return mail.get();
    }

    public StringProperty mailProperty()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail.set(mail);
    }

    public String getDodatkoweInformacje()
    {
        return dodatkoweInformacje.get();
    }

    public StringProperty dodatkoweInformacjeProperty()
    {
        return dodatkoweInformacje;
    }

    public void setDodatkoweInformacje(String dodatkoweInformacje)
    {
        this.dodatkoweInformacje.set(dodatkoweInformacje);
    }

    public String getDataUtworzenia()
    {
        return dataUtworzenia.get();
    }

    public StringProperty dataUtworzeniaProperty()
    {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(String dataUtworzenia)
    {
        this.dataUtworzenia.set(dataUtworzenia);
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
