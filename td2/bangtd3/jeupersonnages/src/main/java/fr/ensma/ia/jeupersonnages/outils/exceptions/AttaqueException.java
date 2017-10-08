package fr.ensma.ia.jeupersonnages.outils.exceptions;

public class AttaqueException extends Exception{
	private String msgExc;

	public AttaqueException(String msgExc) {
		super();
		this.msgExc = msgExc;
	}

	public AttaqueException() {
		super();
	}
	@Override
	public String getMessage() {

		return super.getStackTrace()[0].toString() + " ; " + msgExc.toString();
	}

}
