package io.rollhax.wheniworkchallenge.view.models;

import android.os.Parcelable;

import io.rollhax.nextripdomain.types.DirectionType;

public interface IStopViewModel extends Parcelable {
    String getStopDescription();

    String getStopId();

    DirectionType getDirectionType();
}
