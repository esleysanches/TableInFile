package br.com.esleysanches.tableinfile.uncheckedexceptions;

public class ZeroIdException extends BaseUncheckedExceptions {

	private static final long serialVersionUID = 1L;

	public ZeroIdException() {
		super("ID definida não pode ser menor que zero!");
	}
	
}
