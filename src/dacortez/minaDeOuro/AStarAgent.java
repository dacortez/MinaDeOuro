/**
 * 
 */
package dacortez.minaDeOuro;

import java.util.Comparator;
import java.util.List;
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
		int size = Main.getEnvironment().getSize();
		int totalGold = Main.getEnvironment().getTotalGold();
		int qSize = size * size * totalGold;
		Queue<Node> queue = new PriorityQueue<Node>(qSize, new Comparator<Node>() {  
            public int compare(Node n1, Node n2) {
            	return getF(n1) > getF(n2) ? -1 : 1;
            }  
        });
		return queue;
	}
	
	private int getF(Node node) {
		return node.getPathCost() + getH(node);
	}
	
	private int getH(Node node) {
		Position agentPosition = node.getState().getPosition();
		List<Position> picked = node.getState().getPicked();
		List<Position> goldPositions = Main.getEnvironment().getGoldPositions();
		Position nearest = null;
		int min = 2 * Main.getEnvironment().getSize();
		for (Position gold: goldPositions)
			if (!picked.contains(gold)) {
				int dist = agentPosition.distTo(gold);
				if (dist < min) {
					nearest = gold;
					min = dist;
				}
			}
		if (nearest != null)
			return -min - nearest.distTo(startPosition) + 4 * Main.getEnvironment().getSize();
		return -agentPosition.distTo(startPosition);
		//return 0;
	}
}
