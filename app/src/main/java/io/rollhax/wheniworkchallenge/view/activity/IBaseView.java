package io.rollhax.wheniworkchallenge.view.activity;

import android.support.annotation.StringRes;

public interface IBaseView {
    void showProgress(boolean show);

    void showError(@StringRes int error);
}
