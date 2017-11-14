//Java imports
import java.lang.*;
import java.util.ArrayList;

//JUnit and Mockito imports
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * This is the test class for Deliverable 5, CS1632 Fall 2017
 * Authors: Randyll Bearer (github: rlb97) and Branden Keck (github: BrandenKeck)
 *
 */

public class Del5Test {
	//Declare reusable element object
	Pairwise _p;
	
	// Create a new Element instance before testing
    @Before
    public void setup() {
		_p = new Pairwise();
    }
    
    // Initial test to ensure that the new Element instance isn't null
    @Test
    public void testElementExists() {
		assertNotNull(_p);
    }
	
	
	/**
	*
	BEGIN TESTS
	
	
	*
	*/

	
	/*
	TEST 1:
	Check that a valid input of two arguments is correctly parsed and returned 
	as an array list.
	*/
	
    @Test
    public void testInputsValid() {
		ArrayList<String> test1 = new ArrayList<String>();
		ArrayList<String> test2 = new ArrayList<String>();
		
		String[] args = new String[2];
		args[0] = "Bill";
		args[1] = "Laboon";
		
		test1 = _p.parseInput(args);
		
		test2.add("Bill");
		test2.add("Laboon");

		assertEquals(test1, test2);
    }
	
	
	/*
	TEST 2:
	Check that an INVALID input returns an empty list, which will be later
	handled as an error.
	An input of only one argument is invalid.  For this test we will use an
	input array of only one element, "Bill".
	*/
	
    @Test
    public void testInputsTooFew() {
		//Create an array list for the test
		ArrayList<String> test1 = new ArrayList<String>();
		
		//
		String[] args = new String[1];
		args[0] = "Bill";
		
		test1 = _p.parseInput(args);
		
		boolean check = test1.isEmpty();
		assertTrue(check);
    }
	
	
	/*
	TEST 3:
	Check that any input that is longer than ten characters is truncated
	to be only ten characters long.
	An input array of length 2 is used so that the input is considered valid.
	*/
	
    @Test
    public void testInputsTruncated() {
		//Create two new array lists for the test
		ArrayList<String> test1 = new ArrayList<String>();
		ArrayList<String> test2 = new ArrayList<String>();
		
		//Argument array with inputs longer than ten characters
		String[] args = new String[2];
		args[0] = "william_laboon";
		args[1] = "lilliam_waboon";
		
		//Pass the array to the parsing function
		test1 = _p.parseInput(args);
		
		//The second array list will contain properly truncated results
		test2.add("william_la");
		test2.add("lilliam_wa");

		//Make sure observations match expectations
		assertEquals(test1, test2);
    }
	
	
	/*
	TEST 4:
	Check that an appropriate truth table is generated for two inputs
	The truth table is generated in a similar way to binary counting.
	Therefore, the expected output for any amount of arguments is easily derived.
	*/
	@Test
	public void testTruthTableTwoInputs() {
		//Create two new array lists for the test
		int[][] test1 = new int[][]{
			{0,0},
			{0,1},
			{1,0},
			{1,1}
		};
		int[][] test2;
		
		//Run the method
		test2 = _p.buildOverallTruthTable(4, 2);

		//Make sure observed method results match what was expected 
		assertEquals(test1, test2);
	}
	
	
	/*
	TEST 4:
	Check that an appropriate truth table is generated for THREE inputs
	After checking for both 2 and 3 inputs, it should follow from here
	that the logic used to build the truth table is sound.
	*/
	@Test
	public void testTruthTableTwoInputs() {
		//Create two new array lists for the test
		int[][] test1 = new int[][]{
			{0,0},
			{0,1},
			{1,0},
			{0,0},
			{0,1},
			{1,0},
			{0,0},
			{1,1}
		};
		int[][] test2;
		
		test2 = _p.buildOverallTruthTable(4, 2);

		//Make use the 
		assertEquals(test1, test2);
	}
	
}