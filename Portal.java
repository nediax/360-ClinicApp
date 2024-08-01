/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.3.1
 * Original File Version: March 19, 2024
 * File Last Updated: April 9, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the Portal classes. The Portal classes display the different portals
 *  (also known as Screens) of the coding system 
 */


package asuJavaFX360;

// Import necessary JavaFX libraries
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

// Abstract class representing a Portal
abstract class Portal {
    // Dimensions of the portal window
    protected int xDimension;
    protected int yDimension;

    // Constructor to initialize the dimensions based on the screen size
    public Portal() {
        // Get the primary screen's bounds
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Set the dimensions of the portal window to match the screen size
        this.xDimension = (int) screenBounds.getWidth();
        this.yDimension = (int) screenBounds.getHeight();
    }

    // Abstract method to display the interface of the portal
    public abstract void displayInterface();
}
