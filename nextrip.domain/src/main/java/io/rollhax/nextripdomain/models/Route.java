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

    public Route() {
    }

    //region IRoute
    @Override
    @Nullable
    public String getDescription() {
        return mDescription;
    }

    @Override
    public void setDescription(String description) {
        mDescription = description;
    }

    @Override
    @Nullable
    public String getProviderId() {
        return mProviderId;
    }

    @Override
    public void setProviderId(String providerId) {
        mProviderId = providerId;
    }

    @Override
    @Nullable
    public String getRoute() {
        return mRoute;
    }

    @Override
    public void setRoute(String route) {
        mRoute = route;
    }
    //endregion

    //region Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    private Route(Parcel in) {
        mDescription = in.readString();
        mProviderId = in.readString();
        mRoute = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mDescription);
        parcel.writeString(mProviderId);
        parcel.writeString(mRoute);
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
