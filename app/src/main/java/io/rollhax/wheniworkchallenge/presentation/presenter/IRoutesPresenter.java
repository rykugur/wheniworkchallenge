package io.rollhax.wheniworkchallenge.presentation.presenter;

import io.rollhax.wheniworkchallenge.presentation.IRefreshable;
import io.rollhax.wheniworkchallenge.view.activity.IRoutesListView;

public interface IRoutesPresenter extends IRefreshable {
    void onCreate(IRoutesListView presentation);
}
