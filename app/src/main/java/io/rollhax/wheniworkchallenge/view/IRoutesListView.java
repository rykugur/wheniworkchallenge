package io.rollhax.wheniworkchallenge.view;

import android.support.annotation.StringRes;

import java.util.List;

import io.rollhax.nextripdomain.models.Route;

public interface IRoutesListView {
    void showProgress(boolean show);

    void displayRoutes(List<Route> routes);

    void showError(@StringRes int error);
}
