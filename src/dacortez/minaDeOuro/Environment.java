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
 * Representa o ambiente da mina, contendo um mapa com suas posi��es 
 * livres, obstru�das e com pepitas de ouro. Possui m�todos que permitem 
 * ao agente decidir como se mover ou pegar ouro na mina. 
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class Environment {
	// A dimens�o da mina.
	private short size;
	// O mapa da mina representado por uma matriz de caracteres.
	private char[][] mine;
	// A lista com as posi��es das pepitas de ouro da mina.
	private List<Position> goldPositions;
	
	
	/**
	 * @return A dimens�o da mina.
	 */
	public short getSize() {
		return size;
	}
	
	/**
	 * @return A lista com a posi��o de todas as pepitas de ouro
	 * da mina na situa��o inicial.
	 */
	public List<Position> getGoldPositions() {
		return goldPositions;
	}
	
	/**
	 * @param size A dimens�o da mina (n�mero de linhas ou colunas do mapa).
	 */
	public Environment(short size) {
		this.size = size;
		mine = new char[size][size];
		goldPositions = new ArrayList<Position>();
	}
	
	/**
	 * Atribu� o conte�do content na posi��o position da mina.
	 * @param position A posi��o da mina onde se deseja atribuir o cont�do.
	 * @param content O conte�do (0, 1, ou *) a ser atribu�do na posi��o.
	 */
	public void setMineContent(Position position, char content) {
		int row = position.getRow();
		int col = position.getCol();
		mine[row][col] = content;
		if (mine[row][col] == '*')
			goldPositions.add(position);
	}
	
	/**
	 * @return O n�mero total de pepitas de ouro da mina na situa��o inicial. 
	 */
	public int getTotalGold() {
		return goldPositions.size();
	}
	
	/**
	 * @param position Posi��o atual na mina.
	 * @return true se � poss�vel se mover para direita a partir de position,
	 * false caso contr�rio.
	 */
	public boolean canMoveRight(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (col + 1 < size && mine[row][col + 1] != '1');
	}
	
	/**
	 * @param position Posi��o atual na mina.
	 * @return true se � poss�vel se mover para esquerda a partir de position,
	 * false caso contr�rio.
	 */
	public boolean canMoveLeft(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (col > 0 && mine[row][col - 1] != '1');
	}
	
	/**
	 * @param position Posi��o atual na mina.
	 * @return true se � poss�vel se mover para cima a partir de position,
	 * false caso contr�rio.
	 */
	public boolean canMoveUp(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (row > 0 && mine[row - 1][col] != '1');
	}
	
	/**
	 * @param position Posi��o atual na mina.
	 * @return true se � poss�vel se mover para baixo a partir de position,
	 * false caso contr�rio.
	 */
	public boolean canMoveDown(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (row + 1 < size && mine[row + 1][col] != '1');
	}
	
	/**
	 * @param position Posi��o atual na mina.
	 * @return true se existe uma pepita de ouro em position, false caso contr�rio.
	 */
	public boolean isThereGold(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return mine[row][col] == '*';
	}
	
	/**
	 * Avalia a performance do agente ao tomar a a��o action. 
	 * Caso a a��o seja pegar ouro, retorna 4n, onde n � a dimens�o 
	 * da mina. Caso contr�rio, retorna -1. 
	 * @param action A a��o tomada pelo agente.
	 * @return O custo associado � a��o action.
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
