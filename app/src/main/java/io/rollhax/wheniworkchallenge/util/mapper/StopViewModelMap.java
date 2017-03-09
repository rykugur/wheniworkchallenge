package io.rollhax.wheniworkchallenge.util.mapper;

import android.support.annotation.NonNull;

import io.reactivex.functions.Function;
import io.rollhax.nextripdomain.models.Stop;
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;
import io.rollhax.wheniworkchallenge.view.models.StopViewModel;

public class StopViewModelMap implements Function<Stop, IStopViewModel> {

    private DirectionType mDirectionType;

    public StopViewModelMap(@NonNull DirectionType directionType) {
        mDirectionType = directionType;
    }

    @Override
    public IStopViewModel apply(Stop stop) throws Exception {
        return new StopViewModel(stop, mDirectionType);
    }
}
