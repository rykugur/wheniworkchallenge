package io.rollhax.nextripservice.service;

import java.util.List;

import io.reactivex.Observable;
import io.rollhax.nextripdomain.IDeparture;
import io.rollhax.nextripdomain.IRoute;
import io.rollhax.nextripdomain.IStop;
import io.rollhax.nextripdomain.types.DirectionType;

public interface INextripService {
    Observable<List<IRoute>> getRoutes();

    Observable<List<IStop>> getStops(String route);

    Observable<List<IDeparture>> getDepartures(int stopId);
}
