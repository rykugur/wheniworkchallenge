package io.rollhax.wheniworkchallenge.view.models;

import android.os.Parcelable;

public interface IRouteViewModel extends Parcelable {
    String getDescription();

    String getProviderId();

    String getRoute();
}
