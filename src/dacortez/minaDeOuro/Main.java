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
			Agent agent = new LimitedDeapthAgent(new Position(0, 0));
			//Agent agent = new BreadthAgent(new Position(0, 0));
			Solution solution = agent.search();
			if (solution != null)
				System.out.println(solution);
			else
				System.out.println("Solução não encontrada!");
		}
	}
	
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

/*
Position p1 = new Position(1, 10);
Position p2 = new Position(1, 10);
Position p3 = new Position(10, 1);

List<Position> l1 = new ArrayList<Position>();
l1.add(p1); 
l1.add(p3);

List<Position> l2 = new ArrayList<Position>();
l2.add(p3); 
l2.add(p2);

State s1 = new State(p1, l1);
State s2 = new State(p2, l2);
State s3 = s2.clone();
s3.getPosition().moveRight();

System.out.println(l1.contains(p2));
System.out.println(l2.contains(p1));
System.out.println(l1.hashCode());
System.out.println(l2.hashCode());
System.out.println(p1.hashCode());
System.out.println(p2.hashCode());
System.out.println(s1.equals(s2));
System.out.println(s3.equals(s2));
*/