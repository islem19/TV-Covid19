package dz.islem.tvcovid.data.network;

import java.util.List;

import dz.islem.tvcovid.data.model.CountryData;
import dz.islem.tvcovid.data.model.GlobalData;
import dz.islem.tvcovid.data.network.api.DataApi;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {
    private static final String BASE_URL = "https://corona.lmao.ninja";
    private DataApi mDataApi;

    private static DataService mInstance;

    public static DataService getInstance(){
        return mInstance == null ? mInstance = new DataService() : mInstance;
    }

    private DataService(){
        Retrofit mRetrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        mDataApi = mRetrofit.create(DataApi.class);
    }

    public Single<GlobalData> getGlobalData(){
        return mDataApi.getGlobalData();
    }

    public Single<List<CountryData>> getAllData(){
        return mDataApi.getAllData();
    }

    public Single<CountryData> getDataByCountry(String country){
        return mDataApi.getDataByCountry(country);
    }
}
