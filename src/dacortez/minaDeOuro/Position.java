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
	
	public int distTo(Position other) {
		return Math.abs(other.row - row) + Math.abs(other.col - col);
	}
	
	@Override
	public Position clone() {
		return new Position(row, col);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + col;
		result = prime * result + row;
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
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (col != other.col)
			return false;
		if (row != other.row)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}
}
