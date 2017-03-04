package io.rollhax.nextripdomain.models;

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
}
