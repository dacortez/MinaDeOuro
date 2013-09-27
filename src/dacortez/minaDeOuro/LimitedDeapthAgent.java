/**
 * 
 */
package dacortez.minaDeOuro;

/**
 * @author dacortez
 *
 */
public class LimitedDeapthAgent extends Agent {
	private static final int MAX_LIMIT = 200;
	private Solution best;
	
	/**
	 * @param startPosition
	 */
	public LimitedDeapthAgent(Position startPosition) {
		super(startPosition);
		best = new Solution(root);
	}

	/* (non-Javadoc)
	 * @see dacortez.minaDeOuro.Agent#search()
	 */
	@Override
	public Solution search() {
		closed.clear();
		return recursiveDLS(root, MAX_LIMIT);
	}
	
	private Solution recursiveDLS(Node node, int limit) {
		closed.add(node.getState());
		boolean cutoffOccurred = false;
		if (goalTest(node.getState()))
			if (-node.getPathCost() > best.getCost())
				best = new Solution(node);
		if (node.getDepth() == limit)
			return new Solution(null);
		for (Node child: node.expand()) {
			if (!closed.contains(child.getState())) {
				Solution result = recursiveDLS(child, limit);
				if (result != null && result.isCutoff()) 
					cutoffOccurred = true;
				else if (result != null) {
					best = result;
				}
			}
		}
		if (cutoffOccurred)
			return new Solution(null);
		else 
			return best;
	}

}
