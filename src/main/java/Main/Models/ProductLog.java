package Main.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ProductLog
{
    private StringProperty akcja = new SimpleStringProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    private IntegerProperty staraWartosc = new SimpleIntegerProperty();
    private IntegerProperty nowaWartosc = new SimpleIntegerProperty();
    private StringProperty dataAkcji = new SimpleStringProperty();

    public ProductLog(String akcja, String nazwa, int staraWartosc, int nowaWartosc, String dataAkcji)
    {
        setAkcja(akcja);
        setNazwa(nazwa);
        setStaraWartosc(staraWartosc);
        setNowaWartosc(nowaWartosc);
        setDataAkcji(dataAkcji);
    }

    public String getAkcja()
    {
        return akcja.get();
    }

    public StringProperty akcjaProperty()
    {
        return akcja;
    }

    public void setAkcja(String akcja)
    {
        this.akcja.set(akcja);
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

    public int getStaraWartosc()
    {
        return staraWartosc.get();
    }

    public IntegerProperty staraWartoscProperty()
    {
        return staraWartosc;
    }

    public void setStaraWartosc(int staraWartosc)
    {
        this.staraWartosc.set(staraWartosc);
    }

    public int getNowaWartosc()
    {
        return nowaWartosc.get();
    }

    public IntegerProperty nowaWartoscProperty()
    {
        return nowaWartosc;
    }

    public void setNowaWartosc(int nowaWartosc)
    {
        this.nowaWartosc.set(nowaWartosc);
    }

    public String getDataAkcji()
    {
        return dataAkcji.get();
    }

    public StringProperty dataAkcjiProperty()
    {
        return dataAkcji;
    }

    public void setDataAkcji(String dataAkcji)
    {
        this.dataAkcji.set(dataAkcji);
    }
}
