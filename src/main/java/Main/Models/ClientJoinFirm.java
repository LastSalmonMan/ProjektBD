package Main.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientJoinFirm
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty imie = new SimpleStringProperty();
    private StringProperty nazwisko = new SimpleStringProperty();
    private StringProperty adres = new SimpleStringProperty();
    private StringProperty kodPocztowy = new SimpleStringProperty();
    private StringProperty miejscowosc = new SimpleStringProperty();
    private StringProperty firma = new SimpleStringProperty();
    private StringProperty telefon = new SimpleStringProperty();
    private StringProperty mail = new SimpleStringProperty();
    private StringProperty NIP = new SimpleStringProperty();
    private StringProperty firmaNazwa = new SimpleStringProperty();
    private StringProperty firmaAdres = new SimpleStringProperty();
    private StringProperty firmaKodPocztowy = new SimpleStringProperty();
    private StringProperty firmaMiejscowosc = new SimpleStringProperty();

    public ClientJoinFirm(int id, String imie, String nazwisko, String adres, String kodPocztowy, String miejscowosc, String firma, String telefon, String mail, String NIP, String firmaNazwa, String firmaAdres, String firmaKodPocztowy, String firmaMiejscowosc)
    {
        setId(id);
        setImie(imie);
        setNazwisko(nazwisko);
        setAdres(adres);
        setKodPocztowy(kodPocztowy);
        setMiejscowosc(miejscowosc);
        setFirma(firma);
        setTelefon(telefon);
        setMail(mail);
        setNIP(NIP);
        setFirmaNazwa(firmaNazwa);
        setFirmaAdres(firmaAdres);
        setFirmaKodPocztowy(firmaKodPocztowy);
        setFirmaMiejscowosc(firmaMiejscowosc);
    }

    public int getId()
    {
        return id.get();
    }

    public IntegerProperty idProperty()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id.set(id);
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

    public String getFirma()
    {
        return firma.get();
    }

    public StringProperty firmaProperty()
    {
        return firma;
    }

    public void setFirma(String firma)
    {
        this.firma.set(firma);
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

    public String getFirmaNazwa()
    {
        return firmaNazwa.get();
    }

    public StringProperty firmaNazwaProperty()
    {
        return firmaNazwa;
    }

    public void setFirmaNazwa(String firmaNazwa)
    {
        this.firmaNazwa.set(firmaNazwa);
    }

    public String getFirmaAdres()
    {
        return firmaAdres.get();
    }

    public StringProperty firmaAdresProperty()
    {
        return firmaAdres;
    }

    public void setFirmaAdres(String firmaAdres)
    {
        this.firmaAdres.set(firmaAdres);
    }

    public String getFirmaKodPocztowy()
    {
        return firmaKodPocztowy.get();
    }

    public StringProperty firmaKodPocztowyProperty()
    {
        return firmaKodPocztowy;
    }

    public void setFirmaKodPocztowy(String firmaKodPocztowy)
    {
        this.firmaKodPocztowy.set(firmaKodPocztowy);
    }

    public String getFirmaMiejscowosc()
    {
        return firmaMiejscowosc.get();
    }

    public StringProperty firmaMiejscowoscProperty()
    {
        return firmaMiejscowosc;
    }

    public void setFirmaMiejscowosc(String firmaMiejscowosc)
    {
        this.firmaMiejscowosc.set(firmaMiejscowosc);
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
}
