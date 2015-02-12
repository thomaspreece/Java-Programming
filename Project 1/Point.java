/*
 * PROJECT I: Project1.java
 *
 * This file contains a template for the class Point. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */
public class Point {
    /**
     * x and y co-ordinates of the point on the plane. 
     */
    private double X, Y;
    
    /**
     * GEOMTOL is described in the formulation. It describes the tolerance
     * that we are going to use throughout this project. Remember that you do
     * NOT need to redefine this in other classes.
     */
    public static final double GEOMTOL = 1.0e-6;

    // =========================
    // Constructors
    // =========================
    
    /**
     * Default constructor - this initializes X and Y to the point (0,0).
     */
    public Point() {
	// This method is complete.
	setPoint(0.0,0.0);
    }

   /**
    * Two-parameter version of the constructor. Initialiases (X,Y) to the
    * a point (a,b) which is supplied to the function.
    *
    * @param X - x-coordinate of the point
    * @param Y - y-coordinate of the point
    */
    public Point (double X, double Y) {
	// This method is complete.
	setPoint(X,Y);
    }

    // =========================
    // Setters and Getters
    // =========================
    
    /**
     * Setter for instance variables - sets the coordinates of the point.
     *
     * @param X - new x-coordinate for this point.
     * @param Y - new y-coordinate for this point
     */
    public void setPoint(double X, double Y) {
	// This method is complete.
	this.X = X;
	this.Y = Y;
    }

    /**
     * Getter for x co-ordinate. 
     *
     * @param  none
     * @return The x co-ordinate of this point.
     */   
    public double getX() {
		return this.X;
    }

    /**
     * Getter for y co-ordinate. 
     *
     * @param  none
     * @return The y co-ordinate of this point.
     */   
    public double getY() {
		return this.Y;
    }
    
    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Point.
     *
     * @return A String of the form [X,Y]
     */
    public String toString() {
	// This method is complete.
        return "[" + X + "," + Y + "]";
    }

    // ==========================
    // Implementors
    // ==========================
    
    /**
     * Compute the distance of this Point to the supplied Point x.
     *
     * @param x  Point from which the distance should be measured.
     * @return   The distance between x and this instance
     */
    public double Distance(Point x) {
	// This method is complete
		double distx = (this.X)-(x.X); //Distance between x coordinates = x2 - x1
		double disty = (this.Y)-(x.Y); //Distance between y coordinates = y2 - y1
		double EuclideanDistance = Math.sqrt(Math.pow(distx,2)+Math.pow(disty,2)); //Finds the distance between two points using formula specified in brief
		return EuclideanDistance; //Returns distance to the function that called this function
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    // -----------------------------------------------------------------------
    // Do not change the two methods below! They are essential for marking the
    // project, and you may lose marks if they are changed.
    //
    // We shall talk about these functions later (week 17).
    // -----------------------------------------------------------------------

    /**
     * Compare this with some Object. Two points are equal if their are in a
     * box given by the constant GEOMTOL.
     *
     * @param obj The object to compare with.
     * @return If obj is a Quaternion with the same coefficients.
     */
    public boolean equals(Object obj) {
	// This method is complete.
	if (obj instanceof Point) {
            Point q = (Point)obj;
            return (Math.abs(X - q.X) <= GEOMTOL && 
                    Math.abs(Y - q.Y) <= GEOMTOL);
	} else {
            return false;
        }
    }

    /**
     * Compare two points. Two points are equal if their are in a box given by
     * the constant GEOMTOL.
     *
     * @param q  A Point to be compared to this instance
     * @return   true if Point q is equal to this instance.
     */
    public boolean equals(Point q) {
	return (Math.abs(X - q.X) <= GEOMTOL && 
		Math.abs(Y - q.Y) <= GEOMTOL);
    }

    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main (String args[]) {
		Point point1 = new Point(); //creates a point at (0,0)
		Point point2 = new Point(1,2); //creates a point at (1,2)
		System.out.println("X coord of point2: "+point2.getX()); //outputs X value of point2
		System.out.println("Y coord of point2: "+point2.getY()); //outputs x value of point2
		System.out.println("Coords of point 1: "+point1.toString()); //outputs coords of point1
		System.out.println("Distance between point1 and point2: "+point2.Distance(point1)); // Outputs distance between point1 and point2
		point1.setPoint(4,5); //sets point1 to position (4,5)
		System.out.println("New coords of point 1: "+point1.toString()); //outputs coords of point1
		System.out.println("New distance between point1 and point2: "+point2.Distance(point1)); // Outputs new distance between point1 and point2
    } 
}
