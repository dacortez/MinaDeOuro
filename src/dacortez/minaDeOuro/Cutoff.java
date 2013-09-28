/**
 * MAC0425 - Inteligência Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

/**
 * Esta classe extende Solution apenas para ser utilizada no método
 * de busca em profundidade limitada para indicar que o limite da 
 * busca foi atingido, porém a solução procurada não foi encontrada.
 *  
 * @author dacortez
 * @version 2013.09.26
 */
public class Cutoff extends Solution {
	
	/* (non-Javadoc)
	 * @see dacortez.minaDeOuro.Solution#toString()
	 */
	@Override
	public String toString() {
		return "Cutoff!";
	}
}
