package io.rollhax.wheniworkchallenge.presentation.presenter;

import io.rollhax.wheniworkchallenge.view.IRoutesView;

public class RoutesPresenter implements IRoutesPresenter {

    private IRoutesView mPresentation;

    //region IRoutesView
    @Override
    public void onCreate(IRoutesView presentation) {
        mPresentation = presentation;
    }
    //endregion
}
