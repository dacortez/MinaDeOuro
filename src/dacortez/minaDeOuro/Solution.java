/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.Stack;


/**
 * Representa a solução encontrada pelo agente de busca, contendo
 * o caminho total entre o nó raiz e o nó objetivo.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class Solution {
	// O nó final (objetivo) encontrado pelo agente de busca.
	private Node finalNode;
	// Uma pilha com todos os nós do caminho entre o nó raiz e o nó final.
	private Stack<Node> stack;
	
	/**
	 * @return A pilha contendo todos os nõs do caminho entre o nó raiz 
	 * e o nó final da solução.
	 */
	public Stack<Node> getStack() {
		return stack;
	}
	
	/**
	 * @param finalNode O nó final (objetivo) encontrado pelo agente de busca.
	 */
	public Solution(Node finalNode) {
		this.finalNode = finalNode;
		if (finalNode != null)
			setStack();
	}
	
	protected Solution() {
	}

	/**
	 * Cria a pilha contendo todos os nós do caminho entre o nó raiz 
	 * e o nó final da solução.
	 */
	private void setStack() {
		stack = new Stack<Node>();
		Node node = finalNode;
		while (node != null) {
			stack.push(node);
			node = node.getParentNode();
		}
	}
	
	/**
	 * @return O custo da solução (os pontos obtidos pelo agente).
	 */
	public int getCost() {
		return finalNode.getPathCost();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int totalPicked = finalNode.getState().getTotalPicked();
		sb.append(getCost()).append(" pontos (" + totalPicked + " ouros)\n");
		sb.append("Lista de ações:\n");
		if (stack.size() < 2) sb.append("[nenhuma]");
		for (int i = stack.size() - 2, j = 1; i >= 0; i--, j++) {
			Node node = stack.get(i);
			sb.append(node.getAction());
			if (j % 31 == 0) 
				sb.append('\n');
		}
		return sb.append('\n').toString();
	}
}
