import java.io.IOException;
import java.util.List;

import br.com.esleysanches.tableinfile.TableInFile;

public class TestaTableInFile {

	public static void main(String[] args) throws IOException {
		
		// Uso do objeto
		TableInFile tf = new TableInFile("Contatos", ".txt", ";");
		
		// 
		// Adicionar vários registros
		//
		String[] cadastro1 = { "Esley Sanches", "meuemail@hotmail.com", "02/12/1988" };
		String[] cadastro2 = { "Mais alguém Sanches", "maisalguem@hotmail.com", "03/05/1990" };
		String[] cadastro3 = { "Zézinho da Silva", "zezinho@uol.com.br", "03/05/1990" };
		String[] cadastro4 = { "Claudio da Silva", "claudinho@uol.com.br", "08/03/1980" };
		
		tf.add(cadastro1);
		tf.add(cadastro2);
		tf.add(cadastro3);
		tf.add(cadastro4);
		System.out.println("Contatos criados com sucesso!");
		System.out.println();System.out.println();
		
		
		
		//
		// Visualizando todos registros
		//
		System.out.println("Buscando registros...");
		List<String[]> todos = tf.getAll();
		System.out.println("Encontrei " + todos.size() + " registro(s)");
		System.out.println("Abaixo segue separado por colunas");
		for (String[] s : todos) {
			// Primeira coluna o ID é automático (é o numero da linha)
			System.out.println("Id: " + s[0]);
			System.out.println("Nome: " + s[1]);
			System.out.println("E-Mail: " + s[2]);
			System.out.println("Data de nascimento: " + s[3]);
			System.out.println();
		}
		System.out.println();
		
		
		
		//
		// Buscar registro com a ID 9
		//
		System.out.println();
		String[] buscaId9 = tf.getById(9);
		
		if (buscaId9 == null) {
			System.out.println("Id 9 não encontrada!");
		}
		System.out.println();
		
		
		
		//
		// Buscar registro com a ID 2
		//
		System.out.println();
		String[] buscaId2 = tf.getById(2);
		if (buscaId2 != null) {
			System.out.println("ID: " + buscaId2[0]);
			System.out.println("Nome: " + buscaId2[1]);
			System.out.println("E-Mail: " + buscaId2[2]);
			System.out.println("Data de nascimento: " + buscaId2[3]);
		}
		System.out.println();
		
		
		//
		// Busca por string ou parte dela (Case sensitive)
		// ATENÇÃO: O método getByString retorna a primeira linha 
		// encontrada que siga o critério de busca
		//
		System.out.println();
		String[] buscaPorString = tf.getByString("Silva");
		if (buscaPorString != null) {
			System.out.println("ID: " + buscaPorString[0]);
			System.out.println("Nome: " + buscaPorString[1]);
			System.out.println("E-Mail: " + buscaPorString[2]);
			System.out.println("Data de nascimento: " + buscaPorString[3]);
		}
		System.out.println();
		
		
		//
		// Mas tem muitos silvas, quero ver todos,
		// melhor quero todos que possuem email do hotmail.com,
		// pode buscar também aqueles que nasceram em 1988 usando (/1988) 
		//
		System.out.println();
		List<String[]> buscaPorEmail = tf.find("@hotmail.com");
		if (buscaPorEmail.size() > 0) {
			System.out.println("Encontrei " + buscaPorEmail.size() + " registro(s)");
			System.out.println("Abaixo segue separado por colunas");
			for (String[] s : buscaPorEmail) {
				// Primeira coluna o ID é automático (é o numero da linha)
				System.out.println("Id: " + s[0]);
				System.out.println("Nome: " + s[1]);
				System.out.println("E-Mail: " + s[2]);
				System.out.println("Data de nascimento: " + s[3]);
				System.out.println();
			}
		}
		System.out.println();
		
		//
		// Agora chega, quero remover a primeira e segunda linha
		//
		tf.delete(new int[] { 0,1 } );
		System.out.println("Removido!");
	}
	
}
