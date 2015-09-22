package celebrity.name.game;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * CelebrityNameGame will find the longest name chain for a given input file with names of celebrities.
 * @author xiaoming
 *
 */

public class CelebrityNameGame {
	String inputFileName;
	
	public CelebrityNameGame(String fileName) {
		this.inputFileName = fileName;
	}
		
	public String startGame() throws FileNotFoundException, IOException {
		GraphGenerator graphGenerator = new GraphGenerator(inputFileName);
		CelebrityNameGraph nameGraph = graphGenerator.constructNameGraph();
		String longestNameChain = nameGraph.findLongestNameChain();
		
		return longestNameChain;
	}
}
