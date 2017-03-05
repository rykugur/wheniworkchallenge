package io.rollhax.nextripdomain.models;

import android.os.Parcel;

import com.google.gson.annotations.SerializedName;

public class Stop implements IStop {

    @SerializedName(Json.DESCRIPTION)
    private String mStopDescription;
    @SerializedName(Json.STOP_ID)
    private String mStopId;

    public Stop(TextValuePair textValuePair) {
        mStopDescription = textValuePair.getText();
        mStopId = textValuePair.getValue();
    }

    //region IStop
    @Override
    public String getStopDescription() {
        return mStopDescription;
    }

    @Override
    public void setStopDescription(String stopDescription) {
        mStopDescription = stopDescription;
    }

    @Override
    public String getStopId() {
        return mStopId;
    }

    @Override
    public void setStopId(String stopId) {
        mStopId = stopId;
    }
    //endregion

    //region Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    private Stop(Parcel in) {
        mStopDescription = in.readString();
        mStopId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mStopDescription);
        parcel.writeString(mStopId);
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
