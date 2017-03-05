package io.rollhax.nextripdomain.models;

import android.os.Parcelable;

public interface IStop extends Parcelable {

    /**
     * Stops are simply returned as key/value pairs.
     */
    interface Json {
        String DESCRIPTION = "Text";
        String STOP_ID = "Value";
    }

    String getStopDescription();

    void setStopDescription(String stopDescription);

    String getStopId();

    void setStopId(String stopId);
}
