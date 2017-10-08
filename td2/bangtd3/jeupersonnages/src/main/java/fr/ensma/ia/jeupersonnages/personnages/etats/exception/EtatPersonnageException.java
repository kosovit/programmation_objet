package fr.ensma.ia.jeupersonnages.personnages.etats.exception;

public class EtatPersonnageException extends Exception {
	private String msgExc;

	 public EtatPersonnageException(String msgExc) {
		super();
		this.msgExc = msgExc;
	}

	public EtatPersonnageException() {
		super();
	}
	@Override
	public String getMessage() {

		return super.getStackTrace()[0].toString() + " ; " + msgExc.toString();
	}

}