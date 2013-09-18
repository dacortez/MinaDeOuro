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
	private Action action;
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
	
	public Action getAction() {
		return action;
	}
	
	public void setAction(Action action) {
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
	
	private int stepCost(Action action) {
		if (action == Action.Pick)
			return -4 * Main.getEnvironment().getSize();
		return 1;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(state);
		sb.append("action = ").append(action).append('\n');
		sb.append("pathCost = ").append(pathCost).append('\n');
		sb.append("depth = ").append(depth).append('\n');
		return sb.toString();
	}
}
