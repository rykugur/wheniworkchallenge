package io.rollhax.nextripdomain.models;

import android.os.Parcel;

public class Stop implements IStop {

    //region Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    private Stop(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static final Creator<Stop> CREATOR = new Creator<Stop>() {
        @Override
        public Stop createFromParcel(Parcel parcel) {
            return new Stop(parcel);
        }

        @Override
        public Stop[] newArray(int size) {
            return new Stop[size];
        }
    };
    //endregion
}
