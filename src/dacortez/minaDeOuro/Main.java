/**
 * MAC0425 - Intelig�ncia Artificial
 * EP1 - Mina de Ouro
 * 
 * Daniel Augusto Cortez - 2960291
 */
package dacortez.minaDeOuro;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Cria o objeto Enviroment apropriado a partir do arquivo de entrada e
 * inst�ncia o tipo de agente escolhido. O objeto Environment pode ser 
 * acessado estaticamente pois � �nico ao longo da vida do agente. 
 * Os tipos de agente que podem ser instanciados efetuam busca em 
 * largura limitada, busca em profundidade, ou busca A*.
 * 
 * @author dacortez
 * @version 2013.09.27
 */
public class Main {
	// Vari�vel contendo o mapa da mina.
	private static Environment environment;
	
	
	/**
	 * @return O ambiente criado a partir do arquivo de entrada.
	 */
	public static Environment getEnvironment() {
		return environment;
	}
	
	/**
	 * Cria o ambiente, instancializa o agente apropriadao e efetua 
	 * a busca da melhor solu��o para o problema.
	 * @param args Argumentos passados na linha de comando. O primeiro 
	 * par�metro deve ser o arquivo de entrada com a descri��o da mina 
	 * e o segundo deve ser o tipo do agente, sendo 'L' para largura, 
	 * 'P' para profundidade e 'A' para A*. 
	 */
	public static void main(String[] args) {
		if (correctArgs(args)) {
			setEnvironment(args[0]);
			if (environment != null) {
				System.out.println(environment);
				Agent agent = getAgent(args[1].charAt(0));
				if (agent != null)
					searchAndShowSolution(agent);
				else
					System.out.println("Agente n�o definido.");
			}
		}
	}
	
	
	/**
	 * Verifica se os argumentos foram passados corretamente.
	 * @param args Argumentos passados na linha de comando.
	 * @return true se argumentos passados corretamente, false caso contr�rio.
	 */
	private static boolean correctArgs(String args[]) {
		if (args.length < 2 || !args[1].matches("L|P|A")) {
			System.out.println("Uso: java -jar MinaDeOuro.jar <arquivo_de_entrada> <tipo_de_busca>");
			System.out.println("Tipo de busca: 'L' para largura, 'P' para profundidada, 'A' para A*");
			return false;
		}
		return true;
	}

	/**
	 * @param type Indica o tipo de agente que deve ser instanciado. 
	 * 'L' para largura, 'P' para profundidade, ou 'A' para A*. 
	 * @return A inst�ncia solicitada para o tipo de agente.
	 */
	private static Agent getAgent(char type) {
		switch (type) {
		case 'L':
			return new LimitedDeapthAgent(new Position(0, 0));
		case 'P':
			return new BreadthAgent(new Position(0, 0));
		case 'A':
			return new AStarAgent(new Position(0, 0));
		}
		return null;
	}
	
	/**
	 * Utiliza o agente para buscar e exibir a melhor a solu��o encontrada
	 * para o problema. Imprime a pontua��o, o n�mero de pepitas coletadas,
	 * as a��es a serem tomadas e tempo de processamento para encontrar a
	 * solu��o.
	 * @param agent O agente que efetuar� a busca da melhor solu��o.
	 */
	private static void searchAndShowSolution(Agent agent) {
		long ti = System.currentTimeMillis();
		Solution solution = agent.search();
		long tf = System.currentTimeMillis();
		System.out.println(solution);
		System.out.println("Tempo de processamento = " + (tf - ti) / 1000.0 + " s");
	}
	
	/**
	 * Inicializa a vari�vel de ambiente a partir do arquivo de entrada.
	 * Mant�m ela nula caso ocorra algum erro.
	 * @param file Arquivo de entrada com o mapa da mina de acordo com 
	 * as especifica��es do EP.
	 */
	private static void setEnvironment(String file) {
		try	{
			tryToSetEnvironment(file);
			return;
		}
		catch (FileNotFoundException ex) {
			System.err.println("Arquivo de entrada n�o encontrado.");
		}	
		catch (IOException ex) {
			System.err.println("Erro na leitura do arquivo de entrada.");
		}
		catch (Exception ex) {
			System.err.println("Arquivo de entrada mal formatado:");
			System.err.println(ex.getMessage());
		}
		environment = null;
	}

	/**
	 * @param file Arquivo de entrada com o mapa da mina de acordo com 
	 * as especifica��es do EP.
	 * @throws FileNotFoundException Arquivo n�o encontrado.
	 * @throws IOException Erro na leitura do arquivo.
	 * @throws Exception Arquivo de entrada com formata��o diferente da especifica��o.
	 */
	private static void tryToSetEnvironment(String file) 
			throws FileNotFoundException, IOException, Exception {
		BufferedReader input = new BufferedReader(new FileReader(file));
		String line = input.readLine();
		short size = Short.parseShort(line);
		if (size > 50) {
			input.close();
			throw new Exception("Tamanho da mina maior do que 50."); 
		}
		environment = new Environment(size);
		for (short row = 0; row < size; row++) {
			line = input.readLine();
			for (short col = 0; col < size; col++) {
				if (line.length() != size) {
					input.close();
					throw new Exception("Largura da mina n�o compat�vel com seu tamanho.");
				}
				char content = line.charAt(col);
				if (content != '0' && content != '1' && content != '*') {
					input.close();
					throw new Exception("Conte�do da mina n�o identificado.");
				}
				environment.setMineContent(new Position(row, col), content);
			}
		}
		input.close();
	}
}