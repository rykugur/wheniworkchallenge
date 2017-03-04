package io.rollhax.nextripdomain.types;

public enum DirectionType {
    SOUTH(1),
    EAST(2),
    WEST(3),
    NORTH(4),
    UNKNOWN(-1);

    private int mServerId;

    DirectionType(int serverId) {
        mServerId = serverId;
    }

    public int getServerId() {
        return mServerId;
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
}
