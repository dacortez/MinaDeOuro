package dacortez.minaDeOuro;

public class ActionState {
	private Action action;
	private State state;
	
	public Action getAction() {
		return action;
	}

	public State getState() {
		return state;
	}

	public ActionState(Action action, State state) {
		this.action = action;
		this.state = state;
	}
}
