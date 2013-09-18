/**
 * 
 */
package dacortez.minaDeOuro;

/**
 * @author dacortez
 *
 */
public class Environment {
	private short size;
	private char[][] mine;
	private int totalGold;
	
	public short getSize() {
		return size;
	}
	
	public char[][] getMine() {
		return mine;
	}
	
	public int getTotalGold() {
		return totalGold;
	}
	
	public Environment(short size) {
		this.size = size;
		mine = new char[size][size];
		totalGold = 0;
	}
	
	public void setMineContent(Position position, char content) {
		int row = position.getRow();
		int col = position.getCol();
		mine[row][col] = content;
		if (mine[row][col] == '*')
			totalGold++;
	}
	
	public boolean canMoveRight(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (col + 1 < size && mine[row][col + 1] != '1');
	}
	
	public boolean canMoveLeft(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (col > 0 && mine[row][col - 1] != '1');
	}
	
	public boolean canMoveUp(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (row > 0 && mine[row - 1][col] != '1');
	}
	
	public boolean canMoveDown(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return (row + 1 < size && mine[row + 1][col] != '1');
	}
	
	public boolean isThereGold(Position position) {
		int row = position.getRow();
		int col = position.getCol();
		return mine[row][col] == '*';
	}
	
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
