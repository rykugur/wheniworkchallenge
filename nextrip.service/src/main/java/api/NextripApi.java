package api;

import java.util.List;

import io.reactivex.Observable;
import io.rollhax.nextripdomain.models.Departure;
import io.rollhax.nextripdomain.models.Route;
import io.rollhax.nextripdomain.models.TextValuePair;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NextripApi {

    @GET("Routes")
    Observable<List<Route>> getRoutes();

    @GET("Directions/{ROUTE}")
    Observable<List<TextValuePair>> getDirections(@Path("ROUTE") String route);

    @GET("Stops/{ROUTE}/{DIRECTION}")
    Observable<List<TextValuePair>> getStops(@Path("ROUTE") String route,
                                     @Path("DIRECTION") String direction);

    @GET("{STOPID}")
    Observable<List<Departure>> getDepartures(@Path("STOPID") int stopId);
}
