/**
 * 
 */
package dacortez.minaDeOuro;

import java.util.List;
import java.util.ArrayList;

/**
 * @author dacortez
 *
 */
public abstract class Agent implements AgentInterface {
	protected Position startPosition;
	protected List<State> closed;
	protected Node root;
	
	public Agent(Position startPosition) {
		this.startPosition = startPosition;
		closed = new ArrayList<State>();
		setRoot();
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
	
	protected boolean goalTest(State state) {
		return startPosition.equals(state.getPosition()); 
	}
	
	/* (non-Javadoc)
	 * @see dacortez.minaDeOuro.AgentInterface#search()
	 */
	@Override
	public abstract Solution search();
}
