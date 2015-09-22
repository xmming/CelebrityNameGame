package celebrity.name.game.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.junit.Assert.*;

import celebrity.name.game.CelebrityNameGraph;
import celebrity.name.game.GraphGenerator;
import celebrity.name.game.GraphNode;

public class GraphGeneratorTest {
	String inputFileName = "mockFile";
	List<String> mockNames;
	BufferedReader br;
	GraphGenerator graphGenerator;
	
	@Before 
	public void setup() throws IOException {
		mockNames = Arrays.asList(new String[] {"Elton John", "John Lennon", "James Elton", "Lebron James", "James Faulkner"});
		br = Mockito.mock(BufferedReader.class);
		Mockito.when(br.readLine()).thenReturn("Elton John", "John Lennon", "James Elton", "Lebron James", "James Faulkner").thenReturn(null);
		graphGenerator = new GraphGenerator(inputFileName, br);
	}
	
	@Test
	public void givenInputFileGraphGeneratorShouldConstructCorrectGraph() throws FileNotFoundException, IOException {
		CelebrityNameGraph nameGraph = graphGenerator.constructNameGraph();		
		graphShouldHaveCorrectNodes(nameGraph);
		graphShouldHaveCorrectEdges(nameGraph);
	}

	private void graphShouldHaveCorrectNodes(CelebrityNameGraph nameGraph) {
		List<GraphNode> nameNodes = nameGraph.getNameNodes();
		for (int i = 0; i < nameNodes.size(); i++) {
			nameNodes.get(i).getFullName().equals(mockNames.get(i));
		}		
	}
	
	private void graphShouldHaveCorrectEdges(CelebrityNameGraph nameGraph) {
		List<GraphNode> nameNodes = nameGraph.getNameNodes();
		assertTrue(nameNodes.get(0).getNeighbors().contains(nameNodes.get(1)));
		assertTrue(nameNodes.get(1).getNeighbors().isEmpty());
		assertTrue(nameNodes.get(2).getNeighbors().contains(nameNodes.get(0)));
		assertTrue(nameNodes.get(3).getNeighbors().contains(nameNodes.get(2)));
		assertTrue(nameNodes.get(3).getNeighbors().contains(nameNodes.get(4)));
		assertTrue(nameNodes.get(4).getNeighbors().isEmpty());
	}
	
}
