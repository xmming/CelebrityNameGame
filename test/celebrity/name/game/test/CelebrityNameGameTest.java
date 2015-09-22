package celebrity.name.game.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.*;
import celebrity.name.game.CelebrityNameGame;

/**
 * Functional testing of CelebrityNameGame.
 * @author xiaoming
 *
 */
public class CelebrityNameGameTest {
	String inputFileName = "sample.in";
	String inputFileName2 = "sample2.in";
	
	@Test
	public void test() throws FileNotFoundException, IOException {
		CelebrityNameGame game1 = new CelebrityNameGame(inputFileName);
		CelebrityNameGame game2 = new CelebrityNameGame(inputFileName2);
		
		String longestNameChain = game1.startGame();
		String longestNameChain2 = game2.startGame();
		
		assertEquals("Lebron James Elton Jackson John Lennon", longestNameChain);
		assertEquals("James Elton Jackson John Lebron James Faulkner", longestNameChain2);
	}		
}
