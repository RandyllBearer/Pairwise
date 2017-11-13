import java.util.ArrayList;


/*
*
*/
public class Pairwise{
	static ArrayList<Integer> toDisplay = new ArrayList<Integer>();	//Will hold all the rows we need to display
	static ArrayList<String> parameters = new ArrayList<String>();		//Will hold all the user-passed in parameters
	static int first = 0;
	static int second = 0;
	static int numParameters = 0;		//How many passed in user arguments there are
	static int numRows = 0;			//How many total rows there are: #param^2
	static int[][] table = null;	//Will hold the exhaustive truth table, padded to 10 character length
	
	/*
	*
	*/
	public static void parseInput(String[] arguments){
		if(arguments.length < 2){
			System.out.println("ERROR: Pairwise.java requires at least two parameter arguments to produce a pairwise combination");
			System.exit(1);
		}else{
			numParameters = arguments.length;
			int i = 0;
			while(i < numParameters){
				if(arguments[i].length() > 10){
					arguments[i] = arguments[i].substring(0,11);
				}
				parameters.add(arguments[i]);
				i = i + 1;
			}
		}
	}
	
	/*
	*
	*/
	public static int[][] buildOverallTruthTable(){
		double w = numParameters
		double l = Math.pow(2, w);
		
		int[][] table = new int[(int)(l)][(int)(w)];
		
		for(int i=0; i<l; i++){
			if(i==0){
				for(int j=0; j<w; j++){
					table[i][j]=0;
				}
			}else{
				for(int j=(int)(w)-1; j>=0; j--){
					if(j==((int)(w)-1)){
						if(table[i-1][j] == 0){
							table[i][j] = 1;
						}else{
							table[i][j] = 0;
						}
					}else{
						if(table[i-1][j+1] == table[i][j+1] || (table[i-1][j+1] == 0 && table[i][j+1] == 1)){
							table[i][j] = table[i-1][j];
						}else{
							if(table[i-1][j] == 0){
								table[i][j] = 1;
							}else{
								table[i][j] = 0;
							}
						}
					}
				}
			}
		}
		
		//Testing output REMOVE
		for(int i=0; i<l; i++){
			for(int j=0; j<w; j++){
				System.out.println("i: " + Integer.toString(i) + "  j: " + Integer.toString(j) + "  val: " + Integer.toString(table[i][j]));
			}
		}
		
		return table;
	}
	
	/*
	*
	*/
	public static void findToDisplay(){
		
		first = 0;	//index of parameters<>
		second = 1;	//index of parameters<>
		while(first < numParameters){
			
			boolean ff = false;
			boolean ft = false;
			boolean tf = false;
			boolean tt = false;
			int row = 0;	//which row we're on in table
			if(first == 0 && second == 1){
				while(!ff && !ft && !tf && !tt){
					if(row >= numRows){
						System.out.println("ERROR: Could not find pair coverage");
						System.exit(2);
					}
					
					//Exhaustive Checking
					if(table[row][first] == false && table[row][second] == false && ff == false){
						ff = true;
						toDisplay.add(row);
					
					}else if(table[row][first] == false && table[row][second] == true && ft == false){
						ft = true;
						toDisplay.add(row);
						
					}else if(table[row][first] == true && table[row][second] == false && tf == false){
						tf = true;
						toDisplay.add(row);
						
					}else if(table[row][first] == true && table[row][second] == true && tt == false){
						tt = true;
						toDisplay.add(row);
						
					}
					
					row = row + 1;
				}
				
			}else{
				int flag = 0;
				while(!ff && !ft && !tf && !tt){
					if(row > numRows){
						System.out.println("ERROR: Could not find pair coverage");
						System.exit(2);
					}
					
					if(flag == 0){
						//Check toDisplay First
						int i = 0;
						while(i < toDisplay.size()){
							if(table[i][first] == false && table[i][second] == false && ff == false){
								ff = true;
							
							}else if(table[i][first] == false && table[i][second] == true && ft == false){
								ft = true;
								
							}else if(table[i][first] == true && table[i][second] == false && tf == false){
								tf = true;
								
							}else if(table[i][first] == true && table[i][second] == true && tt == false){
								tt = true;
								
							}
							
							i = i + 1;
						}
						
						flag = 1;
						
					}else{
						//Exhaustive Checking
						if(table[row][first] == false && table[row][second] == false && ff == false){
							ff = true;
							toDisplay.add(row);
						
						}else if(table[row][first] == false && table[row][second] == true && ft == false){
							ft = true;
							toDisplay.add(row);
							
						}else if(table[row][first] == true && table[row][second] == false && tf == false){
							tf = true;
							toDisplay.add(row);
							
						}else if(table[row][first] == true && table[row][second] == true && tt == false){
							tt = true;
							toDisplay.add(row);
							
						}
						
						row = row + 1;
						
					}
					
				}
					
			}
			
			//update the indices we are comparing
			if(second == numParameters){
				first = first + 1;
				second = first + 1;
			}else{
				second = second + 1;
			}
		
		}
	}
		
	/*
	*
	*/
	public static void displayAll(){
		int i = 0;
		while(i < toDisplay.size()){
			System.out.println(toDisplay[i]);
			
			i = i + 1;
		}
	}
	
	/*
	*
	*/
	public static void main(String[] args){
		//Parse Inputs, concatenate if > 10
		parseInput(args);
		
		//Build Exhaustive Truth Table, #param^2 by #param
		table = buildOverallTruthTable();
		
		//Find all toDisplay Tests
		findToDisplay();
		
		//Display all toDisplay
		displayAll();
		
		//Exit Main
		System.exit(0);
	}	
}