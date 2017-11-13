import java.util.ArrayList;


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
	public static void main(String[] args){
		//Parse Inputs, concatenate if > 10
		if(args.length < 2){
			System.out.println("ERROR: Pairwise.java requires at least two parameter arguments to produce a pairwise combination");
			System.exit(1);
		}else{
			int i = 0;
			while(i < args.length){
				if(args[i].length() > 10){
					args[i] = args[i].substring(0,11);
				}
			}
		}
		
		//Build Exhaustive Truth Table, #param^2 by #param
		
		
		//Find all toExecute Tests
		
		
		//Display toExecute
		
	}	
}