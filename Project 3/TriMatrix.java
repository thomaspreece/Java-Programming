/*
 * PROJECT III: TriMatrix.java
 *
 * This file contains a template for the class TriMatrix. Not all methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class TriMatrix extends Matrix {
    /**
     * An array holding the diagonal elements of the matrix.
     */
    private double[] diag;

    /**
     * An array holding the upper-diagonal elements of the matrix.
     */
    private double[] upper;

    /**
     * An array holding the lower-diagonal elements of the matrix.
     */
    private double[] lower;
    
    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param N  The dimension of the array.
     */
    public TriMatrix(int N) {
		//Uses super command to initialise m and n through Matrix constructor
        super(N,N);
		//Checks to make sure that the dimensions of matrix arnt 0 or less, if true throw exception
		if (N<1){
			throw new MatrixException("Matrix cannot have dimension of 0 or less");
		}
		//Sets up the 3 arrays for storing the lower and upper diagonal and the diagonal
		diag = new double[N];
		upper = new double[N-1];
		lower = new double[N-1];
    }
    
    /**
     * Getter function: return the (i,j)'th entry of the matrix.
     *
     * @param i  The location in the first co-ordinate.
     * @param j  The location in the second co-ordinate.
     * @return   The (i,j)'th entry of the matrix.
     */
    public double getIJ(int i, int j) {
		//Check to see if element i,j is in matrix else throw exception
		if (i>=0 && i<m && j>=0 && j<n){
			//Checks to see if required element is on diagonal
			//and if so fetches the value from the diag array
			if (i == j){
				return diag[i];
			} 
			//Checks to see if required element is on upper diagonal
			//and if so fetches the value from the upper array
			else if (j == i+1){
				return upper[i];
			}
			//Checks to see if required element is on lower diagonal 
			//and if so fetches the value from the lower array
			else if (i == j+1){
				return lower[j];
			}
			//If its not on upper,lower or centre diagonal return zero
			else
			{
				return 0;
			}
		}else{
			throw new MatrixException("cannot return element as its not in the matrix");
		}
    }
    
    /**
     * Setter function: set the (i,j)'th entry of the data array.
     *
     * @param i    The location in the first co-ordinate.
     * @param j    The location in the second co-ordinate.
     * @param val  The value to set the (i,j)'th entry to.
     */
    public void setIJ(int i, int j, double val) {
		//Check to see if element i,j is in matrix else throw exception
		if (i>=0 && i<m && j>=0 && j<n){	
			//Checks to see if required element is on diagonal
			//and if so sets the value in the diag array
			if (i == j){
				diag[i] = val;
			//Checks to see if required element is on upper diagonal
			//and if so sets the value in the upper array			
			} else if (j == i+1){
				upper[i] = val;
			//Checks to see if required element is on lower diagonal
			//and if so sets the value in the lower array
			} else if (i == j+1){
				lower[j] = val;
			//If the required element isnt on the upper,lower or central diagonal throw error
			} else {
				throw new MatrixException("Cannot set non tridiagonal element.");
			}      
		}else
		{
			throw new MatrixException("cannot set element as its not in the matrix");	
		}
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
		//sets up a double det that will be used to calculate the determinant
		double det = 1;
		//Sets the matrix LU to the result of the decomp() function
		TriMatrix LU = this.decomp();
		//Using a similar method as was used in GeneralMatrix
		//Determinant is the product of all the diagonl terms in the LU matrix
		for (int i=0; i < this.n; i++){
			det = det*LU.getIJ(i,i);
		}
		//Returns the determinant
		return det;
    }
    
    /**
     * Returns the LU decomposition of this matrix. See the formulation for a
     * more detailed description.
     * 
     * @return The LU decomposition of this matrix.
     */
    public TriMatrix decomp() {
		/*
		Example of how this algorithm works for a 4x4 trimatrix
		|1 	0	0	0|x|d1	u1	0	0 | = |d1	u1		0		0 		| = |a0	a1	0	0 |
		|L1	1	0	0| |0	d2	u2	0 |   |L1d1	L1u1+d2	u2		0		|   |a2	a3	a4	0 |
		|0	L2	1	0| |0	0	d3	u3|   |0	L2d2	L2u2+d3	u3		|   |0	a5	a6	a7|
		|0	0	L3	1| |0	0	0	d4|   |0	0		L3d3	L3u3+d4 |   |0	0	a8	a9| 
		     (L)             (U)                     (L*U)                   (this Matrix)
			 
		This function basically works line by line and compares L*U to this Matrix 
		and calculates the L, d and u elements one by one and then saves the values in TriMatrix LU
 		*/
		
		//Sets up a new TriMatrix that will eventually be returned
        TriMatrix LU = new TriMatrix(this.n);
		//First diagonal element is the same as the first diagonal element of this matrix
		LU.setIJ(0,0,this.getIJ(0,0));
		//All the upper diagonal elements are the same as diagonal elements in this matrix
		//so loops through all diagonal elements and copies them to new matrix
		for (int u=0; u < this.n-1; u++){
			LU.setIJ(u,u+1,this.getIJ(u,u+1));
		}
		//Loops through remaining elements and calculates value of element using the previous elements
		for (int d=0; d < this.n-1; d++){
			//Works out lower diagonal elements of LU by using a(i)=L(n)*d(n) => L(n) = a(i)/d(n)
			LU.setIJ(d+1,d,this.getIJ(d+1,d)/LU.getIJ(d,d)); 
			//Works out diagonal elements of LU by using a(j)=L(n)*u(n)+d(n+1) => d(n+1)=a(j)-L(n)*u(n)
			LU.setIJ(d+1,d+1,this.getIJ(d+1,d+1)-(LU.getIJ(d+1,d)*LU.getIJ(d,d+1)));
		}
		//Returns the completed LU Matrix
		return LU;
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A){
		if (A instanceof GeneralMatrix){
			return A.add(this);
		}else if (A instanceof TriMatrix){
			//sets up a double for use in the loop
			double total=0;
			//Checks to see if matrix dimensions are equal otherwise throws an exception
			if (this.n == A.n){
				//Sets up a new TriMatrix that will eventually be returned
				TriMatrix returnMatrix = new TriMatrix(this.n);
				//Loops through all upper diagonal elements 
				//and adds the corresponding pairs together and stores them in returnMatrix 
				for (int u=0; u < this.n-1; u++){
					total=this.getIJ(u,u+1)+A.getIJ(u,u+1);
					returnMatrix.setIJ(u,u+1,total);
				}
				//Loops through all diagonal elements 
				//and adds the corresponding pairs together and stores them in returnMatrix 			
				for (int d=0; d < this.n; d++){
					total=this.getIJ(d,d)+A.getIJ(d,d);
					returnMatrix.setIJ(d,d,total);
				}
				//Loops through all lower diagonal elements 
				//and adds the corresponding pairs together and stores them in returnMatrix 			
				for (int l=0; l < this.n-1; l++){
					total=this.getIJ(l+1,l)+A.getIJ(l+1,l);
					returnMatrix.setIJ(l+1,l,total);
				}			
				//returns the matrix with the sum of the two matrices
				return returnMatrix;
			}else{
				throw new MatrixException("Cannot add, as dimensions not equal");
			}	
		}else{
			throw new MatrixException("Unknown Matrix Type");
		}
    }
    
    /**
     * Multiply the matrix by another matrix A. This is a _left_ product,
     * i.e. if this matrix is called B then it calculates the product BA.
     *
     * @param A  The Matrix to multiply by.
     * @return   The product of this matrix with the matrix A.
     */
    public Matrix multiply(Matrix A) {
		//Sets up a double for use in the loop
		double rowTotal;
		//Checks to make sure column number of this is equal to row number of A else throws an exception
		if (this.n == A.m){
			//Sets up a new GeneralMatrix of size row number of this matrix by column number of A
			GeneralMatrix returnMatrix = new GeneralMatrix(this.m,A.n); 
			//Loops through all the rows of this matrix
			for (int r=0; r < this.m; r++){
				//Loops through all the columns of A
				for (int c=0; c < A.n; c++){
					//Loops through the elements of row r of this and column c of A multiplying each pair together and adding them all up
					rowTotal=0;
					for (int a=0; a < this.n; a++){
						rowTotal=rowTotal+(this.getIJ(r,a)*A.getIJ(a,c));
					}	
					//Sets the total to the corresponding element in returnMatrix
					returnMatrix.setIJ(r,c,rowTotal);
				}
			}
			//Returns the returnMatrix which contains the product of this and A
			return returnMatrix;
		}else{
			throw new MatrixException("Cannot Multiply, as width of first isnt equal to height of second");
		}
    }
    
    /**
     * Multiply the matrix by a scalar.
     *
     * @param a  The scalar to multiply the matrix by.
     * @return   The product of this matrix with the scalar a.
     */
    public Matrix multiply(double a) {
		//Sets up a new TriMatrix of same size as this Matrices
		TriMatrix returnMatrix = new TriMatrix(this.n);
		//Loops through all the upper diagonal elements and multiplys them by a
		//and saves the result to the corresponding position in returnmatrix
		for (int u=0; u < this.n-1; u++){
			returnMatrix.setIJ(u,u+1,this.getIJ(u,u+1)*a);
		}
		//Loops through all the diagonal elements and multiplys them by a
		//and saves the result to the corresponding position in returnmatrix		
		for (int d=0; d < this.n; d++){
			returnMatrix.setIJ(d,d,this.getIJ(d,d)*a);
		}
		//Loops through all the lower diagonal elements and multiplys them by a
		//and saves the result to the corresponding position in returnmatrix
		for (int l=0; l < this.n-1; l++){
			returnMatrix.setIJ(l+1,l,this.getIJ(l+1,l)*a);
		}
		//returns the product of the matrix multiplied by scalar a
		return returnMatrix;
    }

    /**
     * Returns a matrix containing random numbers which are uniformly
     * distributed between 0 and 1.
     *
     * @param n  The first dimension of the matrix.
     * @param m  The second dimension of the matrix.
     */
    public void random() {
		//Loops through the upper, lower and centre diagonal elements of the matrix
		//and sets each element a random value between 0 and 1
		for (int u=0; u < this.n-1; u++){
			this.setIJ(u,u+1,Math.random());
		}
		for (int d=0; d < this.n; d++){
			this.setIJ(d,d,Math.random());
		}
		for (int l=0; l < this.n-1; l++){
			this.setIJ(l+1,l,Math.random());
		}        
    }
    
    /*
     * Your tester function should go here.
     */
    public static void main(String[] args) {
		//Tests Constructor and setIJ() functions
        Matrix a = new TriMatrix(3);
		a.setIJ(0,0,1);
		a.setIJ(0,1,2);
		a.setIJ(1,0,3);
		a.setIJ(1,1,1);
		a.setIJ(1,2,1);
		a.setIJ(2,1,2);
		a.setIJ(2,2,3);
        Matrix b = new TriMatrix(3);
		b.setIJ(0,0,4);
		b.setIJ(0,1,1);
		b.setIJ(1,0,2);
		b.setIJ(1,1,2);
		b.setIJ(1,2,2);
		b.setIJ(2,1,4);
		b.setIJ(2,2,1);		
		//Tests the getIJ() function
		System.out.println("(0,0) element of a is: "+a.getIJ(0,0));
		//Tests the toString() function
		System.out.println("TriMatrix a: \n"+a.toString());
		System.out.println("TriMatrix b: \n"+b.toString());	
		//Tests the determinant() and decomp() functions
		System.out.println("Determinant of a is: "+a.determinant()+"\n");
		System.out.println("Determinant of b is: "+b.determinant()+"\n");
		//Tests the add function
		Matrix c = a.add(b);
		System.out.println("a+b: \n"+c.toString());	
		Matrix z = new GeneralMatrix(3,3);
		z.setIJ(0,0,1);
		z.setIJ(0,1,1);
		z.setIJ(1,0,1);
		z.setIJ(2,0,1);
		z.setIJ(0,2,1);
		z.setIJ(1,1,1);
		z.setIJ(1,2,1);
		z.setIJ(2,1,1);
		z.setIJ(2,2,1);	
		System.out.println("GeneralMatrix z: \n"+z.toString());
		Matrix za = a.add(z);
		System.out.println("a+z: \n"+za.toString());
		//Tests the multiply() function with both scalar and matrix variables
		Matrix d = a.multiply(2);
		System.out.println("2*a: \n"+d.toString());	
		Matrix e = a.multiply(b);
		System.out.println("a*b: \n"+e.toString());	
		Matrix h = a.multiply(z);
		System.out.println("a*z: \n"+h.toString());	
		//Tests random() function
		Matrix f = new TriMatrix(4);
		f.random();
		System.out.println("Random TriMatrix: \n"+f.toString());
		//Checks exceptions work correctly
		System.out.println("Trying to premultiply 3x3 trimatrix with 4x4 trimatrix: ");
		try{
			Matrix a2 = new TriMatrix(4);
			a2.random();
			Matrix b2 = new TriMatrix(3);
			b2.random();
			Matrix c2 = b2.multiply(a2);
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}
		System.out.println("\nTrying to add 10x10 trimatrix to 3x3 trimatrix: ");
		try{
			Matrix a2 = new TriMatrix(10);
			a2.random();
			Matrix b2 = new TriMatrix(3);
			b2.random();
			Matrix c2 = b2.add(a2);
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}				
		System.out.println("\nTrying to set a non upper/lower/centre diagonal element: ");
		try{
			Matrix a2 = new TriMatrix(10);
			a2.setIJ(10,0,2.5);
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}
		System.out.println("\nTrying to Create Matrix with one dimension zero ");
		try{
			Matrix a3 = new TriMatrix(0);	
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}			
	}
}