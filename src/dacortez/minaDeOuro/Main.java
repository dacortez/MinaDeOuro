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
		if (args.length < 2 || !args[1].matches("L|P|A")) {
			System.out.println("Uso: java Main <arquivo_de_entrada> <tipo_de_busca>");
			System.out.println("Tipo de busca: L para largura, P para profundidada, A para A*");
			return;
		}
		Main main = new Main();
		State initialState = main.getInitialState(args[0]);
		if (initialState != null) {
			Agent agent = new Agent(initialState);
			agent.breadthSearh();
		}
	}
	
	private State getInitialState(String inputFile) {
		try	{
			return tryToGetInitialState(inputFile);
		}
		catch (FileNotFoundException ex) {
			System.err.println("Arquivo de entrada n‹o encontrado.");
		}	
		catch (IOException ex) {
			System.err.println("Erro na leitura do arquivo de entrada.");
		}
		catch (Exception ex) {
			System.err.println("Arquivo de entrada mal formatado.");
		}
		return null;
	}

	private State tryToGetInitialState(String inputFile) 
			throws FileNotFoundException, IOException, Exception {
		BufferedReader input = new BufferedReader(new FileReader(inputFile));
		String line = input.readLine();
		int size = Integer.parseInt(line);
		State state = new State(size);
		state.setPosition(0, 0);
		for (int y = 0; y < size; y++) {
			line = input.readLine();
			for (int x = 0; x < size; x++) {
				char content = line.charAt(x);
				state.setContent(x, y, content);
			}
		}
		input.close();
		return state;
	}

}