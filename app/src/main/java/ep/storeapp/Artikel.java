package ep.storeapp;

import java.io.Serializable;
import java.util.Locale;

public class Artikel implements Serializable {
    public int id_artikla;
    public String ime_artikla, opis_artikla, uri;
    public double cena;

    @Override
    public String toString() {
        return String.format(Locale.ENGLISH,
                "%s: %s, (id: %d) (%.2f EUR)",
                ime_artikla, opis_artikla, id_artikla, cena);
    }
}
