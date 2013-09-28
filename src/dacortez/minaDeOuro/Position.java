/**
 * MAC0425 - Intelig�ncia Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

/**
 * Representa uma posi��o na mina como o par ordenado (linha, coluna).
 * A posi��o (0, 0) corresponde ao canto superior esquerdo.
 * Os valores de linha crescem para baixo e os valores de coluna 
 * crescem para direita.
 * 
 * @author dacortez
 * @version 2013.09.26
 */
public class Position {
	// N�mero da linha. 
	private short row;
	// N�mero da coluna.
	private short col;

	/**
	 * @return O valor da linha.
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @param row O valor da linha.
	 */
	public void setRow(short row) {
		this.row = row;
	}

	/**
	 * @return O valor da coluna.
	 */
	public int getCol() {
		return col;
	}

	/**
	 * @param col O valor da coluna.
	 */
	public void setCol(short col) {
		this.col = col;
	}

	/**
	 * @param row O valor da linha.
	 * @param col O valor da coluna.
	 */
	public Position(int row, int col) {
		this.row = (short) row;
		this.col = (short) col;
	}

	/**
	 * Move esta posi��o para direita, incrementando o valor 
	 * da coluna em uma unidade.
	 */
	public void moveRight() {
		col++;
	}
	
	/**
	 * Move esta posi��o para esquerda, decrementando o valor 
	 * da coluna em uma unidade.
	 */
	public void moveLeft() {
		col--;
	}
	
	/**
	 * Move esta posi��o para baixo, incrementando o valor 
	 * da linha em uma unidade.
	 */
	public void moveDown() {
		row++;
	}
	
	/**
	 * Move esta posi��o para cima, incrementando o valor 
	 * da linha em uma unidade.
	 */
	public void moveUp() {
		row--;
	}
	
	
	/**
	 * Calcula a dist�ncia entre a posi��o atual e a posi��o other,
	 * sem considerar as obstru��es da mina, considerando apenas
	 * movimentos verticais e horizontais. 
	 * @param other Outra posi��o
	 * @return A dist�ncia entre a posi��o atual e a posi��o other.
	 */
	public int distTo(Position other) {
		return Math.abs(other.row - row) + Math.abs(other.col - col);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + row + "," + col + ")";
	}
}
