package io.rollhax.wheniworkchallenge.presentation.presenter;

import io.rollhax.wheniworkchallenge.presentation.IRefreshable;
import io.rollhax.wheniworkchallenge.view.IStopsListView;

public interface IStopsPresenter extends IRefreshable {
    void onCreate(IStopsListView presentation);

    void setRoute(String route);
}
