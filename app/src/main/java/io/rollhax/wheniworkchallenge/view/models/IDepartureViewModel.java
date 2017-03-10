package io.rollhax.wheniworkchallenge.view.models;

import android.os.Parcelable;

import java.util.Date;

import io.rollhax.nextripdomain.types.DirectionType;

public interface IDepartureViewModel extends Parcelable {

    public boolean isActual();

    public int getBlockNumber();

    public String getDepartureText();

    public Date getDepartureTime();

    public String getDescription();

    public String getGate();

    public String getRoute();

    public DirectionType getRouteDirection();

    public String getTerminal();

    public long getVehicleHeading();

    public long getVehicleLatitude();

    public long getVehicleLongitude();
}
