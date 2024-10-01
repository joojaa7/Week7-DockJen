import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class SportsActivityLoggerTest {
    private SportsActivityLogger logger;

    @BeforeEach
    void setUp() {
        logger = new SportsActivityLogger();
    }

    @Test
    void logActivity() {
        logger.logActivity("Running", 2.0);
        assertEquals(2.0, logger.calculateTotalTime());
    }

    @Test
    void viewLoggedActivities() {
        logger.logActivity("Running", 2.0);
        logger.logActivity("Swimming", 1.5);

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        logger.viewLoggedActivities();

        String expectedOutput  = "Sport: Running, Hours: 2.0\r\nSport: Swimming, Hours: 1.5\r\n";

        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void calculateTotalTime() {
        logger.logActivity("Running", 2.0);
        logger.logActivity("Swimming", 1.5);
        logger.logActivity("Cycling", 3.0);

        assertEquals(6.5, logger.calculateTotalTime());
    }
}