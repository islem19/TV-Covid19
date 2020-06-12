package dz.islem.tvcovid.data.network.api;

import java.util.List;

import dz.islem.tvcovid.data.model.CountryData;
import dz.islem.tvcovid.data.model.GlobalData;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DataApi {
    @GET("v2/all")
    Single<GlobalData> getGlobalData();

    @GET("v2/countries?sort=cases")
    Single<List<CountryData>> getAllData();

    @GET("v2/countries/{country}")
    Single<CountryData> getDataByCountry(@Path("country") String country);
}
