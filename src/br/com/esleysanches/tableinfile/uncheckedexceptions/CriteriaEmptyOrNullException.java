package br.com.esleysanches.tableinfile.uncheckedexceptions;

public class CriteriaEmptyOrNullException extends BaseUncheckedExceptions {

	private static final long serialVersionUID = 1L;

	public CriteriaEmptyOrNullException() {
		super("String para crit�rio � vazia ou nula!");
	}
	
}
