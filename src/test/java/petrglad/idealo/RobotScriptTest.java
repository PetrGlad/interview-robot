package petrglad.idealo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RobotScriptTest {
    // Script from the task specification
    static String defaultScript = """
            POSITION 1 3 EAST //sets the initial position for the robot
            FORWARD 3 //lets the robot do 3 steps forward
            WAIT //lets the robot do nothing
            TURNAROUND //lets the robot turn around
            FORWARD 1 //lets the robot do 1 step forward
            RIGHT //lets the robot turn right
            FORWARD 2 //lets the robot do 2 steps forward
            """;

    @Test
    void testRun() {
        assertEquals(new Robot(3, 1, Direction.NORTH), new RobotScript().run(defaultScript));
    }

    @Test
    void testRotation() {
        var script1 = """
                POSITION 1 1 SOUTH
                LEFT
                LEFT
                LEFT
                LEFT
                """;
        assertEquals(new Robot(1, 1, Direction.SOUTH), new RobotScript().run(script1));

        var script2 = """
                POSITION 1 1 SOUTH
                LEFT
                """;
        assertEquals(new Robot(1, 1, Direction.EAST), new RobotScript().run(script2));

        var script3 = """
                POSITION 1 1 SOUTH
                RIGHT
                """;
        assertEquals(new Robot(1, 1, Direction.WEST), new RobotScript().run(script3));
    }

    @Test
    void testScriptFormatting() {
        var script1 = """
                POSITION -10 13        SOUTH
                     LEFT //
                FORWARD 77 // A comment here.
                
                turnaround
                
                """;
        assertEquals(new Robot(67, 13, Direction.WEST), new RobotScript().run(script1));
    }
}