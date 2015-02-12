/*
 * PROJECT II: Complex.java
 *
 * This file contains a template for the class Complex. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class implements a new type for complex numbers and corresponding
 * arithmetic operations. You should already have done something very similar
 * in week 14 (see CmplxNum in the online lab notes), so you should be able to
 * simply copy and paste from that file into this one.
 *
 * At the bottom of this file, I have included a simple main function which
 * tests the basic functionality. This is the same code that was released in
 * the week 14 solutions.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Complex {
    /**
     * Real part x of the complex number x+iy.
     */
    private double x;
    
    /**
     * Imaginary part y of the complex number x+iy.
     */
    private double y;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Constructor: Initializes x, y.
     *
     * @param x  The initial value of the real component.
     * @param y  The initial value of the imaginary component.
     */
    public Complex(double x, double y) {
        //assigns the x and y values inputed into Complex to the private variables of complex class
		this.x=x;
		this.y=y;
    }

    /**
     * Real constructor - initialises with a real number.
     *
     * @param x  The initial real number to initialise to.
     */
    public Complex(double x) {
		//assigns x value inputed into Complex and 0 to private x and y respectivly
        this.x=x;
		this.y=0;
    }

    /**
     * Default constructor; initialiase x and y to zero.
     */
    public Complex() {
		//assigns both private x and y to zero
        this.x=0;
		this.y=0;
    }
    
    // ========================================================
    // Accessor and mutator methods.
    // ========================================================
    
    /**
     * Accessor Method: get real part of the complex number.
     * 
     * @return The real part of the complex number.
     */
    public double getReal() {
		//returns x(real part)
        return this.x;
    }

    /**
     * Accessor Method: get imaginary part of the complex number.
     *
     * @return The imaginary part of the complex number
     */
    public double getImag() {
		//returns y(imaginary part)
        return this.y;
    }

    /**
     * Mutator method: set the real part of the complex number.
     *
     * @param x  The replacement real part of z.
     */
    public void setReal(double x) {
		//sets private x to value of inputed x
        this.x=x;
    }
    
    /**
     * Mutator method: set the imaginary part of the complex number.
     *
     * @param y  The replacement imaginary part of z.
     */
    public void setImag(double y) {
		//sets private y to value of inputed y
        this.y=y;
    }
    
    // ========================================================
    // Operations and functions with complex numbers.
    // ========================================================

    /**
     * Converts the complex number to a string. This is an important method as
     * it allows us to print complex numbers using System.out.println.
     *
     * @return A string describing the complex number.
     */
    public String toString() {
        // This function is complete.
        if (y < 0.0)
            return x + "-" + Math.abs(y) + "i";
        else
            return x + "+" + Math.abs(y) + "i";
    }

    /**
     * Computes square of the absolute value (magnitude) of the complex number
     * (i.e. |z|^2).
     *
     * @return The square of the absolute value of this complex number.
     */
    public double abs2() {
		//uses formula x^2 + y^2 and returns value
        return Math.pow(this.x,2)+Math.pow(this.y,2);
    }

    /**
     * Computes absolute value (magnitude) of the complex number.
     *
     * @return The absolute value of the complex number.
     */
    public double abs() {
		//uses formula absolute of complex = sqrt(x^2 + y^2) using abs2() to get x^2 + y^2 and returns calculated value
        return Math.sqrt(abs2());
    }
    
    /**
     * Calculates the conjugate of this complex number.
     *
     * @return A Complex contaning the conjugate.
     */
    public Complex conjugate() {
		//Conjugate of a+bi is a-bi so creates a new Complex with values a,-b and returns it
		Complex c = new Complex(this.x,-(this.y));
        return c;
    }

    /**
     * Adds a complex number to this one.
     *
     * @param b  The complex number to add to this one.
     * @return   The sum of this complex number with b.
     */
    public Complex add(Complex b) {
        double tempx=this.x+b.getReal(); //Adds the real parts of Current Complex (x) and inputed Complex b
		double tempy=this.y+b.getImag(); //Adds the imaginary parts of Current Complex (y) and inputed Complex b
		//Creates new complex using the above calculations for real and imaginary parts of sumed complex number, and returns it
		Complex c = new Complex(tempx,tempy); 
		return c;
    }
    
    /**
     * Calculates -z.
     *
     * @return The complex number -z = -x-iy
     */
    public Complex minus() {
        double tempx=-this.x; //Caculates -x and assigns it to tempx
		double tempy=-this.y; //Caculates -y and assigns it to tempy
		//Creates new Complex with variables tempx,tempy and returns it
		Complex c = new Complex(tempx,tempy);
		return c;
    }

    /**
     * Multiplies this complex number by a constant.
     *
     * @param alpha   The constant to multiply by.
     * @return        The product of alpha with z.
     */
    public Complex multiply(double alpha) {
        double tempx=this.x*alpha;//Caculates real part of complex times by alpha
		double tempy=this.y*alpha;//Caculates imaginary part of complex times by alpha
		//Creates new Complex with above variables and returns it
		Complex c = new Complex(tempx,tempy);
		return c;
    }
    
    /**
     * Multiplies this complex number by another complex number.
     *
     * @param b   The complex number to multiply by.
     * @return    The product of b with z.
     */
    public Complex multiply(Complex b) {
		//Uses formula: (x1+y1i)(x2+y2i) = (x1x2-y1y2)+(y1x2+x1y2)i 
		//Then creates and returns a complex with this value
        double tempx=(this.x*b.getReal())-(this.y*b.getImag());
		double tempy=(this.y*b.getReal())+(this.x*b.getImag());
		Complex c = new Complex(tempx,tempy);
		return c;
    }

    /**
     * Divide this complex number by another.
     *
     * @param b  The complex number to divide by.
     * @return   The division z/b.
     */
    public Complex divide(Complex b) {
		//Uses formula (a+bi)/(c+di) = (a+bi)(c-di)/(c^2+d^2)
		//Then returns a complex with the calculated values
        Complex bConj = b.conjugate(); //finds conjugate of b
		Complex num = this.multiply(bConj); //Does (a+bi)(c-di) part of calculation
		double denom = Math.pow(b.getReal(),2) + Math.pow(b.getImag(),2); //Does c^2 + d^2 part of calculation
		//Calculates (a+bi)(c-di)/(c^2+d^2) then creates a complex with these values and returns it
		double tempx = num.getReal()/denom;
		double tempy = num.getImag()/denom;
		Complex c = new Complex(tempx,tempy);
		return c;
    }
        
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
        // Test all of the constructor functions. You can add anything you
        // want to this if you think it's necessary!
        Complex A = new Complex(1.0, 1.0);
        Complex B = new Complex(1.0);
        Complex C = new Complex();
        
        // Test the converters by printing out A, B and C.
        System.out.println("Constructor test:");
        System.out.println("A = "+A.toString());
        System.out.println("B = "+B.toString());
        System.out.println("C = "+C.toString());
        
        // Test accessor/mutators.
        System.out.println("\nSetting imag(C) = real(C) + 1:");
        C.setImag(C.getReal() + 1.0);
        System.out.println("C = "+C.toString());
        System.out.println("Setting real(C) = imag(C) + 1:");
        C.setReal(C.getImag() + 1.0);
        System.out.println("C = "+C.toString());		
        
        // Now test operations for both complex and real numbers, just to make
        // sure something isn't wrong.
        System.out.println("\nTesting operators:");
        System.out.println("abs(A)   = "+A.abs());
        System.out.println("abs2(A)  = "+A.abs2());
        System.out.println("abs(B)   = "+B.abs());
        System.out.println("abs2(B)  = "+B.abs2());
        System.out.println("conj(A)  = "+A.conjugate());
        System.out.println("conj(B)  = "+B.conjugate());
        System.out.println("neg(A)   = "+A.minus());
        System.out.println("neg(B)   = "+B.minus());
        System.out.println("A+B      = "+A.add(B));
        System.out.println("A+C      = "+A.add(C));
        System.out.println("2*A      = "+A.multiply(2.0));
        System.out.println("A*C      = "+A.multiply(C));
        System.out.println("B*C      = "+B.multiply(C));
        System.out.println("A*A      = "+A.multiply(A));
        System.out.println("A/B      = "+A.divide(B));
        System.out.println("A/C      = "+A.divide(C));
        System.out.println("A/A      = "+A.divide(A));

        // And to finish off, a couple examples of how you can chain the
        // different operations together.
        System.out.println("\nChained operators:");
        System.out.println("B*(A+C)    = "+B.multiply(A.add(C)));
        System.out.println("-A*(B+C)   = "+A.minus().multiply(B.add(C)));
    }
}