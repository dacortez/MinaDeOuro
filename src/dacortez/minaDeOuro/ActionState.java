package dacortez.minaDeOuro;

public class ActionState {
	private Actions action;
	private State state;
	
	public Actions getAction() {
		return action;
	}

	public State getState() {
		return state;
	}

	public ActionState(Actions action, State state) {
		this.action = action;
		this.state = state;
	}
}
