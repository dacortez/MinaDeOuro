/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

/**
 * Concretiza a classe AStarAgent utilizando uma heurística nula,
 * o que equivale a busca uniforme. 
 * 
 * @author dacortez
 * @version 2013.09.27
 */
public class AStarAgentHZero extends AStarAgent {

	/**
	 * @param startPosition Define a posição do agente na mina e a 
	 * posição para onde ele deve voltar no final da sua exploração.
	 */
	public AStarAgentHZero(Position startPosition) {
		super(startPosition);
	}

	/**
	 * Esta heurística retorna zero, tornando o método equivalente a busca uniforme.
	 * @see dacortez.minaDeOuro.AStarAgent#getH(dacortez.minaDeOuro.Node)
	 */
	@Override
	protected int getH(Node node) {
		return 0;
	}
}
