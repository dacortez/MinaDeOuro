/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

/**
 * Representa um par (action, state), onde o estado state é atingido
 * após a execução da ação action.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class ActionState {
	// Variável com a ação.
	private Action action;
	// Variável com o estado resultade da ação.
	private State state;
	
	/**
	 * @return A ação do par (action, state).
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @return O estado do par (action, state).
	 */
	public State getState() {
		return state;
	}

	/**
	 * @param action A ação do par (action, state).
	 * @param state O estado do par (action, state).
	 */
	public ActionState(Action action, State state) {
		this.action = action;
		this.state = state;
	}
}
