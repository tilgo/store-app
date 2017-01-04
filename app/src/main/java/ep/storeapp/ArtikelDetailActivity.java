package ep.storeapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArtikelDetailActivity extends AppCompatActivity implements Callback<Artikel> {
    private static final String TAG = ArtikelDetailActivity.class.getCanonicalName();

    private Artikel artikel;
    private TextView tvArtikelDetail;
    private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artikel_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        tvArtikelDetail = (TextView) findViewById(R.id.tv_artikel_detail);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int id = getIntent().getIntExtra("ep.rest.id", 0);
        if (id > 0) {
            ArtikelService.getInstance().get(id).enqueue(this);
        }
    }


    @Override
    public void onResponse(Call<Artikel> call, Response<Artikel> response) {
        artikel = response.body();
        Log.i(TAG, "Got result: " + artikel);

        if (response.isSuccessful()) {
            tvArtikelDetail.setText(String.format(Locale.ENGLISH, "Cena: %.2f EUR \nOpis: %s", artikel.cena, artikel.opis_artikla));
            toolbarLayout.setTitle(artikel.ime_artikla);
        } else {
            String errorMessage;
            try {
                errorMessage = "An error occurred: " + response.errorBody().string();
            } catch (IOException e) {
                errorMessage = "An error occurred: error while decoding the error message.";
            }
            Log.e(TAG, errorMessage);
            tvArtikelDetail.setText(errorMessage);
        }
    }

    @Override
    public void onFailure(Call<Artikel> call, Throwable t) {
        Log.w(TAG, "Error: " + t.getMessage(), t);
    }
}
