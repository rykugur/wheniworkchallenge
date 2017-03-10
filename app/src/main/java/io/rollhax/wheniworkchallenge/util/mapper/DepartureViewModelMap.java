package io.rollhax.wheniworkchallenge.util.mapper;

import io.reactivex.functions.Function;
import io.rollhax.nextripdomain.models.Departure;
import io.rollhax.wheniworkchallenge.view.models.DepartureViewModel;
import io.rollhax.wheniworkchallenge.view.models.IDepartureViewModel;

public class DepartureViewModelMap implements Function<Departure, IDepartureViewModel> {
    @Override
    public IDepartureViewModel apply(Departure departure) throws Exception {
        return new DepartureViewModel(departure);
    }
}
