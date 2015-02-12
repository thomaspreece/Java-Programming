/*
 * PROJECT I: Circle.java
 *
 * This file contains a template for the class Circle. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Point class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Circle {

    /*
     * Here, you should define private variables that represent the radius and
     * centre of this particular Circle. The radius should be of type double,
     * and the centre should be of type Point.
     */
	 
	 private double radius;
	 private Point centre;
	 

    // =========================
    // Constructors
    // =========================
    /**
     * Default constructor - performs no initialization.
     */
    public Circle() {
        // This method is complete.
    }
    
    /**
     * Alternative constructor, which sets the circle up with x and y
     * co-ordinates representing the centre, and a radius. Remember you should
     * not store these x and y co-ordinates explicitly, but instead create a
     * Point to hold them for you.
     *
     * @param xc   x-coordinate of the centre of the circle
     * @param yc   y-coordinate of the centre of the circle
     * @param rad  radius of the circle
     */
    public Circle(double xc, double yc, double rad) {
        radius = rad; //Sets the given variable to the private radius variable of Circle
		centre = new Point(xc,yc); //Sets the given x and y coords to a new Point object
    }

    /**
     * Alternative constructor, which sets the circle up with a Point
     * representing the centre, and a radius.
     *
     * @param center  Point representing the centre
     * @param rad     Radius of the circle
     */
    
    public Circle(Point center, double rad) {
        radius = rad; //sets the radius
		centre.setPoint(center.getX(),center.getY()); //Gets x and y coord of the point passed to function as parameter and sets them to the Point type centre of Circle
    }

    // =========================
    // Setters and Getters
    // =========================

    /**
     * Setter - sets the co-ordinates of the centre.
     *
     * @param xc  new x-coordinate of the centre
     * @param yc  new y-coordinate of the centre
     */   
    public void setCenter(double xc, double yc) {
        centre.setPoint(xc,yc); //uses the setPoint function of Point class to change the centre to (xc,yc)
    }

    /**
     * Setter - sets the centre of the circle to a new Point.
     *
     * @param C  A Point representing the new centre of the circle.
     */   
    public void setCenter(Point A) {
		centre.setPoint(A.getX(),A.getY()); //uses the setPoint function of Point class to change the centre to x and y of Point A
    }
    
    /**
     * Setter - change the radius of this circle.
     *
     * @param rad  New radius for the circle.
     */   
    public void setRadius(double rad) {
        radius = rad; //Sets Radius to the value of the parameter passed to function
    }
    
    /**
     * Getter - returns the centre of this circle.
     *
     * @return The centre of this circle (a Point).
     */   
    public Point getCenter(){
        return centre; //Returns the centre object of type Point to caller function
    }

    /**
     * Getter - extract the radius of this circle.
     *
     * @return The radius of this circle.
     */   
    public double getRadius(){
        return radius; //Returns the private variable radius to caller function
    }

    // =========================
    // Convertors
    // =========================

    /**
     * Calculates a String representation of the Circle.
     *
     * @return A String of the form: "[Ax,Ay], r=Radius" where Ax and Ay are
     *         numerical values of the coordinates, and Radius is a numerical
     *         value of the radius.
     */
    public String toString() {
	    return "["+centre.getX()+","+centre.getY()+"], r="+radius;  //returns required string as defined above
    }
    
    // ==========================
    // Service routines
    // ==========================
    
    /**
     * Similar to the equals() function in Point. Returns true if two Circles
     * are equal. By this we mean:
     * 
     * - They have the same radius (up to tolerance).
     * - They have the same centre (up to tolerance).
     * 
     * Remember that the second test is already done in the Point class!
     *
     * @return true if the two circles are equal.
     */
    public boolean equals(Circle c) {
	    boolean radiusEqual = false; 
		if (Math.abs(this.radius - c.radius) <= Point.GEOMTOL){ //Checks to see if radii are within a certain tolerance of each other
			radiusEqual = true; //If they are within tolerance set radiusEqual to true otherwise variable is left as false
		}
		boolean centreEqual = this.centre.equals(c.centre); //Uses the equals function of Point class to see if two points are the same, if so it sets centreEqual to true
		if (radiusEqual == true && centreEqual == true){ //Checks to see if both radii and centres are the same, if so it returns true to caller function otherwise it returns false
			return true; 
		} else {
			return false;
		}
    }
    
    // -----------------------------------------------------------------------
    // Do not change the method below! It is essential for marking the
    // project, and you may lose marks if it is changed.
    // -----------------------------------------------------------------------

    /**
     * Compare this Circle with some Object, using the test above.
     *
     * @param obj  The object to compare with.
     * @return true if the two objects are equal.
     */
    public boolean equals(Object obj) {
        // This method is complete.
        
        if (obj instanceof Circle) {
            boolean test = false;
            Circle C = (Circle)obj;
            
            test = this.equals(C);

            return test;
        } else {
            return false;
        }
    }

    // ======================================
    // Implementors
    // ======================================
    
    /**
     * Computes and returns the area of the circle.
     *
     * @return  Area of the circle
     */
    public double area() {
        return Math.pow(radius,2)*Math.PI; //Uses formula PI*(radius^2) to calculate area then returns the calculated value as a double
    }

    /**
     * Tests whether this circle overlaps/touches/is disjoint with another
     * Circle, as set out in the project formulation.
     *
     * @return An integer describing the overlap with C:
     *         0 - disjoint; 1 - overlaps; 2 - touches; 3 - identical.
     */
    public int overlap(Circle C) {
        if (this.equals(C) == true){ //Checks to see if circles are identical and returns 3 if true, otherwise it does other checks
			return 3;
		} else {
			double dist = this.centre.Distance(C.centre); //finds distance between the two centres of circles
			double radii = (this.radius + C.radius); //finds the total of the two radii of the circles
			
			if ( Math.abs(dist-radii) <= Point.GEOMTOL){ //checks if distance between centres is equal (within tolerance) to total of radii
					return 2; //If true then the circles touch
			} else {
				if ( dist < radii ){ //checks to see if distance between centres is less than to total of radii
					return 1; //If true then circles overlap
				} else {
					return 0; //If false then dist > radii and hense they are disjoint
				}
			}
	
		}
	}
    


    // =======================================================
    // Tester - test methods defined in this class
    // =======================================================
    
    public static void main(String args[]) {
        Circle c1 = new Circle(0,0,2); 
		Circle c2 = new Circle(0,0,2);
		System.out.println("c1: "+c1.toString()); //Tests toString function - Should output [0.0,0.0], r=2.0
		System.out.println("c2: "+c2.toString());
		System.out.println("c1 and c2 overlap?: "+c1.overlap(c2)); //Tests overlap function - should return 3(identical)
		c2.setCenter(1,0); //Tests setCenter function
		System.out.println("c1: "+c1.toString()); 
		System.out.println("c2: "+c2.toString()); //Should output [1.0,0.0], r=2.0
		System.out.println("c1 and c2 overlap?: "+c1.overlap(c2)); //Tests overlap function - should return 1(overlap)
		c2.setCenter(3,0);
		c2.setRadius(1);//Tests setRadius function
		System.out.println("c1: "+c1.toString());
		System.out.println("c2: "+c2.toString()); //Should output [3.0,0.0], r=1.0
		System.out.println("c1 and c2 overlap?: "+c1.overlap(c2)); //Tests overlap function - should return 2(touches)
		c2.setCenter(20,10);
		System.out.println("c1: "+c1.toString());
		System.out.println("c2: "+c2.toString());
		System.out.println("c1 and c2 overlap?: "+c1.overlap(c2)); //Tests overlap function - should return 0(disjoint)
		Circle c3 = new Circle(1,2,4);
		System.out.println("Two lines below should be the same: ");
		c1.setRadius(c3.getRadius()); //Testing getRadius Function 
		c1.setCenter(c3.getCenter()); //Testing getCenter and setCenter functions
		System.out.println("c3: "+c3.toString());
		System.out.println("c1: "+c1.toString());   //Should output same as line above
		System.out.println("area of c1: "+c1.area()); //Tests area function - should output ~50
    }
}
