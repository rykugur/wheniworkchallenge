package io.rollhax.wheniworkchallenge.util.mapper;

import io.reactivex.functions.Function;
import io.rollhax.nextripdomain.models.Route;
import io.rollhax.wheniworkchallenge.view.models.IRouteViewModel;
import io.rollhax.wheniworkchallenge.view.models.RouteViewModel;

public class RouteViewModelMap implements Function<Route, IRouteViewModel> {
    @Override
    public IRouteViewModel apply(Route route) throws Exception {
        return new RouteViewModel(route);
    }
}
