/**
 * MAC0425 - Intelig�ncia Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

/**
 * Representa um par (action, state), onde o estado state � atingido
 * ap�s a execu��o da a��o action.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class ActionState {
	// Vari�vel com a a��o.
	private Action action;
	// Vari�vel com o estado resultade da a��o.
	private State state;
	
	/**
	 * @return A a��o do par (action, state).
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
	 * @param action A a��o do par (action, state)
	 * @param state O estado do par (action, state)
	 */
	public ActionState(Action action, State state) {
		this.action = action;
		this.state = state;
	}
}
