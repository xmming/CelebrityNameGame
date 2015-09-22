package celebrity.name.game.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import celebrity.name.game.CelebrityNameGraph;
import celebrity.name.game.GraphNode;

public class CelebrityNameGraphTest {
	String[] mockNamesWithNoCycle =  {"Elton Jackson John", "John Lennon", "James Elton", "Lebron James", "James Miachel Faulkner"};
	String[] mockNamesWithCycle = {"Jack Lebron", "Lebron James", "James Elton",  "Elton Jackson John", "John Lebron", "James Faulkner"};
	CelebrityNameGraph graphWithNoCycle;
	CelebrityNameGraph graphWithCycle;
	
	@Before
	public void setup() {
		graphWithNoCycle = generateMockGraphWithNoCycle();
		graphWithCycle = generateMockGraphWithCycle();
	}
	
	@Test
	public void findLongestNameChainInAGraphWithNoCycleShouldReturnCorrectNameChain() {
		String longestNameChain = graphWithNoCycle.findLongestNameChain();
		assertEquals(longestNameChain, "Lebron James Elton Jackson John Lennon");
	}
	
	@Test
	public void findLongestNameChainInAGraphWithCycleShouldReturnCorrectNameChain() {
		String longestNameChain = graphWithCycle.findLongestNameChain();
		assertEquals(longestNameChain, "James Elton Jackson John Lebron James Faulkner");
	}
	
	/*
	 * Lebron James --> James Elton --> Elton Jackson John --> John Lemmon 
	 *    |
	 *    |
	 *    V
	 * James Faulkner
	 */
	private CelebrityNameGraph generateMockGraphWithNoCycle() {
		CelebrityNameGraph graph = new CelebrityNameGraph();
		GraphNode node0 = new GraphNode(mockNamesWithNoCycle[0]);
		GraphNode node1 = new GraphNode(mockNamesWithNoCycle[1]);
		GraphNode node2 = new GraphNode(mockNamesWithNoCycle[2]);
		GraphNode node3 = new GraphNode(mockNamesWithNoCycle[3]);
		GraphNode node4 = new GraphNode(mockNamesWithNoCycle[4]);
		
		node0.getNeighbors().add(node1);
		node2.getNeighbors().add(node0);
		node3.getNeighbors().add(node2);
		node3.getNeighbors().add(node4);
		
		graph.getNameNodes().add(node0);
		graph.getNameNodes().add(node1);
		graph.getNameNodes().add(node2);
		graph.getNameNodes().add(node3);
		graph.getNameNodes().add(node4);
		
		return graph;
	}
	
	/*Jack Lebron
	 *    | 
	 *    |
	 *    V
	 * Lebron James --> James Elton --> Elton Jackson John --> John Lebron( go back to Lebron James)
	 * 	  |
	 *    |
	 *    V
	 * James Miachel Faulkner							
	 * 
	 */
	private CelebrityNameGraph generateMockGraphWithCycle() {
		CelebrityNameGraph graph = new CelebrityNameGraph();
		GraphNode node0 = new GraphNode(mockNamesWithCycle[0]);
		GraphNode node1 = new GraphNode(mockNamesWithCycle[1]);
		GraphNode node2 = new GraphNode(mockNamesWithCycle[2]);
		GraphNode node3 = new GraphNode(mockNamesWithCycle[3]);
		GraphNode node4 = new GraphNode(mockNamesWithCycle[4]);
		GraphNode node5 = new GraphNode(mockNamesWithCycle[5]);
		
		node0.getNeighbors().add(node1);
		node1.getNeighbors().add(node2);
		node1.getNeighbors().add(node5);
		node2.getNeighbors().add(node3);
		node3.getNeighbors().add(node4);
		node4.getNeighbors().add(node1);
		
		graph.getNameNodes().add(node0);
		graph.getNameNodes().add(node1);
		graph.getNameNodes().add(node2);
		graph.getNameNodes().add(node3);
		graph.getNameNodes().add(node4);
		graph.getNameNodes().add(node5);
		
		return graph;
	}

}


