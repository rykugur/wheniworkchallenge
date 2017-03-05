package api;

import java.util.List;

import io.reactivex.Observable;
import io.rollhax.nextripdomain.models.IDeparture;
import io.rollhax.nextripdomain.models.IRoute;
import io.rollhax.nextripdomain.models.IStop;
import io.rollhax.nextripdomain.models.TextValuePair;
import io.rollhax.nextripdomain.types.DirectionType;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NextripApi {

    @GET("Routes")
    Observable<List<IRoute>> getRoutes();

    @GET("Directions/{ROUTE}")
    Observable<List<TextValuePair>> getDirections(@Path("ROUTE") String route);

    @GET("Stops/{ROUTE}/{DIRECTION}")
    Observable<List<TextValuePair>> getStops(@Path("ROUTE") String route,
                                     @Path("DIRECTION") String direction);

    @GET("{STOPID}")
    Observable<List<IDeparture>> getDepartures(@Path("STOPID") int stopId);
}
