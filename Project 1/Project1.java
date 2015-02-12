/*
 * PROJECT I: Project1.java
 *
 * As in project 0, this file - and the others you downloaded - form a
 * template which should be modified to be fully functional.
 *
 * This file is the *last* file you should implement, as it depends on both
 * Point and Circle. Thus your tasks are to:
 *
 * 1) Make sure you have carefully read the project formulation. It contains
 *    the descriptions of all of the functions and variables below.
 * 2) Write the class Point.
 * 3) Write the class Circle
 * 4) Write this class, Project1. The Results() method will perform the tasks
 *    laid out in the project formulation.
 */
public class Project1 {
    // -----------------------------------------------------------------------
    // Do not modify the names of the variables below! This is where you will
    // store the results generated in the Results() function.
    // -----------------------------------------------------------------------
    public int    circleCounter; // Number of non-singular circles in the file.
    public int    posFirstLast;  // Indicates whether the first and last
                                 // circles overlap or not.
    public double maxArea;       // Area of the largest circle (by area).
    public double minArea;       // Area of the smallest circle (by area).
    public double averageArea;   // Average area of the circles.
    public double stdArea;       // Standard deviation of area of the circles.
    public double medArea;       // Median of the area.
    public int    stamp=189375;
    // -----------------------------------------------------------------------
    // You may implement - but *not* change the names or parameters of - the
    // functions below.
    // -----------------------------------------------------------------------
	
    /**
     * Default constructor for Project1. You should leave it empty.
     */
    public Project1() {
        // This method is complete.
    }

    /**
     * Results function. It should open the file called FileName (using
     * MaInput), and from it generate the statistics outlined in the project
     * formulation. These are then placed in the instance variables above.
     *
     * @param fileName  The name of the file containing the circle data.
     */
    public void results(String fileName){
		//Reads all the data from the file and counts how many 
		//non-singular circles there is within the data
		circleCounter = 0;
		MaInput F1 = new MaInput(fileName);
		double x, y, rad;
		x=0;
		y=0;
		rad=0;
        while (!F1.atEOF()) {
            x   = F1.readDouble();
            y   = F1.readDouble();
            rad = F1.readDouble();
			if (rad > Point.GEOMTOL){
				circleCounter++; // Counts the number of non-singular circles
			}
        }
		
		//Does another loop through the data
        F1 = new MaInput(fileName);
		int circleNum=1; //Sets Integer circleNum which is equal to the line number the current circle data is from
		Circle firstC = new Circle(0,0,0); //
		Circle lastC = new Circle(0,0,0);  // defines a new Circle ready to be set when cycling through data
		Circle tempC = new Circle(0,0,0);  //
		double[] area = new double[circleCounter]; //create an array to store all the individual areas in
		//Loops until end of file is reached
        while (!F1.atEOF()) {
            x   = F1.readDouble(); //reads first double on line
            y   = F1.readDouble(); //reads second double on line
            rad = F1.readDouble(); //reads third double on line
			
			 
			if (rad > Point.GEOMTOL){ //If radius of circle not equal to zero (within a tolerance), evaluate the data
				
				//Sets data of first circle to firstC
				if (circleNum == 1) {
					firstC.setRadius(rad);
					firstC.setCenter(x,y);
				}
				//Sets data of last circle to lastC
				if (circleNum == circleCounter) {
					lastC.setRadius(rad);
					lastC.setCenter(x,y);
				}
				//Sets data of each circle in data file to a temporary Circle named tempC
				tempC.setRadius(rad);
				tempC.setCenter(x,y);
				//calculate area of each circle and put it in the array named area
				//with its index equal to line number of circle in data file
				area[circleNum-1] = tempC.area();
				circleNum++; //end of loop so we move to next line of file and increase our variable for line number
			}
			
        }
		
		//Calculates whether the first and last circles overlap 
		//using the Circle class function 'overlap' with the two circles firstC and lastC
		posFirstLast = firstC.overlap(lastC);
		
		//Sets max and min area to the area of the first circle
		//and sets averageArea to zero
		maxArea=area[1];
		minArea=area[1];
		averageArea=0;
		
		//loops through each element of the area array
		//Works out max and min areas contained in area array
		for (int i=0 ; i < circleNum-1 ; i++){
			if (area[i] > maxArea) {
				maxArea = area[i]; //If area in array is bigger than maxArea, set maxArea=area
			}
			if (area[i] < minArea) {
				minArea = area[i]; //If area in array is smaller than minArea, set minArea=area
			}
			averageArea=averageArea+area[i]; //Adds up all the areas one by one and assigns them to variable averageArea
			stdArea=stdArea+Math.pow(area[i],2); //Adds up all the areas squared one by one and assigns them to variable stdArea
		}
		
		//Finds average area by using the sum of areas(averageArea) and dividing by number of circles
		//and then assigning the answer back into averageArea
		averageArea=averageArea/circleCounter;
		
		//Finds standard deviation of area by using the sum of areas squared(stdArea) and dividing by number of circles
		//then taking away average of areas squared and finially square rooting the total
		//then it assigns the answer back into stdArea
		stdArea=Math.sqrt((stdArea/circleCounter)-Math.pow(averageArea,2));
		
		//Uses an algorithm to sort the area array into numerical order
		boolean swaped = true;
		while (swaped) {
			swaped = false;
			for (int i = 0; i < circleNum-2 ; i++) {
				if (area[i] > area[i+1]) {
					double temp = area[i+1];
					area[i+1] = area[i];
					area[i] = temp;
					swaped = true;
				}
			}
		}
		
		//Finds the median by taking the middle number in the array if there is an odd number of elements in array
		//or taking the average of the 2 middle numbers in array if there is an even number of elements
		if (circleCounter%2 == 0) {
			//even number of elements 
			medArea = ( area[((circleNum/2))-1] + area[((circleNum/2)+1)-1] )/2;
		} else {
			//odd number of elements
			medArea = area[(circleNum+1)/2-1];
		}
    }
	  
    // =======================================================
    // Tester - tests methods defined in this class
    // =======================================================

    /**
     * Your tester function should go here (see week 14 lecture notes if
     * you're confused). It is not tested by BOSS, but you will receive extra
     * credit if it is implemented in a sensible fashion.
     */
    public static void main(String args[]){
        Project1 P = new Project1();
		P.results("Project1.data");
		System.out.println("Number of Circles: "+P.circleCounter);
		System.out.println("First and last circle overlap: "+P.posFirstLast);
		System.out.println("Min area: "+P.minArea);
		System.out.println("Max area: "+P.maxArea);
		System.out.println("Average area: "+P.averageArea);
		System.out.println("Standard Deviation of area: "+P.stdArea);
		System.out.println("medium of area: "+P.medArea);
    }
}

