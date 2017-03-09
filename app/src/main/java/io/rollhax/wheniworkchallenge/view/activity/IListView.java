package io.rollhax.wheniworkchallenge.view.activity;

import java.util.List;

public interface IListView<T> {
    void setRoutes(List<T> routes);

    void displayRoutes(List<T> routes);
}
