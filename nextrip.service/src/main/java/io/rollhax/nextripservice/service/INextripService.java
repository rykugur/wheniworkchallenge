package io.rollhax.nextripservice.service;

import java.util.List;

import io.reactivex.Observable;
import io.rollhax.nextripdomain.models.IDeparture;
import io.rollhax.nextripdomain.models.IRoute;
import io.rollhax.nextripdomain.models.IStop;

public interface INextripService {
    Observable<List<IRoute>> getRoutes();

    Observable<List<IStop>> getStops(String route);

    Observable<List<IDeparture>> getDepartures(int stopId);
}
