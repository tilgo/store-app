package ep.storeapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class ArtikelService {
    interface RestApi {
        String URL = "http://10.0.2.2:8080/netbeans/ep-store/api/"; //10.0.2.2:8080

        @GET("artikli")
        Call<List<Artikel>> getAll();

        @GET("artikli/{id}")
        Call<Artikel> get(@Path("id") int id);

    }

    private static RestApi instance;

    public static synchronized RestApi getInstance() {
        if (instance == null) {
            final Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestApi.URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(RestApi.class);
        }

        return instance;
    }
}
