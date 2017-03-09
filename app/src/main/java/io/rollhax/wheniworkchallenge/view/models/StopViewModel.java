package io.rollhax.wheniworkchallenge.view.models;

import android.os.Parcel;

import io.rollhax.nextripdomain.models.Stop;

public class StopViewModel implements IStopViewModel {

    private Stop mWrappedStop;

    public StopViewModel(Stop stop) {
        mWrappedStop = stop;
    }

    //region IStopViewModel
    @Override
    public String getStopDescription() {
        return mWrappedStop.getStopDescription();
    }

    @Override
    public String getStopId() {
        return mWrappedStop.getStopId();
    }
    //endregion

    //region Parcelable
    private StopViewModel(Parcel in) {
        mWrappedStop = in.readParcelable(Stop.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(mWrappedStop, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StopViewModel> CREATOR = new Creator<StopViewModel>() {
        @Override
        public StopViewModel createFromParcel(Parcel parcel) {
            return new StopViewModel(parcel);
        }

        @Override
        public StopViewModel[] newArray(int size) {
            return new StopViewModel[size];
        }
    };
    //endregion
}
