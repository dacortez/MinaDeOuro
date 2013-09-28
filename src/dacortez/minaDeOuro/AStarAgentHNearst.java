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
 * Concretiza a classe AStarAgent utilizando uma heurística que encontra
 * as pepitas mais próximas do agente, uma a uma, e depois retorna 
 * à posição inicial.
 * 
 * @author dacortez
 * @version 2013.09.27
 */
public class AStarAgentHNearst extends AStarAgent {

	/**
	 * @param startPosition Define a posição do agente na mina e a 
	 * posição para onde ele deve voltar no final da sua exploração.
	 */
	public AStarAgentHNearst(Position startPosition) {
		super(startPosition);
	}
	
	/**
	 * Esta heurística avalia a distância do agente até a pepita mais próxima,
	 * depois a partir dessa posição para a póxima mais perto e assim por diante
	 * até visitar todas as pepitas necessárias. Por fim avalia a distância
	 * da última pepita visitada até a posição de início. Retorna os pontos
	 * obtidos pela coleta das pepitas menos a distância percorrida.
	 * @see dacortez.minaDeOuro.AStarAgent#getH(dacortez.minaDeOuro.Node)
	 */
	@Override
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