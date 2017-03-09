package io.rollhax.wheniworkchallenge.util.mapper;

import io.reactivex.functions.Function;
import io.rollhax.nextripdomain.models.Stop;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;
import io.rollhax.wheniworkchallenge.view.models.StopViewModel;

public class StopViewModelMap implements Function<Stop, IStopViewModel> {
    @Override
    public IStopViewModel apply(Stop stop) throws Exception {
        return new StopViewModel(stop);
    }
}
