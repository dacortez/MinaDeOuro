/**
 * 
 */
package dacortez.minaDeOuro;

/**
 * @author dacortez
 *
 */
public class LimitedDeapthAgent extends Agent {
	private static final int MAX_LIMIT = 500;
	
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
		return recursiveDLS(root, MAX_LIMIT);
	}
	
	private Solution recursiveDLS(Node node, int limit) {
		if (!closed.contains(node.getState())) { 
			closed.add(node.getState());
			boolean cutoffOccurred = false;
			if (goalTest(node.getState()))
				return new Solution(node);
			if (node.getDepth() == limit)
				return new Solution(null);
			for (Node child: node.expand()) {
				Solution result = recursiveDLS(child, limit);
				if (result.isCutoff()) 
					cutoffOccurred = true;
				else if (result != null)
					return result;
			}
			if (cutoffOccurred)
				return new Solution(null);
			else 
				return null;
		}
		return new Solution(null);
	}

}
