package dz.islem.tvcovid.data.network.api;

import dz.islem.tvcovid.data.model.Location;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface LocationApi {
    @GET("/json")
    Single<Location> getLocation();
}
