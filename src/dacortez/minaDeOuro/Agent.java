/**
 * MAC0425 - Intelig�ncia Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.List;
import java.util.ArrayList;

/**
 * O agente � respons�vel por efetuar o procedimento de busca adequado
 * na mina. O m�todo de busca da solu��o getSolution() � abstrato e deve
 * ser implementado pelasinst�ncias concreta do agente que deriva desta 
 * classe. A estrutura comum a todos esses agentes, entretanto, est�o 
 * presentes nesta classe base.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public abstract class Agent {
	// Armazena a posi��o inicial do agente e onde ele deve encerrar a busca.
	protected Position startPosition;
	// Lista dos estados j� visitados pelo agente.
	protected List<State> closed;
	// N� raiz da �rvore de busca gerada.
	protected Node root;
	// N�mero de pepitas a serem coletadas como objetivo de busca.
	protected int goldGoal;
	
	/**
	 * @param startPosition Define a posi��o do agente na mina e a 
	 * posi��o para onde ele deve voltar no final da sua explora��o. 
	 */
	public Agent(Position startPosition) {
		this.startPosition = startPosition;
		closed = new ArrayList<State>();
		setRoot();
	}
	
	/**
	 * Inicializa o n� raiz da �rvore de busca a partir do estado onde
	 * o agente se encontra na posi��o inicial.
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
	 * A partir da estrat�gia de busca do agente, explora a mina tentando
	 * coletar 1, 2, ..., todas as pepitas da mina, retornando a melhor
	 * solu��o poss�vel.
	 * @return O objeto Solution correspondente a melhor solu��o encontrada
	 * pelo agente de acordo com a sua estrat�gia de busca.
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
	 * @return O objeto Solution correspondente a melhor solu��o encontrada 
	 * pelo agente de acordo com a sua estrat�gia de busca, considerando um
	 * valor �nico de pepitas a serem coletadas armazenado na vari�vel 
	 * goldGoal.
	 */
	protected abstract Solution getSolution();
	
	/**
	 * Testa se o estado atual do agente � o estado objetivo. O estado objetivo � aquele
	 * em que o agente retorna � sua posi��o inicial ap�s ter coletado o n�mero correto
	 * de pepitas (vari�vel goldGoal).
	 * @param state O estado atual do agente.
	 * @return true se o estado atual do agente � o estado objetivo, false caso contr�rio.
	 */
	protected boolean goalTest(State state) {
		if (state.getTotalPicked() == goldGoal)
			return startPosition.equals(state.getPosition());
		return false;
	}
}
