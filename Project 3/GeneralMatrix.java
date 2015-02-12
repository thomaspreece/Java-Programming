/*
 * PROJECT III: GeneralMatrix.java
 *
 * This file contains a template for the class GeneralMatrix. Not all methods
 * are implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

import java.util.Arrays;

public class GeneralMatrix extends Matrix {
    /**
     * This instance variable stores the elements of the matrix.
     */
    private double[][] data;

    /**
     * Constructor function: should initialise m and n through the Matrix
     * constructor and set up the data array.
     *
     * @param m  The first dimension of the array.
     * @param n  The second dimension of the array.
     */
    public GeneralMatrix(int m, int n) throws MatrixException {
		super(m,n); //Uses super command to initialise m and n through Matrix constructor
		//Checks to make sure that the dimensions of matrix arnt 0 or less, if true throw exception
		if (m<1 || n<1){
			throw new MatrixException("Matrix cannot have dimension of 0 or less");
		}
		data = new double[m][n]; //Initialises double[][] data as a double array of length m x n
    }

    /**
     * Constructor function. This is a copy constructor; it should create a
     * copy of the matrix A.
     *
     * @param A  The matrix to create a copy of.
     */
    public GeneralMatrix(GeneralMatrix A) {
		//Uses super command to initialise m and n through Matrix constructor
		super(A.m,A.n);
		//Initialises double[][] data as a double array of length m x n
		data = new double[A.m][A.n];
		//Loops through each element of Matrix A and sets it to corresponding element of this Matrix
		for (int i=0; i < A.m; i++){
			for (int j=0; j < A.n; j++){
				data[i][j] = A.getIJ(i,j);
			}
		}
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
			//returns the (i,j) element of matrix
			return data[i][j];
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
			//sets (i,j) element of matrix to val
	        data[i][j] = val;
		}else{
			throw new MatrixException("cannot set element as its not in the matrix");
		}
    }
    
    /**
     * Return the determinant of this matrix.
     *
     * @return The determinant of the matrix.
     */
    public double determinant() {
		//sets up array of length 1 d and double det
		double[] d = new double[1];
		double det = 1;
		//Runs decomp() function on this matrix and passes array d which will contain either -1 or 1 when finished
		GeneralMatrix LUCombined = this.decomp(d);
		//det(A)=det(LU)=det(L)*det(U)
		//As det(L) is 1 because all the diagonal elements are all one and its in lower triangular form
		//Therefore det(A)=det(U) which is equal to the diagonal elements of U as its in upper triangular form
		//Therefore as the matrix returned by decomp has the diagonal elements of U on its diagonal
		//determinant of this matrix is equal to the product of the diagonal elements of matrix LUCombined
		//This loop gets the diagonal elements of LUCombined and times them together and stores it in double det
		for (int i=0; i < this.n;i++){
			det = det*LUCombined.getIJ(i,i);
		}
		//The way the decomp() algorithm works the determinant must be times by the returned value of d
		det = det*d[0];
		//return the determinant
		return det;
    }

    /**
     * Add the matrix to another matrix A.
     *
     * @param A  The Matrix to add to this matrix.
     * @return   The sum of this matrix with the matrix A.
     */
    public Matrix add(Matrix A) {
		//Checks to make sure matrix dimensions are equal, else it throws an exception
        if (this.n == A.n && this.m == A.m){
			//Sets up a new GeneralMatrix that will eventually be returned to caller function
			GeneralMatrix returnMatrix = new GeneralMatrix(this.m,this.n);
			//Loops through each element of the matrices and adds the corresponding values together
			//and sets the result to the corresponding element in returnmatrix 
			for (int i=0; i < this.m; i++){
				for (int j=0; j < this.n; j++){
					returnMatrix.setIJ(i,j,(A.getIJ(i,j)+this.getIJ(i,j)));
				}
			}
			//returns the returnMatrix which contains the sum of this and A
			return returnMatrix;
		}else{
			throw new MatrixException("Cannot add, as dimensions not equal");
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
		//Loops through each element of this and multiplys it by a and
		//then sets it to the corresponding element in returnMatrix
        GeneralMatrix returnMatrix = new GeneralMatrix(this.m,this.n); 
		for (int i=0; i < this.m; i++){
			for (int j=0; j < this.n; j++){
				returnMatrix.setIJ(i,j,getIJ(i,j)*a);
			}
		}
		//returns the returnMatrix which contains the product of this with scalar a
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
		//Loops through each element of this matrix and sets that element a random value between 0 and 1
		for (int i=0; i < this.m; i++){
			for (int j=0; j < this.n; j++){
				this.setIJ(i,j,Math.random());
			}
		}
    }

    /**
     * Returns the LU decomposition of this matrix; i.e. two matrices L and U
     * so that A = LU, where L is lower-diagonal and U is upper-diagonal.
     * 
     * On exit, decomp returns the two matrices in a single matrix by packing
     * both matrices as follows:
     *
     * [ u_11 u_12 u_13 u_14 ]
     * [ l_21 u_22 u_23 u_24 ]
     * [ l_31 l_32 u_33 u_34 ]
     * [ l_41 l_42 l_43 l_44 ]
     *
     * where u_ij are the elements of U and l_ij are the elements of l. When
     * calculating the determinant you will need to multiply by the value of
     * d[0] calculated by the function.
     * 
     * If the matrix is singular, then the routine throws a MatrixException.
     *
     * This method is an adaptation of the one found in the book "Numerical
     * Recipies in C" (see online for more details).
     * 
     * @param d  An array of length 1. On exit, the value contained in here
     *           will either be 1 or -1, which you can use to calculate the
     *           correct sign on the determinant.
     * @return   The LU decomposition of the matrix.
     */
    public GeneralMatrix decomp(double[] d) {
        // This method is complete. You should not even attempt to change it!!
        if (n != m)
            throw new MatrixException("Matrix is not square");
        if (d.length != 1)
            throw new MatrixException("d should be of length 1");
        
        int           i, imax = -10, j, k; 
        double        big, dum, sum, temp;
        double[]      vv   = new double[n];
        GeneralMatrix a    = new GeneralMatrix(this);
        
        d[0] = 1.0;
        
        for (i = 1; i <= n; i++) {
            big = 0.0;
            for (j = 1; j <= n; j++)
                if ((temp = Math.abs(a.data[i-1][j-1])) > big)
                    big = temp;
            if (big == 0.0)
                throw new MatrixException("Matrix is singular");
            vv[i-1] = 1.0/big;
        }
        
        for (j = 1; j <= n; j++) {
            for (i = 1; i < j; i++) {
                sum = a.data[i-1][j-1];
                for (k = 1; k < i; k++)
                    sum -= a.data[i-1][k-1]*a.data[k-1][j-1];
                a.data[i-1][j-1] = sum;
            }
            big = 0.0;
            for (i = j; i <= n; i++) {
                sum = a.data[i-1][j-1];
                for (k = 1; k < j; k++)
                    sum -= a.data[i-1][k-1]*a.data[k-1][j-1];
                a.data[i-1][j-1] = sum;
                if ((dum = vv[i-1]*Math.abs(sum)) >= big) {
                    big  = dum;
                    imax = i;
                }
            }
            if (j != imax) {
                for (k = 1; k <= n; k++) {
                    dum = a.data[imax-1][k-1];
                    a.data[imax-1][k-1] = a.data[j-1][k-1];
                    a.data[j-1][k-1] = dum;
                }
                d[0] = -d[0];
                vv[imax-1] = vv[j-1];
            }
            if (a.data[j-1][j-1] == 0.0)
                a.data[j-1][j-1] = 1.0e-20;
            if (j != n) {
                dum = 1.0/a.data[j-1][j-1];
                for (i = j+1; i <= n; i++)
                    a.data[i-1][j-1] *= dum;
            }
        }
        
        return a;
    }

    public static void main(String[] args) {
		//Tests Constructor Functions
		//Sets up new GeneralMatrix's of dimension 2x2
        Matrix a = new GeneralMatrix(2,2);
		a.setIJ(0,0,1);
		a.setIJ(0,1,2);
		a.setIJ(1,0,3);
		a.setIJ(1,1,1);
		Matrix b = new GeneralMatrix(2,2);
		b.setIJ(0,0,3);
		b.setIJ(0,1,0);
		b.setIJ(1,0,-2);
		b.setIJ(1,1,9);
		Matrix aCopy = new GeneralMatrix((GeneralMatrix)a);
		//Tests the getIJ and setIJ functions
		System.out.println("Get 0,0 value of matrix A: "+a.getIJ(0,0)+"\n");
		//Tests the toString() function
		System.out.println("Matrix A: \n"+a.toString());
		System.out.println("Matrix B: \n"+b.toString());
		System.out.println("Matrix ACopy: \n"+aCopy.toString());
		//Tests the determinant() function
		System.out.println("Determinant of A is: "+a.determinant()+"\n");
		System.out.println("Determinant of B is: "+b.determinant()+"\n");
		//Tests the multiply() function, for both scalar and matrix versions
		Matrix e = a.multiply(b);
		System.out.println("Matrix A*B: \n"+e.toString());
		Matrix d = a.multiply(2);
		System.out.println("Matrix A*2: \n"+d.toString());
		//Tests the add() function
		Matrix c = a.add(b);
		System.out.println("Matrix A+B: \n"+c.toString());
		//Test the random() function
		Matrix f = new GeneralMatrix(2,2);
		f.random();
		System.out.println("Random Matrix: \n"+f.toString());
		//Tests the exceptions
		System.out.println("Trying to premultiply 3x3 matrix with 4x10 matrix: ");
		try{
			Matrix a2 = new GeneralMatrix(4,10);
			a2.random();
			Matrix b2 = new GeneralMatrix(3,3);
			b2.random();
			Matrix c2 = b2.multiply(a2);
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}
		
		System.out.println("\nTrying to add 10x10 matrix to 3x3 matrix: ");
		try{
			Matrix a2 = new GeneralMatrix(10,10);
			a2.random();
			Matrix b2 = new GeneralMatrix(3,3);
			b2.random();
			Matrix c2 = b2.add(a2);
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}		
		
		System.out.println("\nTrying to find determinant of 5x3 matrix: ");
		try{
			Matrix a2 = new GeneralMatrix(5,3);
			a2.determinant();
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}	
		
		System.out.println("\nTrying to find determinant of singular 2x2 matrix: ");
		try{
			Matrix a2 = new GeneralMatrix(2,2);
			a.setIJ(0,0,1);
			a.setIJ(0,1,1);
			a.setIJ(1,0,0);
			a.setIJ(1,1,0);		
			a2.determinant();
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}	
		
		System.out.println("\nTrying to Create Matrix with one dimension zero ");
		try{
			Matrix a3 = new GeneralMatrix(0,9);	
		}catch (MatrixException except){
			System.out.println(except.getMessage());
		}			
	}
}