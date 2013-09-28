/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Este agente implementa o método de busca A*.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class AStarAgent extends Agent {

	/**
	 * @param startPosition Define a posição do agente na mina e a 
	 * posição para onde ele deve voltar no final da sua exploração.
	 */
	public AStarAgent(Position startPosition) {
		super(startPosition);
	}

	/**
	 * @return O objeto Solution correspondente a melhor solução encontrada 
	 * pelo agente de acordo com a busca A*.
	 * @see dacortez.minaDeOuro.Agent#getSolution()
	 */
	@Override
	protected Solution getSolution() {
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
	
	/**
	 * @return Uma fila de prioridade de nós que utiliza como critério de 
	 * comparação o valor da função f(n) = g(n) + h(n) avaliuada no nó n.
	 */
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
	
	/**
	 * @param node O nó da árvore de busca sobre o qual deseja avaliar a função f.
	 * @return O valor da função f aplicada ao nó.
	 */
	private int getF(Node node) {
		return node.getPathCost() + getH(node);
	}
	
	/**
	 * @param node O nó da árvore de busca sobre o qual deseja avaliar a função h.
	 * @return O valor da função h aplicada ao nó.
	 */
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
		// O caso de retorno 0 corresponde a uma busca uniforme.
		// return 0;
	}
}
