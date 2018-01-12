package Main.Models;

import javafx.beans.property.*;

public class Product
{
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty nazwa = new SimpleStringProperty();
    private FloatProperty cena = new SimpleFloatProperty();
    private IntegerProperty ilosc = new SimpleIntegerProperty();

    public Product(int id, String nazwa, float cena)
    {
        setId(id);
        setNazwa(nazwa);
        setCena(cena);
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
