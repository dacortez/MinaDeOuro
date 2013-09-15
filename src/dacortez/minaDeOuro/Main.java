/**
 * 
 */
package dacortez.minaDeOuro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author dacortez
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Nome do arquivo de entrada.");
			return;
		}
		try	{
			BufferedReader input = new BufferedReader(new FileReader(args[0]));
		    String line = null;
		    while ((line = input.readLine()) != null) {
		    	System.out.println(line);
		    }
		    input.close();
		}
		catch (FileNotFoundException ex) {
			System.err.println("Arquivo de entrada '" + args[0] + "' n‹o encontrado.");
		}	
		catch (IOException ex) {
			System.err.println("Erro na leitura do arquivo de entrada: " + ex.getMessage());
		}
	}

}
