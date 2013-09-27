/**
 * 
 */
package dacortez.minaDeOuro;

/**
 * @author dacortez
 *
 */
public class LimitedDeapthAgent extends Agent {
	private static final int MAX_LIMIT = 100;
	
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
		return recursiveDLS(root, MAX_LIMIT);
	}
	
	private Solution recursiveDLS(Node node, int limit) {
		closed.add(node.getState());
		boolean cutoffOccured = false;
		if (goalTest(node.getState())) return new Solution(node);
		if (node.getDepth() == limit) return new Cutoff();
		for (Node child: node.expand())
			if (!closed.contains(child.getState())) {
				Solution result = recursiveDLS(child, limit);
				if (result instanceof Cutoff)
					cutoffOccured = true;
				else if (result != null)
					return result;
			}
		if (cutoffOccured) return new Cutoff();
		return null;
	}
}
