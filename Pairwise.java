import java.util.ArrayList;

/*
*
*/
public class Pairwise{
	
	/*
	*
	*/
	
	public static ArrayList<String> parseInput(String[] arguments){
		ArrayList<String> parameters = new ArrayList<String>();
		parameters.clear();
		
		if(arguments.length < 2){
			return parameters;
		}else{
			int numParameters = arguments.length;
			int i = 0;
			while(i < numParameters){
				if(arguments[i].length() > 10){
					arguments[i] = arguments[i].substring(0,10);
				}
				parameters.add(arguments[i]);
				i = i + 1;
			}
		}
		
		return parameters;
	}
	
	/*
	*
	*/
	
	public static int[][] buildOverallTruthTable(int l, int w){
		int[][] table = new int[l][w];
		
		for(int i=0; i<l; i++){
			if(i==0){
				for(int j=0; j<w; j++){
					table[i][j]=0;
				}
			}else{
				for(int j=w-1; j>=0; j--){
					if(j==w-1){
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
		
		return table;
	}
	
	/*
	*
	*/
	
	public static ArrayList<Integer> findToDisplay(int[][] table, int numRows, int numParameters){
		ArrayList<Integer> toDisplay = new ArrayList<Integer>();
		int first = 0;	//index of parameters<>
		int second = 1;	//index of parameters<>
		
		while(second < numParameters){
			
			boolean ff = false;
			boolean ft = false;
			boolean tf = false;
			boolean tt = false;
			int row = 0;	//which row we're on in table
			if(first == 0 && second == 1){
				ff = true;
				tt = true;
				toDisplay.add(0);
				toDisplay.add(numRows-1);
				while(!ff || !ft || !tf || !tt){
					if(row >= numRows){
						System.out.println("ERROR: Could not find pair coverage");
						System.exit(2);
					}
					
					//Exhaustive Checking
					if(table[row][first] == 0 && table[row][second] == 1 && ft == false){
						ft = true;
						toDisplay.add(row);
						
					}else if(table[row][first] == 1 && table[row][second] == 0 && tf == false){
						tf = true;
						toDisplay.add(row);
						
					}
					row = row + 1;
				}
				
			}else{
				int flag = 0;
				while(!ff || !ft || !tf || !tt){
					if(row > numRows){
						System.out.println("ERROR: Could not find pair coverage");
						System.exit(2);
					}
					
					if(flag == 0){
						//Check toDisplay First
						int i = 0;
						while(i < toDisplay.size()){
							
							if(table[toDisplay.get(i)][first] == 0 && table[toDisplay.get(i)][second] == 0 && ff == false){
								ff = true;
							
							}else if(table[toDisplay.get(i)][first] == 0 && table[toDisplay.get(i)][second] == 1 && ft == false){
								ft = true;
								
							}else if(table[toDisplay.get(i)][first] == 1 && table[toDisplay.get(i)][second] == 0 && tf == false){
								tf = true;
								
							}else if(table[toDisplay.get(i)][first] == 1 && table[toDisplay.get(i)][second] == 1 && tt == false){
								tt = true;
								
							}
							
							i = i + 1;
						}
						
						flag = 1;
						
					}else{
						//Exhaustive Checking
						if(table[row][first] == 0 && table[row][second] == 0 && ff == false){
							ff = true;
							toDisplay.add(row);
						
						}else if(table[row][first] == 0 && table[row][second] == 1 && ft == false){
							ft = true;
							toDisplay.add(row);
							
						}else if(table[row][first] == 1 && table[row][second] == 0 && tf == false){
							tf = true;
							toDisplay.add(row);
							
						}else if(table[row][first] == 1 && table[row][second] == 1 && tt == false){
							tt = true;
							toDisplay.add(row);
							
						}
						
						row = row + 1;
						
					}
					
				}
					
			}
			
			//update the indices we are comparing
			second = second + 1;
			if(second == numParameters){
				first = first + 1;
				second = first + 1;
			}
		
		}
		
		return toDisplay;
	}
	
	/*
	*
	*/
	
	public static String buildOutput(ArrayList<String> cats, ArrayList<Integer> rows, int[][] table){
		String out = "";

		for(String c:cats){
			out += c + "\t";
		}
		out += "\n";

		for(int r:rows){
			for(int i=0;i<table[r].length; i++){
				out+=Integer.toString(table[r][i])+"\t";
			}
			out+="\n";
		}


		return out;
	}
	
	/*
	*
	*/

	public static void main(String[] args){
		//Parse Inputs, concatenate if > 10
		ArrayList<String> parameters = new ArrayList<String>();
		parameters = parseInput(args);
		
		if(!parameters.isEmpty()){
			//Build Exhaustive Truth Table, #param^2 by #param
			int w = parameters.size();
			int l = (int) Math.pow(2, w);
			
			int[][] table;
			table = buildOverallTruthTable(l, w);
			
			//Find all toDisplay Tests
			ArrayList<Integer> toDisplay = new ArrayList<Integer>();
			toDisplay = findToDisplay(table, l, w);
			
			//Build Program Output
			String printMe;
			printMe = buildOutput(parameters, toDisplay, table);
			System.out.println(printMe);
		}else{
			System.out.println("ERROR: Invalid Arguments. \nAt least two arguments are required.");
			System.exit(1);
		}
		
		//Exit Main
		System.exit(0);
	}	
}