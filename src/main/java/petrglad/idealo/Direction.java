package petrglad.idealo;

public enum Direction {
    EAST,
    SOUTH,
    WEST,
    NORTH;

    Direction turnLeft() {
        return switch (this) {
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            case NORTH -> WEST;
        };
    }

    Direction turnRight() {
        return switch (this) {
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            case NORTH -> EAST;
        };
    }

    Direction turnBack() {
        return this.turnRight().turnRight();
    }
}
