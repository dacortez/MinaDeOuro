/**
 * 
 */
package dacortez.minaDeOuro;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author dacortez
 *
 */
public class Agent {
	private Node root;
	
	public Agent(State state) {
		root = new Node();
		root.setState(state);
		root.setParentNode(null);
		root.setAction(null);
		root.setPathCost(0);
		root.setDepth(0);
		System.out.println(root);
	}
	
	public void breadthSearh() {
		Queue<Node> queue = new LinkedList<Node>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node node = queue.remove();
			if (goalTest(node.getState())) {
				System.out.println("solução encontrada!!");
				System.out.println(node);
				return;
			}
			queue.addAll(node.expand());
		}
		System.out.println("solução NÃO encontrada!!");
	}
	
	private boolean goalTest(State state) {
		if (!state.isThereGold())
			return (state.getX() == 0 && state.getY() == 0);
		return false; 
	}
}