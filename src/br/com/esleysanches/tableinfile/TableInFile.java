package br.com.esleysanches.tableinfile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.esleysanches.tableinfile.uncheckedexceptions.CriteriaEmptyOrNullException;
import br.com.esleysanches.tableinfile.uncheckedexceptions.EmptyRowException;
import br.com.esleysanches.tableinfile.uncheckedexceptions.ZeroIdException;

/**
 * Essa � a classe principal. A classe {@code TableInFile} � respons�vel por
 * simular uma tabela com ilimitadas colunas em um arquivo de texto. Com ela
 * voc� pode criar um arquivo em texto puro dividir as colunas atrav�s de um
 * caracter ou palavra delimitadora, fazer consultas simples fazer remo��o
 * simples ou com diversos id's
 * 
 * @author Esley Sanches
 * @version 1.0
 */
public class TableInFile {

	/** Determina o nome da tabela (mesmo nome do arquivo) */
	private String tableName = "";
	/** Determina qual a extens�o do arquivo */
	private String extension = ".table";
	/** Determina qual delimitador para colunas */
	private String delimiter = "#";

	/**
	 * Nesse construtor voc� determina o nome da tabela (Nome do arquivo), qual
	 * extens�o que ir� utilizar e qual ser� o delimitador de campos
	 * 
	 * @param tableName
	 *            Nome da tabela (arquivo)
	 * @param extension
	 *            Extens�o do arquivo
	 * @param delimiter
	 *            Delimitador de colunas
	 * @throws IOException
	 */
	public TableInFile(String tableName, String extension, String delimiter) throws IOException {

		if (!extension.contains(".")) {
			// ERRO -> N�o definiu uma exten��o v�lida
		} else {
			this.extension = extension;
		}

		File f = new File(this.removeIlegalCaracters(tableName) + extension);
		if (!f.exists()) {
			f.createNewFile();
		}
		this.tableName = this.removeIlegalCaracters(tableName);

		if (!delimiter.equals("")) {
			this.delimiter = delimiter;
		}
	}

	/**
	 * Esse � o construtor simplificado, basta indicar o nome da tabela (nome do
	 * arquivo). Ser� utilizado extens�o e delimitador padr�o
	 * 
	 * @param tableName
	 *            Nome da tabela (arquivo)
	 * @throws IOException
	 */
	public TableInFile(String tableName) throws IOException {
		File f = new File(this.removeIlegalCaracters(tableName) + this.extension);
		if (!f.exists()) {
			// CRIAR ERRO
			f.createNewFile();
		}
		this.tableName = this.removeIlegalCaracters(tableName);
	}

	/**
	 * Trata-se de um m�todo privado que remove caracteres n�o aceitos como nome
	 * de arquivo. N�o apenas caracteres como tamb�m nomes especiais como: CON,
	 * PRN, LPT0, LPT1, NUL e assim por diante. Na verdade via CMD � poss�vel
	 * criar alguns com esses nomes, mas n�o ser�o removidos nem lidos pelo
	 * Windows explorer (caso use windows)
	 * 
	 * @param str
	 *            String que possa conter caracteres ilegais
	 * @return String com todos caracteres ilegais removidos
	 */
	private String removeIlegalCaracters(String str) {
		return str.replace("/", "").replace("\\", "").replace(":", "").replace("*", "").replace("?", "")
				.replace("\"", "").replace("<", "").replace(">", "").replace("|", "").replace("$", "")
				.replace("con", "").replace("prn", "").replace("aux", "").replace("clock$", "").replace("nul", "")
				.replace("com0", "").replace("com1", "").replace("com2", "").replace("com3", "").replace("com4", "")
				.replace("com5", "").replace("com6", "").replace("com7", "").replace("com8", "").replace("com9", "")
				.replace("lpt0", "").replace("lpt1", "").replace("lpt2", "").replace("lpt3", "").replace("lpt4", "")
				.replace("lpt5", "").replace("lpt6", "").replace("lpt7", "").replace("lpt8", "").replace("lpt9", "");
	}

	/**
	 * M�todo privado para buscar dentro de uma array determinado valor int
	 * Utilizei esse m�todo pois n�o achei, e n�o sei se existe algum m�todo
	 * dentro da classe Array ou Arrays que fa�a isso. O objetivo dele � para
	 * remo��o em massa de linhas, evitando ler todas a cada remo��o
	 * 
	 * @param array
	 *            recebe o array de inteiros
	 * @param id
	 *            recebe o inteiro para ser comparado
	 * @return true se existir o id fornecido no array.
	 */
	private boolean containsInArray(int[] array, int id) {
		for (int i : array) {
			if (i == id)
				return true;
		}
		return false;
	}

	/**
	 * Adiciona um novo registro a tabela. Ele recebe suas colunas em um array
	 * (quantas colunas desejar), assim ele retira os dados, separa pelo
	 * delimitador que voc� determinou e grava em uma linha no arquivo
	 * 
	 * @param row
	 *            Array com todos as colunas do registro
	 * @throws FileNotFoundException
	 */
	public void add(String[] row) throws FileNotFoundException {
		if (row.length == 0 || row == null) {
			throw new EmptyRowException();
		}

		String linha = "";
		for (String l : row) {
			linha += l + this.delimiter;
		}
		linha = linha.substring(0, linha.length() - 1);

		try {
			FileWriter fw = new FileWriter(this.tableName + this.extension, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(linha);
			bw.newLine();
			bw.close();
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
	}

	/**
	 * M�todo sobrecarregado para remo��o simples. Basta informar o id (com base
	 * nas linhas do arquivo) que o m�todo ir� percorrer o arquivo e remover o
	 * id selecionado
	 * 
	 * @param id
	 *            numero da linha a ser removido (inicial baseado em zero)
	 * @throws IOException
	 */
	public void delete(int id) throws IOException {
		if (id < 0) {
			throw new ZeroIdException();
		}
		FileReader fr = new FileReader(this.tableName + this.extension);
		BufferedReader br = new BufferedReader(fr);
		List<String> linhas = new ArrayList<>();

		for (int i = 0; br.ready(); i++) {
			if (i == id) {
				br.readLine();
				continue;
			} else
				linhas.add(br.readLine());
		}
		br.close();

		FileWriter fw = new FileWriter(this.tableName + this.extension, false);
		BufferedWriter bw = new BufferedWriter(fw);
		for (String linha : linhas) {
			bw.write(linha);
			bw.newLine();
		}

		bw.close();
	}

	/**
	 * M�todo sobrecarregado para remo��o em massa. Com o outro m�todo era
	 * poss�vel fazer remo��o simples, mas n�o era poss�vel a remo��o de v�rios
	 * id's ao mesmo tempo.
	 * 
	 * @param ids
	 *            array com v�rios int (numero de linhas) a serem removidas
	 * @throws IOException
	 */
	public void delete(int[] ids) throws IOException {
		if (ids.length == 0 || ids == null) {
			throw new IllegalArgumentException("Nenhum valor fornecido!");
		}
		FileReader fr = new FileReader(this.tableName + this.extension);
		BufferedReader br = new BufferedReader(fr);
		List<String> linhas = new ArrayList<>();

		for (int i = 0; br.ready(); i++) {
			if (this.containsInArray(ids, i)) {
				br.readLine();
				continue;
			} else
				linhas.add(br.readLine());
		}
		br.close();

		FileWriter fw = new FileWriter(this.tableName + this.extension, false);
		BufferedWriter bw = new BufferedWriter(fw);
		for (String linha : linhas) {
			bw.write(linha);
			bw.newLine();
		}

		bw.close();
	}

	/**
	 * Devolve sua linha separada por colunas
	 * 
	 * @param id
	 *            linha que deseja buscar
	 * @return array dividido com as colunas (Primeira coluna � o ID)
	 * @throws IOException
	 */
	public String[] getById(int id) throws IOException {
		if (id < 0) {
			throw new ZeroIdException();
		}

		String[] linha = null;
		FileReader fr = new FileReader(this.tableName + this.extension);
		BufferedReader br = new BufferedReader(fr);
		for (int i = 0; br.ready(); i++) {
			if (i == id) {
				String s = i + this.delimiter + br.readLine();
				linha = s.split(this.delimiter);
				break;
			} else {
				br.readLine();
			}
		}
		br.close();
		return linha;
	}

	/**
	 * Devolve sua linha separada por colunas
	 * 
	 * @param criteria
	 *            Cadeia de strings que deseja buscar
	 * @return array dividido com as colunas (Primeira coluna � o ID)
	 * @throws IOException
	 */
	public String[] getByString(String criteria) throws IOException {
		if (criteria.equals("") || criteria == null) {
			throw new CriteriaEmptyOrNullException();
		}

		String[] linha = null;
		FileReader fr = new FileReader(this.tableName + this.extension);
		BufferedReader br = new BufferedReader(fr);
		for (int i = 0; br.ready(); i++) {
			String encontrado = i + this.delimiter + br.readLine();
			if (encontrado.contains(criteria)) {
				br.close();
				linha = encontrado.split(this.delimiter);
				return linha;
			}
		}
		br.close();
		return linha;
	}

	/**
	 * Esse m�todo � semelhante ao {@code getByString} e {@code getById}. Por�m
	 * ao inv�s de retornar 1 linha esse m�todo retorna uma lista com v�rias
	 * linhas
	 * 
	 * @param criteria
	 *            Crit�rio de busca
	 * @return Lista com diversos registros ou null
	 * @throws IOException
	 */
	public List<String[]> find(String criteria) throws IOException {
		if (criteria.equals("") || criteria == null) {
			throw new CriteriaEmptyOrNullException();
		}
		List<String[]> encontrados = new ArrayList<>();
		FileReader fr = new FileReader(this.tableName + this.extension);
		BufferedReader br = new BufferedReader(fr);
		for (int i = 0; br.ready(); i++) {
			String linha = i + this.delimiter + br.readLine();
			if (linha.contains(criteria)) {
				String[] dados = linha.split(this.delimiter);
				encontrados.add(dados);
			}
		}
		br.close();
		return encontrados;
	}

	/**
	 * Simples e direto!
	 * 
	 * @return Retorna todos os registros dentro da tabela (arquivo)
	 * @throws IOException
	 */
	public List<String[]> getAll() throws IOException {

		List<String[]> todos = new ArrayList<>();
		FileReader fr = new FileReader(this.tableName + this.extension);
		BufferedReader br = new BufferedReader(fr);
		for (int i = 0; br.ready(); i++) {
			String linha = i + this.delimiter + br.readLine();
			todos.add(linha.split(this.delimiter));
		}
		br.close();
		return todos;
	}

}
