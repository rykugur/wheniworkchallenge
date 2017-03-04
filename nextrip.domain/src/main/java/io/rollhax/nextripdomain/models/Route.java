package io.rollhax.nextripdomain.models;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class Route implements IRoute {

    @SerializedName(Json.DESCRIPTION)
    private String mDescription;
    @SerializedName(Json.PROVIDER_ID)
    private String mProviderId;
    @SerializedName(Json.ROUTE)
    private String mRoute;

    //region IRoute
    @Override
    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Override
    @Nullable
    public String getProviderId() {
        return mProviderId;
    }

    @Override
    @Nullable
    public String getRoute() {
        return mRoute;
    }
    //endregion

    //region Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    private Route(Parcel in) {
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

    public static final Creator<Route> CREATOR = new Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel parcel) {
            return new Route(parcel);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
    //endregion
}
