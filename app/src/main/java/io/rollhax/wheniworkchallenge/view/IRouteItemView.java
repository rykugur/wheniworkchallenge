package io.rollhax.wheniworkchallenge.view;

import io.rollhax.wheniworkchallenge.listener.IRouteClickListener;
import io.rollhax.wheniworkchallenge.view.models.IRouteViewModel;

public interface IRouteItemView {
    void showRoute(IRouteViewModel route);

    void setRouteClickListener(IRouteClickListener listener);
}
