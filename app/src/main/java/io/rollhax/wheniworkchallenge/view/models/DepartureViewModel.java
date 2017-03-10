package io.rollhax.wheniworkchallenge.view.models;

import android.os.Parcel;

import java.util.Date;

import io.rollhax.nextripdomain.models.Departure;
import io.rollhax.nextripdomain.types.DirectionType;

public class DepartureViewModel implements IDepartureViewModel {

    private Departure mWrappedDeparture;

    public DepartureViewModel(Departure departure) {
        mWrappedDeparture = departure;
    }

    //region IDepartureViewModel
    @Override
    public boolean isActual() {
        return mWrappedDeparture.isActual();
    }

    @Override
    public int getBlockNumber() {
        return mWrappedDeparture.getBlockNumber();
    }

    @Override
    public String getDepartureText() {
        return mWrappedDeparture.getDepartureText();
    }

    @Override
    public Date getDepartureTime() {
        return mWrappedDeparture.getDepartureTime();
    }

    @Override
    public String getDescription() {
        return mWrappedDeparture.getDescription();
    }

    @Override
    public String getGate() {
        return mWrappedDeparture.getGate();
    }

    @Override
    public String getRoute() {
        return mWrappedDeparture.getRoute();
    }

    @Override
    public DirectionType getRouteDirection() {
        return mWrappedDeparture.getRouteDirection();
    }

    @Override
    public String getTerminal() {
        return mWrappedDeparture.getTerminal();
    }

    @Override
    public long getVehicleHeading() {
        return mWrappedDeparture.getVehicleHeading();
    }

    @Override
    public long getVehicleLatitude() {
        return mWrappedDeparture.getVehicleLatitude();
    }

    @Override
    public long getVehicleLongitude() {
        return mWrappedDeparture.getVehicleLongitude();
    }
    //endregion

    //region Parcelable
    private DepartureViewModel(Parcel in) {
        mWrappedDeparture = in.readParcelable(Departure.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(mWrappedDeparture, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DepartureViewModel> CREATOR = new Creator<DepartureViewModel>() {
        @Override
        public DepartureViewModel createFromParcel(Parcel parcel) {
            return new DepartureViewModel(parcel);
        }

        @Override
        public DepartureViewModel[] newArray(int size) {
            return new DepartureViewModel[size];
        }
    };
    //endregion
}
