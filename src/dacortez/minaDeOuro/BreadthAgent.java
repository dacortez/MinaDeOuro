/**
 * MAC0425 - Intelig�ncia Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Este agente implementa o m�todo de busca em largura.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class BreadthAgent extends Agent {
	
	/**
	 * @param startPosition Define a posi��o do agente na mina e a 
	 * posi��o para onde ele deve voltar no final da sua explora��o.
	 */
	public BreadthAgent(Position startPosition) {
		super(startPosition);
	}
	
	/**
	 * @return O objeto Solution correspondente a melhor solu��o encontrada 
	 * pelo agente de acordo com a busca em largura.
	 * @see dacortez.minaDeOuro.Agent#getSolution()
	 */
	@Override
	protected Solution getSolution() {
		closed.clear();
		Queue<Node> queue = new LinkedList<Node>();
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
}