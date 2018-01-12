package Main.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Storage
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    private IntegerProperty ilosc = new SimpleIntegerProperty();

    public Storage(int id, String nazwa, int ilosc)
    {
        setId(id);
        setNazwa(nazwa);
        setIlosc(ilosc);
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
}
