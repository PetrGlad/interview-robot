package petrglad.idealo;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static petrglad.idealo.CommandId.*;

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
        switch (parseCommandId(tokens.get(0))) {
            case POSITION: {
                return new Robot(
                        Integer.parseInt(tokens.get(1)),
                        Integer.parseInt(tokens.get(2)),
                        parseDirection(tokens.get(3)));
            }
            case WAIT:
                return robot;
            case FORWARD: {
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
            case RIGHT:
                return new Robot(robot.x, robot.y, robot.direction.turnRight());
            case LEFT:
                return new Robot(robot.x, robot.y, robot.direction.turnLeft());
            case TURNAROUND:
                return new Robot(robot.x, robot.y, robot.direction.turnBack());
            default:
                throw new IllegalArgumentException("Unexpected command name: " + tokens.get(0));
        }
    }

    public Robot run(String script) {
        Robot robot = null;
        for (var command : parseScript(script)) {
            robot = processCommand(robot, command);
        }
        return robot;
    }
}
