/**
 * 
 */
package dacortez.minaDeOuro;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author dacortez
 *
 */
public class AStarAgent extends Agent {

	/**
	 * @param startPosition
	 */
	public AStarAgent(Position startPosition) {
		super(startPosition);
	}

	/* (non-Javadoc)
	 * @see dacortez.minaDeOuro.Agent#search()
	 */
	@Override
	public Solution search() {
		closed.clear();
		Queue<Node> queue = getPriorityQueue();
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
	
	private Queue<Node> getPriorityQueue() {
		Queue<Node> queue = new PriorityQueue<Node>(1000, new Comparator<Node>() {  
            public int compare(Node n1, Node n2) {
            	return getF(n1) < getF(n2) ? -1 : 1;
            }  
        });
		return queue;
	}
	
	private int getF(Node node) {
		return node.getPathCost() + getH(node);
	}
	
	private int getH(Node node) {
		return 1;
	}
}
