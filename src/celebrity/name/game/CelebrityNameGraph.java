package celebrity.name.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CelebrityNameGraph is graph constructed based on the input file with the celebrities' names.
 * Each node in the graph represent a celebrity with a name. 
 * Names can be duplicated because different people can have the same name.
 * An edge from node A to node B means that A' lastname is the same with B's firstname.
 * @author xiaoming
 *
 */
public class CelebrityNameGraph {
	List<GraphNode> nameNodes;
	
	public CelebrityNameGraph() {
		nameNodes = new ArrayList<>();
	}
	
	public List<GraphNode> getNameNodes() {
		return nameNodes;
	}

	/**
	 * Find the longest name chain within all the names in this graph.
	 * The rule is that if node A' last name is the same with node B' first name, then A and B' names 
	 * can be connected to form a chain.
	 * @return a string representing the longest name chain.
	 */
	public String findLongestNameChain() {
		/*
		 * candidatePaths is a mapping from each graph node to the longest path starting from this node.
		 * This is used to cache the result for the visited graph node, so that next time when we revisit it we can
		 * directly return its longest path instead of doing repeated calculation.
		 * However, nodes within a cycle is not cached. 
		 */
		Map<GraphNode, StringBuilder> candidatePaths = new HashMap<>();
		
		/*
		 * The longest name chain we want to get from this graph.
		 */
		StringBuilder longestPath = new StringBuilder();
		
		/*
		 * Helps to detect a cycle in a graph and tells whether a node is in a cycle or not.
		 */
		GraphCycleDetector cycleDetector = new GraphCycleDetector();
		
		for (GraphNode node : nameNodes) {
			if (!candidatePaths.containsKey(node)) {
				StringBuilder candidatePath = dfs(node, cycleDetector, candidatePaths);
				if (candidatePath.length() > longestPath.length()) {
					longestPath = candidatePath;
				}
			}
		}
		
		return reverseNames(longestPath.toString());
	}

	private StringBuilder dfs(GraphNode node, GraphCycleDetector cycleDetector, Map<GraphNode, StringBuilder> candidatePaths) {
		if (candidatePaths.containsKey(node)) {
			return candidatePaths.get(node);
		}
		
		cycleDetector.addNode(node);
		StringBuilder candidatePath = new StringBuilder();
		List<GraphNode> neighbors = node.neighbors;
		
		/*
		 * if a node has no neighbors, this node is the end of a name chain;
		 * otherwise, we want to look at its following nodes.
		 */
		if (neighbors.isEmpty()) {
			candidatePath.append(reverseNames(node.getFullName()));
		} else {
			for (GraphNode neighbor : neighbors) {
				StringBuilder tempPath = new StringBuilder();

				if (cycleDetector.containsNode(neighbor)) {
					// Cycle detected
					cycleDetector.storeCycleNodes(neighbor);
					tempPath = new StringBuilder();
					tempPath.append(neighbor.getFirstName());
				} else {
					tempPath.append((dfs(neighbor, cycleDetector, candidatePaths)));
				}
				if (tempPath.length() > candidatePath.length()) {
					candidatePath = tempPath;
				}
			}
			candidatePath.append(' ').append(reverseNames(node.getNonLastNames()));
		}
		
		cycleDetector.removeNode(node);

		/*
		 * If cycle is detected and this node is in cycle, we can't cache its
		 * optimized solution. Otherwise cache the solution.
		 */
		if (!cycleDetector.isCycleNode(node)) {
			candidatePaths.put(node, candidatePath);
		}
		return candidatePath;
	}
	
	/*
	 * Used to get the correct order of name chain.
	 */
	private String reverseNames(String name) {
		StringBuilder result = new StringBuilder(name.length() + 1);
		String[] parts = name.split(" ");
		for (int i = parts.length - 1; i >= 0; i--) {
			result.append(parts[i]).append(' ');
		}
		result.setLength(result.length() - 1);
		return result.toString();
	}
}
