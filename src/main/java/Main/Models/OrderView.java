package Main.Models;

import javafx.beans.property.*;
import sun.text.normalizer.Trie;

public class OrderView
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty przedmiot = new SimpleStringProperty();
    private IntegerProperty ilosc = new SimpleIntegerProperty();
    private FloatProperty cena = new SimpleFloatProperty();
    private StringProperty dataZamowienia = new SimpleStringProperty();
    private FloatProperty wartosc = new SimpleFloatProperty();
    private StringProperty typRachunku = new SimpleStringProperty();
    private StringProperty wysylka = new SimpleStringProperty();
    private StringProperty statusZamowienia = new SimpleStringProperty();
    private StringProperty NIP = new SimpleStringProperty();
    private StringProperty firmaNazwa = new SimpleStringProperty();
    private StringProperty imie = new SimpleStringProperty();
    private StringProperty nazwisko = new SimpleStringProperty();
    private StringProperty adres = new SimpleStringProperty();
    private StringProperty telefon = new SimpleStringProperty();

    public OrderView(int id, String przedmiot, int ilosc ,float cena, String dataZamowienia, float wartosc, String typRachunku, String wysylka, String statusZamowienia, String NIP, String firmaNazwa, String imie, String nazwisko, String adres, String telefon)
    {
        setId(id);
        setPrzedmiot(przedmiot);
        setIlosc(ilosc);
        setCena(cena);
        setDataZamowienia(dataZamowienia);
        setWartosc(wartosc);
        setTypRachunku(typRachunku);
        setWysylka(wysylka);
        setStatusZamowienia(statusZamowienia);
        setNIP(NIP);
        setFirmaNazwa(firmaNazwa);
        setImie(imie);
        setNazwisko(nazwisko);
        setAdres(adres);
        setTelefon(telefon);
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

    public String getPrzedmiot()
    {
        return przedmiot.get();
    }

    public StringProperty przedmiotProperty()
    {
        return przedmiot;
    }

    public void setPrzedmiot(String przedmiot)
    {
        this.przedmiot.set(przedmiot);
    }

    public float getCena()
    {
        return cena.get();
    }

    public FloatProperty cenaProperty()
    {
        return cena;
    }

    public void setCena(float cena)
    {
        this.cena.set(cena);
    }

    public String getDataZamowienia()
    {
        return dataZamowienia.get();
    }

    public StringProperty dataZamowieniaProperty()
    {
        return dataZamowienia;
    }

    public void setDataZamowienia(String dataZamowienia)
    {
        this.dataZamowienia.set(dataZamowienia);
    }

    public float getWartosc()
    {
        return wartosc.get();
    }

    public FloatProperty wartoscProperty()
    {
        return wartosc;
    }

    public void setWartosc(float wartosc)
    {
        this.wartosc.set(wartosc);
    }

    public String getTypRachunku()
    {
        return typRachunku.get();
    }

    public StringProperty typRachunkuProperty()
    {
        return typRachunku;
    }

    public void setTypRachunku(String typRachunku)
    {
        this.typRachunku.set(typRachunku);
    }

    public String getWysylka()
    {
        return wysylka.get();
    }

    public StringProperty wysylkaProperty()
    {
        return wysylka;
    }

    public void setWysylka(String wysylka)
    {
        this.wysylka.set(wysylka);
    }

    public String getStatusZamowienia()
    {
        return statusZamowienia.get();
    }

    public StringProperty statusZamowieniaProperty()
    {
        return statusZamowienia;
    }

    public void setStatusZamowienia(String statusZamowienia)
    {
        this.statusZamowienia.set(statusZamowienia);
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
}
