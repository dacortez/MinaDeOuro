/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa o ambiente da mina, contendo um mapa com suas posições 
 * livres, obstruídas e com pepitas de ouro. Possui métodos que permitem 
 * ao agente decidir como se mover ou pegar ouro na mina. 
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class Environment {
	// A dimensão da mina.
	private short size;
	// O mapa da mina representado por uma matriz de caracteres.
	private char[][] mine;
	// A lista com as posições das pepitas de ouro da mina.
	private List<Position> goldPositions;
	
	
	/**
	 * @return A dimensão da mina.
	 */
	public short getSize() {
		return size;
	}
	
	/**
	 * @return A lista com a posição de todas as pepitas de ouro
	 * da mina na situação inicial.
	 */
	public List<Position> getGoldPositions() {
		return goldPositions;
	}
	
	/**
	 * @param size A dimensão da mina (número de linhas ou colunas do mapa).
	 */
	public Environment(short size) {
		this.size = size;
		mine = new char[size][size];
		goldPositions = new ArrayList<Position>();
	}
	
	/**
	 * Atribuí o conteúdo content na posição position da mina.
	 * @param position A posição da mina onde se deseja atribuir o contúdo.
	 * @param content O conteúdo (0, 1, ou *) a ser atribuído na posição.
	 */
	public void setMineContent(Position position, char content) {
		int row = position.getRow();
		int col = position.getCol();
		mine[row][col] = content;
		if (mine[row][col] == '*')
			goldPositions.add(position);
	}
	
	/**
	 * @return O número total de pepitas de ouro da mina na situação inicial. 
	 */
	public int getTotalGold() {
		return goldPositions.size();
	}
	
	/**
	 * @param position Posição atual na mina.
	 * @return true se é possível se mover para direita a partir de position,
	 * false caso contrário.
	 */
	public boolean canMoveRight(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (col + 1 < size && mine[row][col + 1] != '1');
	}
	
	/**
	 * @param position Posição atual na mina.
	 * @return true se é possível se mover para esquerda a partir de position,
	 * false caso contrário.
	 */
	public boolean canMoveLeft(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (col > 0 && mine[row][col - 1] != '1');
	}
	
	/**
	 * @param position Posição atual na mina.
	 * @return true se é possível se mover para cima a partir de position,
	 * false caso contrário.
	 */
	public boolean canMoveUp(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (row > 0 && mine[row - 1][col] != '1');
	}
	
	/**
	 * @param position Posição atual na mina.
	 * @return true se é possível se mover para baixo a partir de position,
	 * false caso contrário.
	 */
	public boolean canMoveDown(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (row + 1 < size && mine[row + 1][col] != '1');
	}
	
	/**
	 * @param position Posição atual na mina.
	 * @return true se existe uma pepita de ouro em position, false caso contrário.
	 */
	public boolean isThereGold(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return mine[row][col] == '*';
	}
	
	/**
	 * Avalia a performance do agente ao tomar a ação action. 
	 * Caso a ação seja pegar ouro, retorna 4n, onde n é a dimensão 
	 * da mina. Caso contrário, retorna -1. 
	 * @param action A ação tomada pelo agente.
	 * @return O custo associado à ação action.
	 */
	public int performanceMeasurement(Action action) {
		if (action == Action.PICK)
			return 4 * size;
		return -1;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (short row = 0; row < size; row++) {
			for (short col = 0; col < size; col++)
				sb.append(mine[row][col]);
			sb.append('\n');
		}
		return sb.toString();
	}
}
