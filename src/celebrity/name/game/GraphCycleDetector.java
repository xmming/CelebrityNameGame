package celebrity.name.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * GraphCycleDector can detect cycle in a graph.
 * @author xiaoming
 *
 */
public class GraphCycleDetector {
	/**
	 * exploredNodesOnPath stores the nodes that have been visited from one dfs search 
	 * starting from one node that have not been visited before this dfs search.
	 */
	Set<GraphNode> exploredNodesOnPath;
	
	/**
	 * visitingPath stores the nodes that have been visited from one dfs search 
	 * starting from one node that have not been visited before this dfs search.
	 * The nodes are stored according to the time sequence of when they are reached from the dfs search.
	 */
	List<GraphNode> visitingPath;
	
	/**
	 * Stores all the nodes that are in cycles.
	 */
	Set<GraphNode> cycleNodes;

	public GraphCycleDetector() {
		this.exploredNodesOnPath = new HashSet<>();
		this.visitingPath = new ArrayList<>();
		this.cycleNodes = new HashSet<>();
	}

	public void addNode(GraphNode n) {
		exploredNodesOnPath.add(n);
		visitingPath.add(n);
	}

	public void removeNode(GraphNode n) {
		exploredNodesOnPath.remove(n);
		visitingPath.remove(visitingPath.size() - 1);
	}

	public boolean containsNode(GraphNode n) {
		if (!exploredNodesOnPath.contains(n)) {
			return false;
		}
		// Cycle detected.
		return true;
	}

	/**
	 * Store all nodes that are in the cycle that node n is in. 
	 * @param n a node that is in a cycle.
	 */
	public void storeCycleNodes(GraphNode n) {
		cycleNodes.add(n);
		int i = visitingPath.size() - 1;
		while (!visitingPath.get(i).equals(n)) {
			cycleNodes.add(visitingPath.get(i));
			i--;
		}
	}

	public boolean isCycleNode(GraphNode n) {
		return cycleNodes.contains(n);
	}

}
