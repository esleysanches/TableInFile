package br.com.esleysanches.tableinfile.uncheckedexceptions;

public class EmptyRowException extends BaseUncheckedExceptions {

	private static final long serialVersionUID = 1L;

	public EmptyRowException() {
		super("Linha fornecida vazia ou nula!");
	}

}
