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
	private static int size;
	private char[][] mine;
	private int x, y;
	
	public static int getSize() {
		return size;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public State(int size) {
		State.size = size;
		mine = new char[size][size];
	}
	
	public State(char mine[][]) {
		this.mine = new char[size][size];
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				this.mine[i][j] = mine[i][j];
	}
	
	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setContent(int x, int y, char content) {
		mine[y][x] = content;
	}
	
	public List<ActionState> getSuccessors() {
		List<ActionState> list = new ArrayList<ActionState>();
		addRight(list);
		addLeft(list);
		addUp(list);
		addDown(list);		
		addPick(list);
		return list;
	}
	
	private void addRight(List<ActionState> list) {
		if (x < size - 1 && mine[y][x + 1] != '1') {
			State state = new State(mine);
			state.setPosition(x + 1, y);
			list.add(new ActionState(Actions.Right, state));
		}
	}

	private void addLeft(List<ActionState> list) {
		if (x > 0 && mine[y][x - 1] != '1') {
			State state = new State(mine);
			state.setPosition(x - 1, y);
			list.add(new ActionState(Actions.Left, state));
		}
	}

	private void addUp(List<ActionState> list) {
		if (y > 0 && mine[y - 1][x] != '1') {
			State state = new State(mine);
			state.setPosition(x, y - 1);
			list.add(new ActionState(Actions.Up, state));
		}
	}

	private void addDown(List<ActionState> list) {
		if (y < size - 1 && mine[y + 1][x] != '1') {
			State state = new State(mine);
			state.setPosition(x, y + 1);
			list.add(new ActionState(Actions.Down, state));
		}
	}
	
	private void addPick(List<ActionState> list) {
		if (mine[y][x] == '*') {
			State state = new State(mine);
			state.setContent(x, y, '0');
			state.setPosition(x, y);
			list.add(new ActionState(Actions.Pick, state));
		}
	}
	
	public boolean isThereGold() {
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (mine[i][j] == '*')
					return true;
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + x + ", " + y + ")\n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++)
				sb.append(mine[i][j]);
			sb.append('\n');
		}
		return sb.toString();
	}
}
