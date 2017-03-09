package io.rollhax.wheniworkchallenge.view;

import io.rollhax.wheniworkchallenge.listener.IStopClickListener;
import io.rollhax.wheniworkchallenge.view.models.IStopViewModel;

public interface IStopItemView {
    void showStop(IStopViewModel stop);

    void setStopClickListener(IStopClickListener listener);
}
