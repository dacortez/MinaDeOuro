/**
 * 
 */
package dacortez.minaDeOuro;

/**
 * @author dacortez
 *
 */
public class LimitedDeapthAgent extends Agent {
	private static final int MAX_LIMIT = 1000;
	private Solution best;
	
	/**
	 * @param startPosition
	 */
	public LimitedDeapthAgent(Position startPosition) {
		super(startPosition);
	}

	/* (non-Javadoc)
	 * @see dacortez.minaDeOuro.Agent#search()
	 */
	@Override
	public Solution search() {
		closed.clear();
		best = new Solution(root);
		recursiveDLS(root, MAX_LIMIT);
		return best;
	}
	
	private void recursiveDLS(Node node, int limit) {
		closed.add(node.getState());
		if (goalTest(node.getState()))
			if (-node.getPathCost() > best.getCost())
				best = new Solution(node);
		if (node.getDepth() == limit)
			return;
		for (Node child: node.expand())
			if (!closed.contains(child.getState()))
				recursiveDLS(child, limit);
	}
}
