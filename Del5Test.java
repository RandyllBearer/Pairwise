//Java imports
import java.lang.*;

//JUnit and Mockito imports
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * This is the test class for Deliverable 5, CS1632 Fall 2017
 * Authors: Randyll Bearer (github: rlb97) and Branden Keck (github: BrandenKeck)
 *
 */

public class Del4Test {
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
	
	*/
	
    @Test
    public void test1() {

		//assertFalse();
		//assertEquals(1, 2);
    }
	
}