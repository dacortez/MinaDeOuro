/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

/**
 * Define as ações que o agente pode executar na mina.
 * RIGHT: mover para direita.
 *  LEFT: mover para esquerda.
 *  DOWN: mover para baixo.
 *  PICK: pegar ouro.
 *  
 * @author dacortez
 * @version 2013.09.26
 */
public enum Action {
	RIGHT("D"), LEFT("E"), UP("C"), DOWN("B"), PICK("P");
	
	// String descritiva de cada ação.
	private final String value;

    /**
     * @param value String descritiva da ação para ser utlizada 
     * na impressão da solução.
     */
    Action(String value) {
        this.value = value;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return value;
    }
}
