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
public class BreadthAgent extends Agent {
	
	public BreadthAgent(Position startPosition) {
		super(startPosition);
	}
	
	@Override
	public Solution search() {
		closed.clear();
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
}