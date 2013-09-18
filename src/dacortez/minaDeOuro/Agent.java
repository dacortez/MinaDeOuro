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
	private Position position;
	private Node root;
	private List<State> closed;
	
	public Agent(SearchMethod method, Position position) {
		this.method = method;
		this.position = position;
		closed = new ArrayList<State>();
	}
	
	public void searh() {
		setRoot();
		closed.clear();
		switch (method) {
		case AStar:
			break;
		case Breadth:
			breadthSearch();
			break;
		case LimitedDepth:
			break; 
		}
	}
	
	private void setRoot() {
		State state = new State(position);
		root = new Node();
		root.setState(state);
		root.setParentNode(null);
		root.setAction(null);
		root.setPathCost(0);
		root.setDepth(0);
	}
	
	private void breadthSearch() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			if (goalTest(node.getState())) {
				System.out.println("solução encontrada!!");
				System.out.println(node);
				return;
			}
			if (!isStateInClosedList(node.getState())) {
				closed.add(node.getState());
				queue.addAll(node.expand());
			}
		}
		System.out.println("solução NÃO encontrada!!");
	}
	
	private boolean goalTest(State state) {
		if (state.getTotalPicked() == Main.getEnvironment().getTotalGold())
			return (position.isSame(state.getPosition()));
		return false; 
	}
	
	private boolean isStateInClosedList(State state) {
		for (State closedState: closed)
			if (closedState.isSame(state))
				// TODO: implementar método equals
				return true;
		return false;
	}	
}