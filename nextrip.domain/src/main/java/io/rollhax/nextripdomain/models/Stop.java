package io.rollhax.nextripdomain.models;

import android.support.annotation.Nullable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.util.Date;

import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.utils.serialization.json.annotations.SkipAutomaticDeserialization;
import io.rollhax.utils.serialization.json.annotations.SkipAutomaticSerialization;

public class Stop implements IStop {

    @SerializedName(Json.ACTUAL)
    private boolean mActual;
    @SerializedName(Json.BLOCK_NUMBER)
    private int mBlockNumber;
    @SerializedName(Json.DEPARTURE_TEXT)
    private String mDepartureText;
    @SkipAutomaticSerialization
    @SkipAutomaticDeserialization
    @SerializedName(Json.DEPARTURE_TIME)
    private Date mDepartureTime;
    @SerializedName(Json.DESCRIPTION)
    private String mDescription;
    @SerializedName(Json.GATE)
    private String mGate;
    @SerializedName(Json.ROUTE)
    private String mRoute;
    @SkipAutomaticSerialization
    @SkipAutomaticDeserialization
    @SerializedName(Json.ROUTE_DIRECTION)
    private DirectionType mRouteDirection = DirectionType.UNKNOWN;
    @SerializedName(Json.TERMINAL)
    private String mTerminal;
    @SerializedName(Json.VEHICLE_HEADING)
    private long mVehicleHeading;
    @SerializedName(Json.VEHICLE_LATITUDE)
    private long mVehicleLatitude;
    @SerializedName(Json.VEHICLE_LONGITUDE)
    private long mVehicleLongitude;

    //region IStop
    @Override
    @Nullable
    public boolean isActual() {
        return mActual;
    }

    @Override
    public void setActual(boolean actual) {
        mActual = actual;
    }

    @Override
    @Nullable
    public int getBlockNumber() {
        return mBlockNumber;
    }

    @Override
    public void setBlockNumber(int blockNumber) {
        mBlockNumber = blockNumber;
    }

    @Override
    @Nullable
    public String getDepartureText() {
        return mDepartureText;
    }

    @Override
    public void setDepartureText(String departureText) {
        mDepartureText = departureText;
    }

    @Override
    @Nullable
    public Date getDepartureTime() {
        return mDepartureTime;
    }

    @Override
    public void setDepatureTime(Date depatureTime) {
        mDepartureTime = depatureTime;
    }

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
    public String getGate() {
        return mGate;
    }

    @Override
    public void setGate(String gate) {
        mGate = gate;
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

    @Override
    @Nullable
    public DirectionType getRouteDirection() {
        return mRouteDirection;
    }

    @Override
    public void setRouteDirection(DirectionType routeDirection) {
        mRouteDirection = routeDirection;
    }

    @Override
    @Nullable
    public String getTerminal() {
        return mDepartureText;
    }

    @Override
    public void setTerminal(String terminal) {
        mTerminal = terminal;
    }

    @Override
    @Nullable
    public long getVehicleHeading() {
        return mVehicleHeading;
    }

    @Override
    public void setVehicleHeading(long vehicleHeading) {
        mVehicleHeading = vehicleHeading;
    }

    @Override
    @Nullable
    public long getVehicleLatitude() {
        return mVehicleLatitude;
    }

    @Override
    public void setVehicleLatitude(long vehicleLatitude) {
        mVehicleLatitude = vehicleLatitude;
    }

    @Override
    @Nullable
    public long getVehicleLongitude() {
        return mVehicleLongitude;
    }

    @Override
    public void setVehicleLongitude(long vehicleLongitude) {
        mVehicleLongitude = vehicleLongitude;
    }
    //endregion

    //region Serdes
    public static class CustomDeserializer implements JsonDeserializer<Stop> {
        @Override
        public Stop deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Stop stop = new Stop();

            JsonObject root = json.getAsJsonObject();

            if (root.has(Json.DEPARTURE_TIME)) {
                JsonElement time = root.get(Json.DEPARTURE_TIME);
                if (time != null && !time.isJsonNull() && time.isJsonPrimitive()) {
                    String timeStr = time.getAsString();
                }
            }
            if (root.has(Json.ROUTE_DIRECTION)) {
                JsonElement direction = root.get(Json.ROUTE_DIRECTION);
                if (direction != null && !direction.isJsonNull() && direction.isJsonPrimitive()) {
                    int directionId = direction.getAsInt();
                    stop.setRouteDirection(DirectionType.from(directionId));
                }
            }

            return stop;
        }
    }

    public static class CustomSerializer implements JsonSerializer<Stop> {
        @Override
        public JsonElement serialize(Stop src, Type typeOfSrc, JsonSerializationContext context) {
            // parse some fields automagically

            return null;
        }
    }
    //endregion
}
