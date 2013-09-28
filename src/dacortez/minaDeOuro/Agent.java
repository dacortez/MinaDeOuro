/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.List;
import java.util.ArrayList;

/**
 * O agente é responsável por efetuar o procedimento de busca adequado
 * na mina. O método de busca da solução getSolution() é abstrato e deve
 * ser implementado pela instância concreta do agente que deriva desta 
 * classe. A estrutura comum a todos esses agentes, entretanto, estão 
 * presentes nesta classe base.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public abstract class Agent {
	// Armazena a posição inicial do agente e onde ele deve encerrar a busca.
	protected Position startPosition;
	// Lista dos estados já visitados pelo agente.
	protected List<State> closed;
	// Nó raiz da árvore de busca gerada.
	protected Node root;
	// Número de pepitas a serem coletadas como objetivo de busca.
	private int goldGoal;
	
	/**
	 * @param startPosition Define a posição do agente na mina e a 
	 * posição para onde ele deve voltar no final da sua exploração. 
	 */
	public Agent(Position startPosition) {
		this.startPosition = startPosition;
		closed = new ArrayList<State>();
		setRoot();
	}
	
	/**
	 * @return O número de pepitas a serem coletadas como objetivo de busca.
	 */
	protected int getGoldGoal() {
		return goldGoal;
	}
	
	/**
	 * Inicializa o nó raiz da árvore de busca a partir do estado onde
	 * o agente se encontra na posição inicial.
	 */
	private void setRoot() {
		State state = new State(startPosition);
		root = new Node();
		root.setState(state);
		root.setParentNode(null);
		root.setAction(null);
		root.setPathCost(0);
		root.setDepth(0);
	}
	
	/**
	 * A partir da estratégia de busca do agente, explora a mina tentando
	 * coletar 1, 2, ..., todas as pepitas da mina, retornando a melhor
	 * solução possível.
	 * @return O objeto Solution correspondente a melhor solução encontrada
	 * pelo agente de acordo com a sua estratégia de busca.
	 */
	public Solution search() {
		Solution best = new Solution(root);
		int totalGold = Main.getEnvironment().getTotalGold();
		for (goldGoal = 1; goldGoal <= totalGold; goldGoal++) {
			Solution solution = getSolution();
			if (solution != null && !(solution instanceof Cutoff))
				if (solution.getCost() > best.getCost())
					best = solution;
		}
		return best;
	}
	
	/**
	 * @return O objeto Solution correspondente a melhor solução encontrada 
	 * pelo agente de acordo com a sua estratégia de busca, considerando o
	 * número de pepitas a serem coletadas da variável goldGoal.
	 */
	protected abstract Solution getSolution();
	
	/**
	 * Testa se o estado atual do agente é o estado objetivo. O estado objetivo é aquele
	 * em que o agente retorna a sua posição inicial após ter coletado o número correto
	 * de pepitas (variável goldGoal).
	 * @param state O estado atual do agente.
	 * @return true se o estado atual do agente é o estado objetivo, false caso contrário.
	 */
	protected boolean goalTest(State state) {
		if (state.getTotalPicked() == goldGoal)
			return startPosition.equals(state.getPosition());
		return false;
	}
}
