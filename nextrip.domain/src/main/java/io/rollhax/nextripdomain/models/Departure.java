package io.rollhax.nextripdomain.models;

import android.os.Parcel;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.Date;

import io.rollhax.nextripdomain.GsonFactory;
import io.rollhax.nextripdomain.types.DirectionType;
import io.rollhax.utils.serialization.json.annotations.SkipAutomaticDeserialization;
import io.rollhax.utils.serialization.json.annotations.SkipAutomaticSerialization;

public class Departure implements IDeparture {

    @SerializedName(Json.ACTUAL)
    private boolean mActual;
    @SerializedName(Json.BLOCK_NUMBER)
    private int mBlockNumber;
    @SerializedName(Json.DEPARTURE_TEXT)
    private String mDepartureText;
    @SerializedName(Json.DEPARTURE_TIME)
    @SkipAutomaticSerialization
    @SkipAutomaticDeserialization
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
    private double mVehicleHeading;
    @SerializedName(Json.VEHICLE_LATITUDE)
    private double mVehicleLatitude;
    @SerializedName(Json.VEHICLE_LONGITUDE)
    private double mVehicleLongitude;

    private Departure() {
    }

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
    public double getVehicleHeading() {
        return mVehicleHeading;
    }

    @Override
    public void setVehicleHeading(double vehicleHeading) {
        mVehicleHeading = vehicleHeading;
    }

    @Override
    @Nullable
    public double getVehicleLatitude() {
        return mVehicleLatitude;
    }

    @Override
    public void setVehicleLatitude(double vehicleLatitude) {
        mVehicleLatitude = vehicleLatitude;
    }

    @Override
    @Nullable
    public double getVehicleLongitude() {
        return mVehicleLongitude;
    }

    @Override
    public void setVehicleLongitude(double vehicleLongitude) {
        mVehicleLongitude = vehicleLongitude;
    }
    //endregion

    //region Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    private Departure(Parcel in) {
        mActual = in.readByte() == 1;
        mBlockNumber = in.readInt();
        mDepartureText = in.readString();
        long tmpDate = in.readLong();
        mDepartureTime =  tmpDate >= 0 ? new Date(tmpDate) : null;
        mDescription = in.readString();
        mGate = in.readString();
        mRoute = in.readString();
        mRouteDirection = DirectionType.from(in.readInt());
        mTerminal = in.readString();
        mVehicleHeading = in.readDouble();
        mVehicleLatitude = in.readDouble();
        mVehicleLongitude = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(mActual ? (byte) 1 : (byte) 0);
        parcel.writeInt(mBlockNumber);
        parcel.writeString(mDepartureText);
        parcel.writeLong(mDepartureTime != null ? mDepartureTime.getTime() : -1);
        parcel.writeString(mDescription);
        parcel.writeString(mGate);
        parcel.writeString(mRoute);
        parcel.writeInt(mRouteDirection.getServerId());
        parcel.writeString(mTerminal);
        parcel.writeDouble(mVehicleHeading);
        parcel.writeDouble(mVehicleLatitude);
        parcel.writeDouble(mVehicleLongitude);
    }

    public static final Creator<Departure> CREATOR = new Creator<Departure>() {
        @Override
        public Departure createFromParcel(Parcel parcel) {
            return new Departure(parcel);
        }

        @Override
        public Departure[] newArray(int size) {
            return new Departure[size];
        }
    };
    //endregion

    //region Serdes
    public static class CustomDeserializer implements JsonDeserializer<Departure> {
        private static final Gson GSON = GsonFactory.newBuilder()
                .setDateFormat(DateFormat.FULL)
                .create();

        @Override
        public Departure deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            Departure departure = GSON.fromJson(json, type);

            JsonObject root = json.getAsJsonObject();

            // this is a disgusting time format
            // TODO: surely there's a library or DateFormat that can handle this.
            if (root.has(Json.DEPARTURE_TIME)) {
                JsonElement time = root.get(Json.DEPARTURE_TIME);
                if (time != null && !time.isJsonNull() && time.isJsonPrimitive()) {
                    String timeString = time.getAsString();
                    timeString = timeString.replace("/Date(", "");
                    timeString = timeString.substring(0, timeString.indexOf("-"));
                    try {
                        long timeMs = Long.parseLong(timeString);
                        departure.setDepatureTime(new Date(timeMs));
                    } catch (NumberFormatException e) {
                        departure.setDepartureText(null);
                    }
                }
            }

            if (root.has(Json.ROUTE_DIRECTION)) {
                JsonElement direction = root.get(Json.ROUTE_DIRECTION);
                if (direction != null && !direction.isJsonNull() && direction.isJsonPrimitive()) {
                    String directionString = direction.getAsString();
                    departure.setRouteDirection(DirectionType.from(directionString));
                }
            }

            return departure;
        }
    }
    //endregion
}
