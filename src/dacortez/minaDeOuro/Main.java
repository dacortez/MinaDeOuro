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
	private static Environment environment;
	
	public static Environment getEnvironment() {
		return environment;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2 || !args[1].matches("L|P|A")) {
			System.out.println("Uso: java Main <arquivo_de_entrada> <tipo_de_busca>");
			System.out.println("Tipo de busca: L para largura, P para profundidada, A para A*");
			return;
		}
		setEnvironment(args[0]);
		if (environment != null) {
			System.out.println(environment);
			Agent agent = new Agent(SearchMethod.Breadth, new Position(0, 0));
			agent.searh();
		}
	}
	
	private static void setEnvironment(String file) {
		try	{
			tryToSetEnvironment(file);
			return;
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
		environment = null;
	}

	private static void tryToSetEnvironment(String file) 
			throws FileNotFoundException, IOException, Exception {
		BufferedReader input = new BufferedReader(new FileReader(file));
		String line = input.readLine();
		short size = Short.parseShort(line);
		environment = new Environment(size);
		for (short row = 0; row < size; row++) {
			line = input.readLine();
			for (short col = 0; col < size; col++) {
				char content = line.charAt(col);
				environment.setMineContent(new Position(row, col), content);
			}
		}
		input.close();
	}
}