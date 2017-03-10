package io.rollhax.nextripservice.service;

import java.util.List;

import io.reactivex.Observable;
import io.rollhax.nextripdomain.models.Departure;
import io.rollhax.nextripdomain.models.Route;
import io.rollhax.nextripdomain.models.Stop;
import io.rollhax.nextripdomain.types.DirectionType;

public interface INextripService {
    Observable<List<Route>> getRoutes();

    Observable<List<DirectionType>> getDirections(String route);

    Observable<List<Stop>> getStops(String route, int directionTypeId);

    Observable<List<Stop>> getStops(String route, String direction);

    Observable<List<Departure>> getDepartures(String route, int directionTypeId, String stopId);

    Observable<List<Departure>> getDepartures(String route, String direction, String stopId);
}
