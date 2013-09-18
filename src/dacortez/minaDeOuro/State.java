/**
 * 
 */
package dacortez.minaDeOuro;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dacortez
 *
 */
public class State {
	private Position position;
	private List<Position> picked;
		
	public Position getPosition() {
		return position;
	}
	
	public List<Position> getPicked() {
		return picked;
	}
	
	public State(Position position) {
		this.position = position;
		this.picked = new ArrayList<Position>();
	}
	
	public State(Position position, List<Position> picked) {
		this.position = position;
		this.picked = picked;
	}
	
	public int getTotalPicked() {
		return picked.size();
	}
	
	public List<ActionState> getSuccessors() {
		List<ActionState> list = new ArrayList<ActionState>();
		addStateForPickAction(list);
		if (list.size() == 0) {
			addStateForRightAction(list);
			addStateForLeftAction(list);
			addStateForUpAction(list);
			addStateForDownAction(list);
		}
		return list;
	}
	
	private void addStateForPickAction(List<ActionState> list) {
		if (Main.getEnvironment().isThereGold(position))
			if (!goldAlreadyPicked()) {
				State state = this.clone();
				state.getPicked().add(position);
				list.add(new ActionState(Action.PICK, state));
			}
	}
	
	private boolean goldAlreadyPicked() {
		return picked.contains(position);
	}
	
	private void addStateForRightAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveRight(position)) {
			State state = this.clone();
			state.getPosition().moveRight();
			list.add(new ActionState(Action.RIGHT, state));
		}
	}

	private void addStateForLeftAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveLeft(position)) {
			State state = this.clone();
			state.getPosition().moveLeft();
			list.add(new ActionState(Action.LEFT, state));
		}
	}

	private void addStateForUpAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveUp(position)) {
			State state = this.clone();
			state.getPosition().moveUp();
			list.add(new ActionState(Action.UP, state));
		}
	}

	private void addStateForDownAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveDown(position)) {
			State state = this.clone();
			state.getPosition().moveDown();
			list.add(new ActionState(Action.DOWN, state));
		}
	}
	
	@Override
	public State clone() {
		return new State(position.clone(), new ArrayList<Position>(picked));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((picked == null) ? 0 : picked.hashCode());
		result = prime * result + ((position == null) ? 0 : position.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof State))
			return false;
		State other = (State) obj;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		if (picked == null) {
			if (other.picked != null)
				return false;
		} else if (picked.size() != other.picked.size()) {
			return false;
		} else {
			for (Position gold: picked)
				if (!other.picked.contains(gold))
					return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(position).append('\n');
		if (picked.size() > 0) {
			sb.append("[");
			for (Position pickedPosition: picked)
				sb.append(pickedPosition).append(", ");
			sb.replace(sb.length() - 2, sb.length(), "]\n");
		}
		return sb.toString();
	}
}
