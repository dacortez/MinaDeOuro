/**
 * 
 */
package dacortez.minaDeOuro;

/**
 * @author dacortez
 *
 */
public enum Action {
	RIGHT("D"), LEFT("E"), UP("C"), DOWN("B"), PICK("P");
	
	private final String value;

    Action(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
