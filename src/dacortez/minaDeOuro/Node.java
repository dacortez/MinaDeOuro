/**
 * 
 */
package dacortez.minaDeOuro;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dacortez
 *
 */
public class Node {
	private State state;
	private Node parentNode;
	private Actions action;
	private int pathCost;
	private int depth;
	
	public State getState() {
		return state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public Node getParentNode() {
		return parentNode;
	}
	
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	public Actions getAction() {
		return action;
	}
	
	public void setAction(Actions action) {
		this.action = action;
	}
	
	public int getPathCost() {
		return pathCost;
	}
	
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public List<Node> expand() {
		List<Node> list = new ArrayList<Node>();
		for (ActionState as: state.getSuccessors()) {
			Node node = new Node();
			node.setState(as.getState());
			node.setParentNode(this);
			node.setAction(as.getAction());
			node.setPathCost(pathCost + stepCost(as.getAction()));
			node.setDepth(depth + 1);
			list.add(node);
		}
		return list;
	}
	
	private int stepCost(Actions action) {
		if (action == Actions.Pick)
			return -4 * State.getSize() ;
		return 1;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(state);
		sb.append("action = " + action + "\n");
		sb.append("pathCost = " + pathCost + "\n");
		sb.append("depth = " + depth + "\n");
		return sb.toString();
	}
}
