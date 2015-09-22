package celebrity.name.game;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Construct a celebrityNameGraph from the given input file.
 * @author xiaoming
 *
 */
public class GraphGenerator {
	String inputFileName; 
	BufferedReader br;
	
	public GraphGenerator(String inputFileName) throws FileNotFoundException, IOException {
		this.inputFileName = inputFileName;
		br = new BufferedReader(new FileReader(inputFileName));		
	}
	
	/**
	 * Only used in the unit test
	 * @param inputFileName
	 * @param br buffered reader
	 */
	public GraphGenerator(String inputFileName, BufferedReader br) {
		this.inputFileName = inputFileName;
		this.br = br;
	}
	
	/**
	 * Construct a celebrityNameGraph from the given input file.
	 * @return a celebrityNameGraph.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public CelebrityNameGraph constructNameGraph() throws FileNotFoundException, IOException {				
		CelebrityNameGraph nameGraph = new CelebrityNameGraph();		
		addNodes(nameGraph);			
		addEdges(nameGraph);
		
		return nameGraph;
	}
	
	/**
	 * Construct each nameNode based on each full name in the input file and add the node to the celebrityNameGraph.
	 * @param nameGraph
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void addNodes(CelebrityNameGraph nameGraph) throws FileNotFoundException, IOException {
		String name;	
		while ((name = br.readLine()) != null) {
			if(name.indexOf(' ') != -1) {
				GraphNode nameNode = new GraphNode(name);
				nameGraph.nameNodes.add(nameNode);
			}						
		}				
	}

	/**
	 * Add a edge between two nodes when the lastname of the first node is the same with the firstname of the second node.
	 * @param nameGraph
	 */
	private void addEdges(CelebrityNameGraph nameGraph) {
		/*
		 * firstNameMapping represent a mapping from a firstname to all the fullnames that starts with this firstname.
		 * The key of firstNameMapping is a string representing firstname.
		 * The value of firstNameMapping is a list of graph nodes that represent celebrities whose firstnames are the same with the key.
		 */
		Map<String, List<GraphNode>> firstNameMapping = getFirstNameMapping(nameGraph);
		
		for (GraphNode nameNode : nameGraph.nameNodes) {
			String lastName = nameNode.getLastName();
			if (firstNameMapping.containsKey(lastName)) {
				nameNode.neighbors.addAll(firstNameMapping.get(lastName));
			}
		}
	}
	
	private  Map<String, List<GraphNode>> getFirstNameMapping(CelebrityNameGraph nameGraph) {
		Map<String, List<GraphNode>> firstNameMapping = new HashMap<>();
		for (GraphNode nameNode : nameGraph.nameNodes) {
			String firstName = nameNode.getFirstName();
			if (!firstNameMapping.containsKey(firstName)) {
				firstNameMapping.put(firstName, new ArrayList<>());
			}
			firstNameMapping.get(firstName).add(nameNode);
		}
		
		return firstNameMapping;
	}
}
