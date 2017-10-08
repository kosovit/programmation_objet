package fr.ensma.ia.jeupersonnages.outils.exceptions;

/**
 * D�finition d'une Exception li�e � la gestion d'une Troupe.
 * @author michaelrichard
 *
 */
public class TroupeVideException extends Exception {

	
	private String msgExc;
	
	public TroupeVideException(){}
	
	public TroupeVideException(final String msg){
		msgExc = msg;
	}
	
	@Override
	public String getMessage() {
		return super.getStackTrace()[0].toString() + " ; " +msgExc.toString();
	}
	
	private static final long serialVersionUID = 1L;
	
}
