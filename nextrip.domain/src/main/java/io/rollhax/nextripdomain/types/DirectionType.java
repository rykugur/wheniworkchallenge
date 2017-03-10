package io.rollhax.nextripdomain.types;

import io.rollhax.nextripdomain.models.TextValuePair;

public enum DirectionType {
    SOUTH(1, "SOUTHBOUND"),
    EAST(2, "EASTBOUND"),
    WEST(3, "WESTBOUND"),
    NORTH(4, "NORTHBOUND"),
    UNKNOWN(-1, "UNKNOWN");

    private int mServerId;
    private String mServerString;

    DirectionType(int serverId, String serverString) {
        mServerId = serverId;
        mServerString = serverString;
    }

    public int getServerId() {
        return mServerId;
    }

    public String getServerString() {
        return mServerString;
    }

    public static DirectionType from(int serverId) {
        DirectionType type = UNKNOWN;

        for (DirectionType direction : values()) {
            if (direction.mServerId == serverId) {
                type = direction;
                break;
            }
        }

        return type;
    }

    public static DirectionType from(String serverString) {
        DirectionType type = UNKNOWN;

        for (DirectionType direction : values()) {
            if (direction.mServerString.equals(serverString)) {
                type = direction;
                break;
            }
        }

        return type;
    }

    public static DirectionType from(TextValuePair textValuePair) {
        try {
            int value = Integer.valueOf(textValuePair.getValue());
            return from(value);
        } catch (NumberFormatException e) {
            return UNKNOWN;
        }
    }
}
