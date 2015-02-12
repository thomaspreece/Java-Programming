/*
 * PROJECT I: ReadData.java
 *
 * A sample code which demonstrates how to read data from an ASCII file using
 * the methods of the class MaInput.
 * 
 * The code demonstrates the procedure you should use to read data from the
 * file Project1.data from a similar file you will use for testing. The code
 * also shows how the max and min coordinates are found in the process of
 * reading.
 *
 * You should NOT submit this to BOSS, but use it as a guide to help you
 * implement the Project1 class.
 *
 * @author  D. Moxey and P. Plechac
 * @version 1.1
 */

public class ReadData {
    public static void main(String args[]) {
        // Defines an instance F1 of the input class MaInput which connects
        // the input stream to the file Project1.data (if you want to test it
        // with your own file, just change the name Project1.data into the
        // name of your file). The format of this file is simple: each line
        // contains four numbers: x y radius which represent (x,y) -
        // co-ordinates of the center, and rad is the radius.
        MaInput F1 = new MaInput("Project1.data");

        // Variables into which coordinates of points will be stored.
        double x, y, rad;
        
        // Variables which will store min and max for each co-ordinate. See
        // lectures in week 16 for more information; basically,
        // Double.MIN_VALUE is the smallest number a double can hold, and
        // Double.MAX_VALUE is the largest.
        double maxX = Double.MIN_VALUE;
        double minX = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        
        // Counter for the number of entered points.
        int points = 0;

        // Read the file until the EOF (end-of-file) mark in the file is reached.
        while (!F1.atEOF()) {
            // Read the coordinates from one line in the file. We know that
            // there are 3 numbers on a line so the following commands read
            // all data on a line.
            x   = F1.readDouble();
            y   = F1.readDouble();
            rad = F1.readDouble();

            // Count the points entered.
            points++;
            
            // Find max, min for x and y coords
            if (x > maxX) 
                maxX = x;
            if (y > maxY) 
                maxY = y;
            if (x < minX) 
                minX = x;
            if (y < minY) 
                minY = y;
        }

        // Print min, max values (Note use of MaF.dF method to format output
        // of floating point numbers).
        System.out.println("Information about the data:");
        System.out.println("  Number of points entered: " + points);

        System.out.println("  Max x-coord: " + MaF.dF(maxX,6,2));
        System.out.println("  Min x-coord: " + MaF.dF(minX,6,2));
        System.out.println("  Max y-coord: " + MaF.dF(maxY,6,2));
        System.out.println("  Min y-coord: " + MaF.dF(minY,6,2));
    }
}

