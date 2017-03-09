package io.rollhax.wheniworkchallenge.view;

import android.support.annotation.StringRes;

import java.util.List;

import io.rollhax.wheniworkchallenge.view.models.IRouteViewModel;

public interface IRoutesListView {
    void showProgress(boolean show);

    void setRoutes(List<IRouteViewModel> routes);

    void displayRoutes(List<IRouteViewModel> routes);

    void showError(@StringRes int error);
}
