package petrglad.idealo;

import java.util.Objects;

public class Robot {
    final public int x;
    final public int y;
    final public Direction direction;

    public Robot(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Robot robot)) return false;
        return x == robot.x && y == robot.y && direction == robot.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, direction);
    }

    @Override
    public String toString() {
        return "Robot{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                '}';
    }
}
