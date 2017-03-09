package io.rollhax.wheniworkchallenge.view.models;

import android.os.Parcel;

import io.rollhax.nextripdomain.models.Route;

public class RouteViewModel implements IRouteViewModel {

    private Route mWrappedRoute;

    public RouteViewModel(Route route) {
        mWrappedRoute = route;
    }

    //region IRouteViewModel
    @Override
    public String getDescription() {
        return mWrappedRoute.getDescription();
    }

    @Override
    public String getProviderId() {
        return mWrappedRoute.getProviderId();
    }

    @Override
    public String getRoute() {
        return mWrappedRoute.getRoute();
    }
    //endregion

    //region Parcelable
    private RouteViewModel(Parcel in) {
        mWrappedRoute = in.readParcelable(Route.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(mWrappedRoute, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<RouteViewModel> CREATOR = new Creator<RouteViewModel>() {
        @Override
        public RouteViewModel createFromParcel(Parcel parcel) {
            return new RouteViewModel(parcel);
        }

        @Override
        public RouteViewModel[] newArray(int size) {
            return new RouteViewModel[size];
        }
    };
    //endregion
}
