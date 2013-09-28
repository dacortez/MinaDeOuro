/**
 * MAC0425 - Intelig�ncia Artificial
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
 * Este agente implementa o m�todo de busca A*.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class AStarAgent extends Agent {

	/**
	 * @param startPosition Define a posi��o do agente na mina e a 
	 * posi��o para onde ele deve voltar no final da sua explora��o.
	 */
	public AStarAgent(Position startPosition) {
		super(startPosition);
	}

	/**
	 * @return O objeto Solution correspondente a melhor solu��o encontrada 
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
	 * @return Uma fila de prioridade de n�s que utiliza como crit�rio de 
	 * compara��o o valor da fun��o f(n) = g(n) + h(n) avaliuada no n� n.
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
	 * @param node O n� da �rvore de busca sobre o qual deseja avaliar a fun��o f.
	 * @return O valor da fun��o f aplicada ao n�.
	 */
	private int getF(Node node) {
		return node.getPathCost() + getH(node);
	}
	
	/**
	 * @param node O n� da �rvore de busca sobre o qual deseja avaliar a fun��o h.
	 * @return O valor da fun��o h aplicada ao n�.
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
