package io.rollhax.wheniworkchallenge.view.activity;

import java.util.List;

public interface IListView<T> {
    void setListItems(List<T> routes);

    void displayListItems(List<T> routes);
}
