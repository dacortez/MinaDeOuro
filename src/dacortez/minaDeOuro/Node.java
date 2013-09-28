/**
 * MAC0425 - Intelig�ncia Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um n� da �rvore de busca expandida pelo agente.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
/**
 * @author dacortez
 *
 */
public class Node {
	// Estado associado ao n�.
	private State state;
	// N� pai que que gerou este n�.
	private Node parentNode;
	// A��o que d� origem a este n� a partir do n� pai.
	private Action action;
	// Custo do caminho da raiz at� este n�.
	private int pathCost;
	// Profundidade deste n� na �rvore de busca.
	private int depth;
	
	/**
	 * @return O estada associada ao n�.
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * @param state O estado associado ao n�.
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * @return O n� pai na �rvore de busca.
	 */
	public Node getParentNode() {
		return parentNode;
	}
	
	/**
	 * @param parentNode O n� pai na �rvore de busca.
	 */
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	/**
	 * @return A a��o que gerou este n� a partir do pai.
	 */
	public Action getAction() {
		return action;
	}
	
	/**
	 * @param action A a��o que gerou este n� a partir do pai.
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	
	/**
	 * @return O custo do caminho a partir da raiz at� este n�.
	 */
	public int getPathCost() {
		return pathCost;
	}
	
	/**
	 * @param pathCost O custo do caminho a partir da raiz at� este n�.
	 */
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	
	/**
	 * @return A profundidade na �rvore de busca deste n�.
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * @param depth A profundidade na �rvore de busca deste n�.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	/**
	 * @return Um lista com todos os n�s que podem ser obtidos a partir
	 * das pos�veis a��es do agente sobre o estado do n� atual. 
	 */
	public List<Node> expand() {
		List<Node> list = new ArrayList<Node>();
		for (ActionState as: state.getSuccessors()) {
			Node node = new Node();
			node.setState(as.getState());
			node.setParentNode(this);
			node.setAction(as.getAction());
			int stepCost = Main.getEnvironment().performanceMeasurement(as.getAction());
			node.setPathCost(pathCost + stepCost);
			node.setDepth(depth + 1);
			list.add(node);
		}
		return list;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(state);
		sb.append("action = ").append(action).append('\n');
		sb.append("pathCost = ").append(pathCost).append('\n');
		sb.append("depth = ").append(depth).append('\n');
		return sb.toString();
	}
}
