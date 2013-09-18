/**
 * 
 */
package dacortez.minaDeOuro;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author dacortez
 *
 */
public class Agent {
	private SearchMethod method;
	private Position startPosition;
	private Node root;
	private List<State> closed;
	
	public Agent(SearchMethod method, Position startPosition) {
		this.method = method;
		this.startPosition = startPosition;
		closed = new ArrayList<State>();
	}
	
	public Solution searh() {
		setRoot();
		closed.clear();
		switch (method) {
		case A_STAR:
			return null;
		case BREADTH:
			return breadthSearch();
		case LIMITED_DEPTH:
			return null;
		}
		return null;
	}
	
	private void setRoot() {
		State state = new State(startPosition);
		root = new Node();
		root.setState(state);
		root.setParentNode(null);
		root.setAction(null);
		root.setPathCost(0);
		root.setDepth(0);
	}
	
	private Solution breadthSearch() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			if (goalTest(node.getState()))
				return new Solution(node);
			if (!closed.contains(node.getState())) {
				closed.add(node.getState());
				queue.addAll(node.expand());
			}
		}
		return null;
	}
	
	private boolean goalTest(State state) {
		if (state.getTotalPicked() == Main.getEnvironment().getTotalGold())
			return (startPosition.equals(state.getPosition()));
		return false; 
	}
}