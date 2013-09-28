/**
 * MAC0425 - Inteligência Artificial
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
 * instância o tipo de agente escolhido. O objeto Environment pode ser 
 * acessado estaticamente pois é único ao longo da vida do agente. 
 * Os tipos de agente que podem ser instanciados efetuam busca em 
 * largura limitada, busca em profundidade, busca A* e busca 
 * uniforme.
 * 
 * @author dacortez
 * @version 2013.09.27
 */
public class Main {
	// Variável contendo o mapa da mina.
	private static Environment environment;
	
	
	/**
	 * @return O ambiente criado a partir do arquivo de entrada.
	 */
	public static Environment getEnvironment() {
		return environment;
	}
	
	/**
	 * Cria o ambiente, instancializa o agente apropriadao e efetua 
	 * a busca da melhor solução para o problema.
	 * @param args Argumentos passados na linha de comando. O primeiro 
	 * parâmetro deve ser o arquivo de entrada com a descrição da mina 
	 * e o segundo deve ser o tipo do agente, sendo 'L' para largura, 
	 * 'P' para profundidade, 'A' para A* e 'U' para uniforme. 
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
					System.out.println("Agente não definido.");
			}
		}
	}
	
	
	/**
	 * Verifica se os argumentos foram passados corretamente.
	 * @param args Argumentos passados na linha de comando.
	 * @return true se argumentos passados corretamente, false caso contrário.
	 */
	private static boolean correctArgs(String args[]) {
		if (args.length < 2 || !args[1].matches("L|P|A|U")) {
			System.out.println("Uso: java -jar MinaDeOuro.jar <arquivo_de_entrada> <tipo_de_busca>");
			System.out.println("'L' para largura, 'P' para profundidada, 'A' para A*, 'U' para uniforme");
			return false;
		}
		return true;
	}

	/**
	 * @param type Indica o tipo de agente que deve ser instanciado. 
	 * 'L' para largura, 'P' para profundidade, 'A' para A*, ou 'U' para uniforme. 
	 * @return A instância solicitada para o tipo de agente. Nulo caso
	 * o tipo seja inválido.
	 */
	private static Agent getAgent(char type) {
		switch (type) {
		case 'P':
			return new LimitedDeapthAgent(new Position(0, 0));
		case 'L':
			return new BreadthAgent(new Position(0, 0));
		case 'A':
			return new AStarAgentHNearst(new Position(0, 0));
		case 'U':
			return new AStarAgentHZero(new Position(0, 0));
		}
		return null;
	}
	
	/**
	 * Utiliza o agente para buscar e exibir a melhor a solução encontrada
	 * para o problema. Imprime a pontuação, o número de pepitas coletadas,
	 * as ações a serem tomadas e tempo de processamento para encontrar a
	 * solução.
	 * @param agent O agente que efetuará a busca da melhor solução.
	 */
	private static void searchAndShowSolution(Agent agent) {
		long ti = System.currentTimeMillis();
		Solution solution = agent.search();
		long tf = System.currentTimeMillis();
		System.out.println(solution);
		System.out.println("Tempo de processamento = " + (tf - ti) / 1000.0 + " s");
	}
	
	/**
	 * Inicializa a variável de ambiente a partir do arquivo de entrada.
	 * Mantém ela nula caso ocorra algum erro.
	 * @param file Arquivo de entrada com o mapa da mina de acordo com 
	 * as especificações do EP.
	 */
	private static void setEnvironment(String file) {
		try	{
			tryToSetEnvironment(file);
			return;
		}
		catch (FileNotFoundException ex) {
			System.err.println("Arquivo de entrada não encontrado.");
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
	 * as especificações do EP.
	 * @throws FileNotFoundException Arquivo não encontrado.
	 * @throws IOException Erro na leitura do arquivo.
	 * @throws Exception Arquivo de entrada com formatação diferente da especificação.
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
					throw new Exception("Largura da mina incompatível com seu tamanho.");
				}
				char content = line.charAt(col);
				if (content != '0' && content != '1' && content != '*') {
					input.close();
					throw new Exception("Conteúdo da mina não identificado.");
				}
				environment.setMineContent(new Position(row, col), content);
			}
		}
		input.close();
	}
}