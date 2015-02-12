/*
 * PROJECT II: Newton.java
 *
 * This file contains a template for the class Newton. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * In this class, you will create a basic Java object responsible for
 * performing the Newton-Raphson root finding method on a given polynomial
 * f(z) with complex co-efficients. The formulation outlines the method, as
 * well as the basic class structure, details of the instance variables and
 * how the class should operate.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Newton {
    /**
     * The maximum number of iterations that should be used when applying
     * Newton-Raphson. Ensure this is *small* (e.g. at most 50) otherwise your
     * program may appear to freeze!
     */
    public static final int MAXITER = 20;

    /**
     * The tolerance that should be used throughout this project. Note that
     * you should reference this variable and _not_ explicity write out
     * 1.0e-10 in your solution code. Other classes can access this tolerance
     * by using Newton.TOL.
     */
    public static final double TOL = 1.0e-10;

    /**
     * The polynomial we wish to apply the Newton-Raphson method to.
     */
    private Polynomial f;
    
    /**
     * The derivative of the the polynomial f.
     */
    private Polynomial fp;

    /**
     * A root of the polynomial f corresponding to the root found by the
     * iterate() function below.
     */
    private Complex root;
    
    /**
     * The number of iterations required to reach within TOL of the root.
     */
    private int numIterations;

    /**
     * An integer that signifies errors that may occur in the root finding
     * process.
     *
     * Possible values are:
     *   =  0: Nothing went wrong.
     *   = -1: Derivative went to zero during the algorithm.
     *   = -2: Reached MAXITER iterations.
     */
    private int err;
    
    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * Basic constructor. You should calculate and set fp in this method.
     *
     * @param p  The polynomial used for Newton-Raphson.
     */
    public Newton(Polynomial p) {
		f = new Polynomial(p.coeff); //Copies the Complex coefficients from Polynomial p into a new Polynomial f
        fp = p.derivative(); //Finds derivative of p and assigns it to fp
    }

    // ========================================================
    // Accessor methods.
    // ========================================================
    
    /**
     * Returns the current value of the err instance variable.
     */
    public int getError() {
        return err;
    }

    /**
     * Returns the current value of the numIterations instance variable.
     */
    public int getNumIterations() { 
       return numIterations;
    }
    
    /**
     * Returns the current value of the root instance variable.
     */
    public Complex getRoot() {
        return root;
    }

    /**
     * Returns the polynomial associated with this object.
     */
    public Polynomial getF() {
       return f;
    }

    /**
     * Returns the derivative of the polynomial associated with this object.
     */
    public Polynomial getFp() {
        return fp;
    }

    // ========================================================
    // Newton-Rapshon method
    // ========================================================
    
    /**
     * Given a complex number z0, apply Newton-Raphson to the polynomial f in
     * order to find a root within tolerance TOL.
     *
     * One of three things may occur:
     *
     *   - The root is found, in which case, set root to the end result of the
     *     algorithm, numIterations to the number of iterations required to
     *     reach it and err to 0.
     *   - At some point the derivative of f becomes zero. In this case, set err 
     *     to -1 and return.
     *   - After MAXITER iterations the algorithm has not converged. In this 
     *     case set err to -2 and return.
     *
     * @param z0  The initial starting point for the algorithm.
     */
    public void iterate(Complex z0) {
		numIterations = 0;//Sets number of iterations to zero
        Complex PreviousZ = new Complex(); //Sets up a Complex to store previous calculated value
		root = new Complex(); //initialises the private Complex root
		//Sets type of a few Complex variables for use in the loop
		Complex num; 
		Complex denom; 
		Complex fraction;
		Complex difference;
		//Uses formula : x(n+1) = x(n) - f(x(n)) / f'(x(n)) until root is calculated within tolerance 
		//or till an error is flagged
		for (int i=0; i < MAXITER; i++){
			//Sets the old root guess to PreviousZ
			PreviousZ.setReal(z0.getReal());
			PreviousZ.setImag(z0.getImag());
			
			//calculates the two parts of fraction f(x(n)) / f'(x(n)) and assigns them to two complex variables
			num = f.evaluate(z0);
			denom = fp.evaluate(z0);
			//Checks to make sure the denominator of fraction isnt zero and returns error -1 if true
			if (denom.getReal()==0 && denom.getImag()==0){
				err = -1;
				numIterations=numIterations+1;
				return;
			}
			//Finds f(x(n)) / f'(x(n)) and assigns it to Complex fraction
			fraction = num.divide(denom);
			//Calculates x(n+1) = x(n) - f(x(n)) / f'(x(n)) and assigns it back to z0
			z0 = z0.add(fraction.minus());
			numIterations=numIterations+1;
			//Calculates difference between this guess of root and the previous guess of root
			
			difference = z0.add(PreviousZ.minus());
			//Checks to see if absolute of difference is within tolerance
			//If it is, sets the private variable root to the value of the current guessed root value, sets error to 0 and returns
			if (difference.abs() < TOL){
				root.setReal(z0.getReal());
				root.setImag(z0.getImag());
				err = 0;
				return;
			}
		}
		//Maximum iterations is reached and an error of -2 is set and function returns
		err = -2;
		return;
    }
    
    // ========================================================
    // Tester function.
    // ========================================================
    
    public static void main(String[] args) {
        // Basic tester: find a root of f(z) = z^3-1 from the starting point
        // z_0 = 1+i.
		Complex[] coeff = new Complex[] { new Complex(-1.0,0.0), new Complex(), 
                                          new Complex(), new Complex(1.0,0.0) };
        Polynomial p    = new Polynomial(coeff);
        Newton     n    = new Newton(p);
        
		//Finds the 3 roots, printing off the root, iteration# and error for each
        n.iterate(new Complex(1.0, 1.0));
		System.out.println("Finding root of z^3-1 from starting point 1+i");
        System.out.println("Root: "+n.getRoot());
		System.out.println("Iterations: "+n.getNumIterations());
		System.out.println("Error: "+n.getError());
		
		n.iterate(new Complex(1.0, 10.0));
		System.out.println("Finding root of z^3-1 from starting point 1+10i");
        System.out.println("Root: "+n.getRoot());
		System.out.println("Iterations: "+n.getNumIterations());
		System.out.println("Error: "+n.getError());
		
		n.iterate(new Complex(1.0, -10.0));
		System.out.println("Finding root of z^3-1 from starting point 1-10i");
        System.out.println("Root: "+n.getRoot());
		System.out.println("Iterations: "+n.getNumIterations());
		System.out.println("Error: "+n.getError());
    }
}