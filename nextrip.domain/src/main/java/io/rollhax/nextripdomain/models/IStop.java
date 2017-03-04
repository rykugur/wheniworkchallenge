package io.rollhax.nextripdomain.models;

import android.os.Parcelable;
import android.support.annotation.Nullable;

import java.util.Date;

import io.rollhax.nextripdomain.types.DirectionType;

public interface IStop extends Parcelable {

    interface Json {
        String ACTUAL = "Actual";
        String BLOCK_NUMBER = "BlockNumber";
        String DEPARTURE_TEXT = "DepartureText";
        String DEPARTURE_TIME = "DepartureTime";
        String DESCRIPTION = "Description";
        String GATE = "Gate";
        String ROUTE = "Route";
        String ROUTE_DIRECTION = "RouteDirection";
        String TERMINAL = "Terminal";
        String VEHICLE_HEADING = "VehicleHeading";
        String VEHICLE_LATITUDE = "VehicleLatitude";
        String VEHICLE_LONGITUDE = "VehicleLongitude";
    }

    @Nullable
    boolean isActual();

    void setActual(boolean actual);

    @Nullable
    int getBlockNumber();

    void setBlockNumber(int blockNumber);

    @Nullable
    String getDepartureText();

    void setDepartureText(String departureText);

    @Nullable
    Date getDepartureTime();

    void setDepatureTime(Date depatureTime);

    @Nullable
    String getDescription();

    void setDescription(String description);

    @Nullable
    String getGate();

    void setGate(String gate);

    @Nullable
    String getRoute();

    void setRoute(String route);

    @Nullable
    DirectionType getRouteDirection();

    void setRouteDirection(DirectionType routeDirection);

    @Nullable
    String getTerminal();

    void setTerminal(String terminal);

    @Nullable
    long getVehicleHeading();

    void setVehicleHeading(long vehicleHeading);

    @Nullable
    long getVehicleLatitude();

    void setVehicleLatitude(long vehicleLatitude);

    @Nullable
    long getVehicleLongitude();

    void setVehicleLongitude(long vehicleLongitude);
}
