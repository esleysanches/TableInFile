package br.com.esleysanches.tableinfile.uncheckedexceptions;

public class ArrayIdEmptyException extends BaseUncheckedExceptions {

	private static final long serialVersionUID = 1L;

	public ArrayIdEmptyException() {
		super("Array de ids vazia ou nula!");
	}

}
