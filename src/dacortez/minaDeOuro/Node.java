/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um nó da árvore de busca expandida pelo agente.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
/**
 * @author dacortez
 *
 */
public class Node {
	// Estado associado ao nó.
	private State state;
	// Nó pai que que gerou este nó.
	private Node parentNode;
	// Ação que dá origem a este nó a partir do nó pai.
	private Action action;
	// Custo do caminho da raiz até este nó.
	private int pathCost;
	// Profundidade deste nó na árvore de busca.
	private int depth;
	
	/**
	 * @return O estada associada ao nó.
	 */
	public State getState() {
		return state;
	}
	
	/**
	 * @param state O estado associado ao nó.
	 */
	public void setState(State state) {
		this.state = state;
	}
	
	/**
	 * @return O nó pai na árvore de busca.
	 */
	public Node getParentNode() {
		return parentNode;
	}
	
	/**
	 * @param parentNode O nó pai na árvore de busca.
	 */
	public void setParentNode(Node parentNode) {
		this.parentNode = parentNode;
	}
	
	/**
	 * @return A ação que gerou este nó a partir do pai.
	 */
	public Action getAction() {
		return action;
	}
	
	/**
	 * @param action A ação que gerou este nó a partir do pai.
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	
	/**
	 * @return O custo do caminho a partir da raiz até este nó.
	 */
	public int getPathCost() {
		return pathCost;
	}
	
	/**
	 * @param pathCost O custo do caminho a partir da raiz até este nó.
	 */
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	
	/**
	 * @return A profundidade na árvore de busca deste nó.
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * @param depth A profundidade na árvore de busca deste nó.
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	/**
	 * @return Um lista com todos os nós que podem ser obtidos a partir
	 * das possíveis ações do agente sobre o estado do nó atual. 
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
