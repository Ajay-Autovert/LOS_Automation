package genericLibrary;

import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * Utility class to handle native Chrome/OS pop-ups 
 * outside SELENIUM control.
 *
 * Author: AJAY A
 */
public class RobotClassUtils {

    /**
     * Clicks at the pop-up "OK" button using fixed coordinates.
     */
    public static void dismissPasswordPopup() {
        try {
            Robot robot = new Robot();

            // Small wait to ensure pop-up is visible
            Thread.sleep(2000);

            // Coordinates for OK button (adjust if needed for resolution/zoom)
            int buttonX = 945;
            int buttonY = 345;

            // Move mouse and click
            robot.mouseMove(buttonX, buttonY);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            System.out.println("[RobotClassUtils] Password popup dismissed at (" + buttonX + "," + buttonY + ")");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tracks mouse coordinates in real-time.
     * Run this to identify X/Y position of buttons or pop-ups.
     * Press CTRL+C to stop. Or shop it manually
     */
    public static void trackMousePosition() {
        try {
            System.out.println("Move your mouse to target location (press CTRL+C to stop):\n");

            while (true) {
                Point p = MouseInfo.getPointerInfo().getLocation();
                System.out.print("\rX: " + p.x + " | Y: " + p.y);
                Thread.sleep(300);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
