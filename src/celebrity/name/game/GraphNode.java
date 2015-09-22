package celebrity.name.game;

import java.util.ArrayList;
import java.util.List;

/**
 * GraphNode represent a celebrity with the given name.
 * @author xiaoming
 *
 */
public class GraphNode {
	String fullname;
	
	/**
	 * A neighbor of a graph node(A) represents another graph node(B) whose first name is the same 
	 * as the last name of A.
	 */
	List<GraphNode> neighbors;
	
	public List<GraphNode> getNeighbors() {
		return neighbors;
	}
	
	public GraphNode(String fullName) {
		this.fullname = fullName;
		this.neighbors = new ArrayList<>();
	}
	
	public String getFullName() {
		return fullname;
	}
	
	public String getFirstName() {
		int index = fullname.indexOf(' ');
		return fullname.substring(0, index);
	}

	/**
	 * Given a full name, return a string containing all other names except the last name.
	 */
	public String getNonLastNames() {
		int index = fullname.lastIndexOf(' ');
		return fullname.substring(0, index);
	}

	public String getLastName() {
		int index = fullname.lastIndexOf(' ');
		return fullname.substring(index + 1);
	}
}
