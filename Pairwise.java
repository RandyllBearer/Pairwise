import java.util.ArrayList;
import java.lang.Math.*;
/*
*
*/
public class Pairwise{
	ArrayList<Integer> toDisplay = new ArrayList<Integer>();	//Will hold all the rows we need to display
	ArrayList<String> parameters = new ArrayList<String>();		//Will hold all the user-passed in parameters
	int first = 0;
	int second = 0;
	int numParameters = 0;		//How many passed in user arguments there are
	int numRows = 0;			//How many total rows there are: #param^2
	String[][] table = null;	//Will hold the exhaustive truth table, padded to 10 character length
	
	/*
	*
	*/
	
	
	public static int[][] buildOverallTruthTable(String[] cats){
		double w = cats.length;
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


	public static void main(String[] args){
		//Parse Inputs, concatenate if > 10
		if(args.length < 2){
			System.out.println("ERROR: Pairwise.java requires at least two parameter arguments to produce a pairwise combination");
			System.exit(1);
		}else{
			/* int i = 0;
			while(i < args.length){
				if(args[i].length() > 10){
					args[i] = args[i].substring(0,11);
				}
			} */
		}
		
		//Build Exhaustive Truth Table, #param^2 by #param
		buildOverallTruthTable(args);
		
		//Find all toExecute Tests
		
		
		//Display toExecute
		
	}	
}