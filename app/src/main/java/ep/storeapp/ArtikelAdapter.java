package ep.storeapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ArtikelAdapter extends ArrayAdapter<Artikel> {
    public ArtikelAdapter(Context context, List<Artikel> objects) {
        super(context, 0, objects);
    }
    private static final String TAG = MainActivity.class.getCanonicalName();

    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        final Artikel artikel = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemlist_element, parent, false);
        }

        final TextView tvOpisArtikla;

        final TextView tvImeArtikla = (TextView) convertView.findViewById(R.id.tv_ImeArtikla);
        tvOpisArtikla = (TextView) convertView.findViewById(R.id.tv_opisArtikla);
        final TextView tvCena = (TextView) convertView.findViewById(R.id.tv_cena);

        tvImeArtikla.setText(artikel.ime_artikla);
        tvOpisArtikla.setText(String.format("%s", artikel.opis_artikla));
        Log.i(TAG, "opis: " + artikel.opis_artikla);
        tvCena.setText(String.format(Locale.ENGLISH, "%.2f EUR", artikel.cena));

        return convertView;
    }
}
