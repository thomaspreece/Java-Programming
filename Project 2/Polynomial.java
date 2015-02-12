/*
 * PROJECT II: Polynomial.java
 *
 * This file contains a template for the class Polynomial. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file.
 *
 * This class is designed to use Complex in order to represent polynomials
 * with complex co-efficients. It provides very basic functionality and there
 * are very few methods to implement! The project formulation contains a
 * complete description.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file! You should also test this class using the main()
 * function.
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

class Polynomial {
    /**
     * An array storing the complex co-efficients of the polynomial.
     */
    Complex[] coeff;

    // ========================================================
    // Constructor functions.
    // ========================================================

    /**
     * General constructor: assigns this polynomial a given set of
     * co-efficients.
     *
     * @param coeff  The co-efficients to use for this polynomial.
     */
    public Polynomial(Complex[] coeff) {
		int degree=coeff.length;//Finds size of inputted array for use in setting size of this.coeff
		//Loops backwards through coeff
		//If it finds a zero it minuses one off degree, if it finds non-zero term it exits loop
		for (int i = coeff.length-1; i > -1; i--){
			if (coeff[i].getReal() == 0 && coeff[i].getImag() == 0){
				degree--;
			} else {
				break;
			}
		}
		//Sets this.coeff to the size of degree(The size of coeff without the last terms with value zero)
		//and then copys all the terms from coeff to this.coeff except the last terms with value zero 
		//eg. coeff = 1,2,0,1,3,0,0 would create this.coeff = 1,2,0,1,3
		this.coeff = new Complex[degree];
        for (int i = 0; i < degree; i++){
			this.coeff[i]=coeff[i];
		}
    }
    
    /**
     * Default constructor: sets the Polynomial to the zero polynomial.
     */
    public Polynomial() {
		//Sets up this.coeff so polynomial is zero polynomial 
        this.coeff = new Complex[] {new Complex()};
    }

    // ========================================================
    // Operations and functions with polynomials.
    // ========================================================

    /**
     * Create a string representation of the polynomial.
     *
     * For example: (1.0+1.0i)+(1.0+2.0i)X+(1.0+3.0i)X^2
     */
    public String toString() {
		//Sets up tempString and sets it equal to a string representation of the first coeff
		String tempString = "("+this.coeff[0].getReal()+"+"+this.coeff[0].getImag()+"i)";
		//Loops through the remaining coefficients of coeff, adding a string representation of each to tempString
		//And then returns tempString
		for (int i = 1; i < this.coeff.length; i++){
			tempString=tempString+"+("+this.coeff[i].getReal()+"+"+this.coeff[i].getImag()+"i)";
			//If its a coefficent of x then just add x otherwise add x^i
			switch (i){ 
				case 1: tempString=tempString+"X"; break; 
				default: tempString=tempString+"X^"+i; break;
			}
		}
		return tempString;
    }

    /**
     * Returns the degree of this polynomial.
     */
    public int degree() {
		//returns the size of coeff -1 (degree)
        return this.coeff.length-1;
    }

    /**
     * Evaluates the polynomial at a given point z.
     *
     * @param z  The point at which to evaluate the polynomial
     * @return   The complex number P(z).
     */
    public Complex evaluate(Complex z) {
		//Uses this algorithm - a+bx+cx^2+dx^3 = a + x( b + x( c + dx)) to calculate polynomial at given value
		Complex c = new Complex(this.coeff[this.coeff.length-1].getReal(),this.coeff[this.coeff.length-1].getImag()); //sets the last coeff to a complex c
		Complex b; //sets b to a Complex type ready to use in loop
		//Loops backwards through coefficients and applys algorithm:
		//(Times coefficient by value z and then adds next coefficient)
		//Then sets resulting calculation to c so algorithm can be reaplyed to new c on next loop
        for (int i = this.coeff.length-2; i > -1; i-- ){
			b = c.multiply(z);
			b = b.add(this.coeff[i]);
			c.setReal(b.getReal());
			c.setImag(b.getImag());
		}
		//returns Complex value of polynomial at z 
		return c;
    }
    
    /**
     * Calculate and returns the derivative of this polynomial.
     *
     * @return The derivative of this polynomial.
     */
    public Polynomial derivative() {
		//Finds derivative by removing first coefficient and then 
		//shifting all the coefficients along 1 to left whilst multiplying coefficient by its old position
        Complex[] tempcoeff = new Complex[this.coeff.length-1]; //creates a temperary array to do manipulations on with size of 1 less than coeff
		//Loops through all coeff except first value assigning the value*position to the position of the value in coeff -1
		for (int i = 1; i < this.coeff.length; i++){ 
			tempcoeff[i-1]=this.coeff[i].multiply(i);
		}
		//Creates a polynomial with our temperary array and returns it
		Polynomial p = new Polynomial(tempcoeff);
		return p;
    }
    
    // ========================================================
    // Tester function.
    // ========================================================

    public static void main(String[] args) {
		//Creates a polynomial with coefficients set in coeff, Tests constructor function
		Complex[] coeff = new Complex[] {new Complex(1.0,0.0), new Complex(0.0,1.0), new Complex(), new Complex(1,1), new Complex(-1,1), new Complex(0,0), new Complex(0,0)};
		Polynomial p = new Polynomial(coeff);
		//Tests toString and degree functions
		System.out.println("Inputed polynomial: "+p.toString());
		System.out.println("Degree: "+p.degree());
		//Tests evaluate functions
		Complex c = p.evaluate(new Complex(-1));
		System.out.println(" ");
		System.out.println("Polynomial Evaluated at -1: "+c.toString());	
		//Tests derivative function
		Polynomial pdash = p.derivative();
		System.out.println(" ");
		System.out.println("Derivative: "+pdash.toString());
		System.out.println("Degree: "+pdash.degree());
		//Tests the constructor with no variables
		Polynomial pzero = new Polynomial();
		System.out.println(" ");
		System.out.println("Zero Polynomial: "+pzero.toString());
		System.out.println("Degree: "+pzero.degree());		
	}
}