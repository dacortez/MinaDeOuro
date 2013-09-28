/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

/**
 * Este agente implementa o método de busca em profundidade limitada.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class LimitedDepthAgent extends Agent {
	// Profundidade limite da busca.
	private static final int MAX_LIMIT = 150;
	
	/**
	 * @param startPosition Define a posição do agente na mina e a 
	 * posição para onde ele deve voltar no final da sua exploração.
	 */
	public LimitedDepthAgent(Position startPosition) {
		super(startPosition);
	}

	/**
	 * @return O objeto Solution correspondente a melhor solução encontrada 
	 * pelo agente de acordo com a busca em profundidade limitada.
	 * @see dacortez.minaDeOuro.Agent#getSolution()
	 */
	@Override
	protected Solution getSolution() {
		closed.clear();
		return recursiveDLS(root, MAX_LIMIT);
	}
	
	/**
	 * @param node Nó a partir do qual se faz a busca.
	 * @param limit Valor da profundidade limite da busca. 
	 * @return O objeto Solution correspondente a melhor solução encontrada 
	 * (pode ser do tipo Cutoff caso o limite seja atingido).
	 */
	private Solution recursiveDLS(Node node, int limit) {
		closed.add(node.getState());
		boolean cutoffOccured = false;
		if (goalTest(node.getState())) return new Solution(node);
		if (node.getDepth() == limit) return new Cutoff();
		for (Node child: node.expand())
			if (!closed.contains(child.getState())) {
				Solution result = recursiveDLS(child, limit);
				if (result instanceof Cutoff)
					cutoffOccured = true;
				else if (result != null)
					return result;
			}
		if (cutoffOccured) return new Cutoff();
		return null;
	}
}
