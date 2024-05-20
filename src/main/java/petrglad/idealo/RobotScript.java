package petrglad.idealo;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Robot script interpreter.
 * X coordinate raises from West to East.
 * Y coordinate raises from North to South.
 */
public class RobotScript {

    static List<String> parseScriptLine(String line) {
        return Splitter.onPattern("\s+")
                .omitEmptyStrings()
                .splitToList(line);
    }

    static Iterable<List<String>> parseScript(String text) {
        return text.lines()
                .map(RobotScript::parseScriptLine)
                .filter(tokens -> !tokens.isEmpty())
                .collect(Collectors.toList());
    }

    static Direction parseDirection(String tag) {
        return Enum.valueOf(Direction.class, tag.trim().toUpperCase());
    }

    static CommandId parseCommandId(String tag) {
        return Enum.valueOf(CommandId.class, tag.trim().toUpperCase());
    }

    Robot processCommand(Robot robot, List<String> tokens) {
        return switch (parseCommandId(tokens.get(0))) {
            case POSITION -> new Robot(
                    Integer.parseInt(tokens.get(1)),
                    Integer.parseInt(tokens.get(2)),
                    parseDirection(tokens.get(3)));
            case WAIT -> robot;
            case FORWARD -> moveForward(robot, tokens);
            case RIGHT -> new Robot(robot.x, robot.y, robot.direction.turnRight());
            case LEFT -> new Robot(robot.x, robot.y, robot.direction.turnLeft());
            case TURNAROUND -> new Robot(robot.x, robot.y, robot.direction.turnBack());
            default -> throw new IllegalArgumentException("Unsupported command: " + tokens.get(0));
        };
    }

    private static Robot moveForward(Robot robot, List<String> tokens) {
        int steps = Integer.parseInt(tokens.get(1));
        int x = robot.x;
        int y = robot.y;
        switch (robot.direction) {
            case EAST: {
                x += steps;
                break;
            }
            case WEST: {
                x -= steps;
                break;
            }
            case SOUTH: {
                y += steps;
                break;
            }
            case NORTH: {
                y -= steps;
            }
        }
        return new Robot(x, y, robot.direction);
    }

    public Robot run(String script) {
        Robot robot = null;
        for (var command : parseScript(script)) {
            robot = processCommand(robot, command);
        }
        return robot;
    }
}
