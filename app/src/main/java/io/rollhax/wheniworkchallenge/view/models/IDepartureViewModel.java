package io.rollhax.wheniworkchallenge.view.models;

import android.os.Parcelable;

import java.util.Date;

import io.rollhax.nextripdomain.types.DirectionType;

public interface IDepartureViewModel extends Parcelable {

    boolean isActual();

    int getBlockNumber();

    String getDepartureText();

    Date getDepartureTime();

    String getDescription();

    String getGate();

    String getRoute();

    DirectionType getRouteDirection();

    String getTerminal();

    double getVehicleHeading();

    double getVehicleLatitude();

    double getVehicleLongitude();
}
