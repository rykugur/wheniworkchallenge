package api;

import java.util.List;

import io.reactivex.Observable;
import io.rollhax.nextripdomain.models.IDeparture;
import io.rollhax.nextripdomain.models.IRoute;
import io.rollhax.nextripdomain.models.IStop;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NextripApi {

    @GET("Routes/?format=json")
    Observable<List<IRoute>> getRoutes();

    @GET("Stops/{ROUTE}?format=json")
    Observable<List<IStop>> getStops(@Path("ROUTE") String route);

    @GET("{STOPID}?format=json")
    Observable<List<IDeparture>> getDepartures(@Path("STOPID") int stopId);
}
