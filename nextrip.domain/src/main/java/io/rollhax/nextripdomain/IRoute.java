package io.rollhax.nextripdomain;

import android.support.annotation.Nullable;

public interface IRoute {

    interface Json {
        String DESCRIPTION = "Description";
        String PROVIDER_ID = "ProviderID";
        String ROUTE = "Route";
    }

    @Nullable
    String getDescription();

    /**
     * The NexTrip API returns this as a string, but it's labeled an ID. That is odd.
     */
    @Nullable
    String getProviderId();

    @Nullable
    String getRoute();
}
