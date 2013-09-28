/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.ArrayList;
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
/**
 * @author dacortez
 *
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
	 * Esta heurística avalia a distância do agente até a pepita mais próxima,
	 * depois a partir dessa posição para a póxima mais perto e assim por diante
	 * até visitar todas as pepitas necessárias. Por fim avalia a distância
	 * da última pepita visitada até a posição de início. Retorna os pontos
	 * obtidos pela coleta das pepitas menos a distância percorrida.
	 * @param node O nó da árvore de busca sobre o qual deseja avaliar a função h.
	 * @return O valor da função h aplicada ao nó.
	 */
	protected int getH(Node node) {
		Position agentPosition = node.getState().getPosition();
		List<Position> notPicked = getNotPicked(node.getState().getPicked());
		int totalPicked = node.getState().getTotalPicked();
		int goldGoal = getGoldGoal();
		int steps = 0;
		Position previous = agentPosition;
		Position next = agentPosition;
		for (int i = totalPicked + 1; i <= goldGoal; i++) {
			next = getNearst(previous, notPicked);
			steps += previous.distTo(next);
			notPicked.remove(next);
			previous = next;
		}
		steps += next.distTo(startPosition);
		return 4 * Main.getEnvironment().getSize() * (goldGoal - totalPicked) - steps; 
	}
	
	/**
	 * @param picked Lista com as posições das pepitas de ouro que o 
	 * agente já pegou.
	 * @return Lista com as posções das pepitas que o agente ainda 
	 * não pegou.
	 */
	private List<Position> getNotPicked(List<Position> picked) {
		List<Position> gold = Main.getEnvironment().getGoldPositions();
		List<Position> notPicked = new ArrayList<Position>();
		for (Position position: gold) 
			if (!picked.contains(position))
				notPicked.add(position);
		return notPicked;
	}
	
	/**
	 * @param from Posição atual a partir da qual se deseja encontrar a 
	 * posição mais póxima na lista.
	 * @param positions Lista de posições.
	 * @return A posição da lista positions que está mais próxima da 
	 * posição from.
	 */
	private Position getNearst(Position from, List<Position> positions) {
		int min = 2 * Main.getEnvironment().getSize();
		Position nearest = null;
		for (Position position: positions) {
			int dist = from.distTo(position);
			if (dist < min) {
				nearest = position;
				min = dist;
			}
		}
		return nearest;
	}
}
