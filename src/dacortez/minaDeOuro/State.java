/**
 * MAC0425 - Intelig�ncia Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o estado onde se encontra o agente em sua busca.
 * O estado � representado pela posi��o do agente e uma lista
 * das posi��es das pepitas de ouro recolhidas.
 *  
 * @author dacortez
 * @version 2013.09.26
 */
public class State {
	// Posi��o atual do agente na mina.
	private Position position;
	// Posi��es das pepitas de ouro recolhidas.
	private List<Position> picked;
		
	/**
	 * @return A posi��o do agente na mina.
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * @return A lista das posi��es das pepitas de ouro recolhidas.
	 */
	public List<Position> getPicked() {
		return picked;
	}
	
	/**.
	 * @param position A posi��o do agente na mina.
	 */
	public State(Position position) {
		this.position = position;
		this.picked = new ArrayList<Position>();
	}
	
	/**
	 * @param position A posi��o do agente na mina.
	 * @param picked A lista das posi��es das pepitas de ouro recolhidas.
	 */
	public State(Position position, List<Position> picked) {
		this.position = position;
		this.picked = picked;
	}
	
	/**
	 * @return O n�mero de pepitas de ouro recolhidas pelo agente no estado.
	 */
	public int getTotalPicked() {
		return picked.size();
	}
	
	/**
	 * Fun��o sucessora para o estado atual.
	 * @return Lista de pares (estado, a��o) que podem ser gerados a partir
	 * do estado atual.
	 */
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
	
	/**
	 * Se for poss�vel, adiciona � lista um novo par (estado, a��o) 
	 * correspondente a a��o de pegar ouro a partir do estado atual.
	 * @param list Lista atual de pares (estado, a��o) sendo atualizada.
	 */
	private void addStateForPickAction(List<ActionState> list) {
		if (Main.getEnvironment().isThereGold(position))
			if (!goldAlreadyPicked()) {
				State state = this.clone();
				state.getPicked().add(position);
				list.add(new ActionState(Action.PICK, state));
			}
	}
	
	/**
	 * Verifica se o agente j� pegou o ouro em sua posi��o atual.
	 * @return true se a pepita de ouro na posi��o atual do agente j� foi
	 * coletada, false caso contr�rio.
	 */
	private boolean goldAlreadyPicked() {
		return picked.contains(position);
	}
	
	/**
	 * Se for poss�vel, adiciona � lista um novo par (estado, a��o) 
	 * correspondente a a��o de mover para direita a partir do estado atual.
	 * @param list Lista atual de pares (estado, a��o) sendo atualizada.
	 */
	private void addStateForRightAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveRight(position)) {
			State state = this.clone();
			state.getPosition().moveRight();
			list.add(new ActionState(Action.RIGHT, state));
		}
	}

	/**
	 * Se for poss�vel, adiciona � lista um novo par (estado, a��o) 
	 * correspondente a a��o de mover para esquerda a partir do estado atual.
	 * @param list Lista atual de pares (estado, a��o) sendo atualizada.
	 */
	private void addStateForLeftAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveLeft(position)) {
			State state = this.clone();
			state.getPosition().moveLeft();
			list.add(new ActionState(Action.LEFT, state));
		}
	}

	/**
	 * Se for poss�vel, adiciona � lista um novo par (estado, a��o) 
	 * correspondente a a��o de mover para cima a partir do estado atual.
	 * @param list Lista atual de pares (estado, a��o) sendo atualizada.
	 */
	private void addStateForUpAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveUp(position)) {
			State state = this.clone();
			state.getPosition().moveUp();
			list.add(new ActionState(Action.UP, state));
		}
	}

	/**
	 * Se for poss�vel, adiciona � lista um novo par (estado, a��o) 
	 * correspondente a a��o de mover para baixo a partir do estado atual.
	 * @param list Lista atual de pares (estado, a��o) sendo atualizada.
	 */
	private void addStateForDownAction(List<ActionState> list) {
		if (Main.getEnvironment().canMoveDown(position)) {
			State state = this.clone();
			state.getPosition().moveDown();
			list.add(new ActionState(Action.DOWN, state));
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
