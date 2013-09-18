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
				list.add(new ActionState(Action.Pick, state));
			}
	}
	
	private boolean goldAlreadyPicked() {
		for (Position pickedPosition: picked) 
			if (pickedPosition.isSame(position))
				return true;
		return false;
	}
	
	private void addStateForRightAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveRight(position)) {
			State state = this.clone();
			state.getPosition().moveRight();
			list.add(new ActionState(Action.Right, state));
		}
	}

	private void addStateForLeftAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveLeft(position)) {
			State state = this.clone();
			state.getPosition().moveLeft();
			list.add(new ActionState(Action.Left, state));
		}
	}

	private void addStateForUpAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveUp(position)) {
			State state = this.clone();
			state.getPosition().moveUp();
			list.add(new ActionState(Action.Up, state));
		}
	}

	private void addStateForDownAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveDown(position)) {
			State state = this.clone();
			state.getPosition().moveDown();
			list.add(new ActionState(Action.Down, state));
		}
	}
	
	@Override
	public State clone() {
		return new State(position.clone(), new ArrayList<Position>(picked));
	}
	
	public boolean isSame(State other) {
		if (position.isSame(other.getPosition()))
			if (getTotalPicked() == other.getTotalPicked())
				// TODO: implemtar mŽtodo equals
				return true;
		return false;
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
