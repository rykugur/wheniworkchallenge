package io.rollhax.nextripdomain.models;

import android.os.Parcelable;
import android.support.annotation.Nullable;

public interface IRoute extends Parcelable {

    interface Json {
        String DESCRIPTION = "Description";
        String PROVIDER_ID = "ProviderID";
        String ROUTE = "Route";
    }

    @Nullable
    String getDescription();

    void setDescription(String description);

    /**
     * The NexTrip API returns this as a string, but it's labeled an ID. That is odd.
     */
    @Nullable
    String getProviderId();

    void setProviderId(String providerId);

    @Nullable
    String getRoute();

    void setRoute(String route);
}
