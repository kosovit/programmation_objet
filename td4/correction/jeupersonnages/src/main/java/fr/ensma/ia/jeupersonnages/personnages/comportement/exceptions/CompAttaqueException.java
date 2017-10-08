package fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions;

public class CompAttaqueException extends Exception {

	private String messExc;
	
	public CompAttaqueException(){	}
	
	public CompAttaqueException(final String mess){
		messExc = mess;
	}
	
	@Override
	public String getMessage() {
		return super.getStackTrace()[0].toString() + " ; " + messExc.toString();
	}

	private static final long serialVersionUID = -7720268981851231149L;
	
}
