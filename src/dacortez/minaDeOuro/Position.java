/**
 * 
 */
package dacortez.minaDeOuro;

/**
 * @author dacortez
 *
 */
public class Position {
	private short row;
	private short col;

	public int getRow() {
		return row;
	}

	public void setRow(short row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(short col) {
		this.col = col;
	}

	public Position(int row, int col) {
		this.row = (short) row;
		this.col = (short) col;
	}

	public void moveRight() {
		col++;
	}
	
	public void moveLeft() {
		col--;
	}
	
	public void moveDown() {
		row++;
	}
	
	public void moveUp() {
		row--;
	}
	
	public boolean isSame(Position other) {
		return (row == other.getRow() && col == other.getCol());
	}
	
	@Override
	public Position clone() {
		return new Position(row, col);
	}
	
	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}
}
