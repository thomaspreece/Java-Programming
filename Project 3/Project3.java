/*
 * PROJECT III: Project3.java
 *
 * This file contains a template for the class Project3. None of methods are
 * implemented. Make sure you have carefully read the project formulation
 * before starting to work on this file. You will also need to have completed
 * the Matrix class, as well as GeneralMatrix and TriMatrix.
 *
 * Remember not to change the names, parameters or return types of any
 * variables in this file!
 *
 * The function of the methods and instance variables are outlined in the
 * comments directly above them.
 */

public class Project3 {
    /**
     * Calculates the variance of the distribution defined by the determinant
     * of a random matrix. See the formulation for a detailed description.
     *
     * @param m           The matrix object that will be filled with random
     *                    samples.
     * @param numSamples  The number of samples to generate when calculating 
     *                    the variance. 
     * @return            The variance of the distribution.
     */
    public static double matVariance(Matrix m, int numSamples) {
		//Sets up doubles for use in loop
		//sumDet will store the sum of all the determinanats 
		//and sumDetSquared will store the sum of the squared values of determinant
		double det;
		double sumDet=0;
		double sumDetSquared=0;
		for (int i=0; i < numSamples; i++){
			//Sets the elements of m to a random number between 0 and 1
			m.random();
			//Finds the determinant of the random matrix
			det=m.determinant();
			//adds the determinant to sumDet and adds the square of determinant to sumDetSquared
			sumDet=sumDet+det;
			sumDetSquared=sumDetSquared+Math.pow(det,2);
		}
		//Finds and returns varience using formula in project formulation
		return (sumDetSquared/numSamples-Math.pow((sumDet/numSamples),2));
    }
    
    /**
     * This function should calculate the variances of matrices for matrices
     * of size 2 <= n <= 50. See the formulation for more detail.
     */
    public static void main(String[] args) {
		//Set up a new GeneralMatrix and a new TriMatrix for use with matVariance function
		GeneralMatrix gen;
		TriMatrix tri;
		//Loops through 2 <= n <= 50 
		//On each run through it prints out the value of n, variance of 15,000 random nxn GeneralMatrix's, 
		//and variance of 150,000 random nxn TriMatrix's
		for (int n=2; n <51; n++){
			gen = new GeneralMatrix(n,n);
			tri = new TriMatrix(n);
			System.out.println(n+" "+matVariance(gen,15000)+" "+matVariance(tri,150000));
		}
    }
}