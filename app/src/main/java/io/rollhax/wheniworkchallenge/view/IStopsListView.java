package io.rollhax.wheniworkchallenge.view;

import android.support.v4.widget.SwipeRefreshLayout;

import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.wheniworkchallenge.view.activity.IBaseView;
import io.rollhax.wheniworkchallenge.view.activity.IListView;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;

public interface IStopsListView extends IListView<IStopViewModel>, IBaseView {
    void setDirectionType(DirectionType directionType);

    void setRefreshCallbackListener(SwipeRefreshLayout.OnRefreshListener swipeRefreshListener);
}
