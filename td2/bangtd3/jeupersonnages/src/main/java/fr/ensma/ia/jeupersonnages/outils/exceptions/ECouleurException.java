package fr.ensma.ia.jeupersonnages.outils.exceptions;

/**
 * Définition d'une exception liée à la gestion du type ECouleur.
 * 
 * @author michaelrichard
 *
 */
public class ECouleurException extends Exception {

	private String msgExc;

	/**
	 * Constructeur.
	 */
	public ECouleurException() {
		super();
	}

	/**
	 * Constructeur avec message.
	 * 
	 * @param msg
	 *            String, le message
	 */
	public ECouleurException(String msg) {
		super();
		msgExc = msg;
	}

	/**
	 * Obtient le message précédé d'un extrait de la trace de l'exception.
	 */
	@Override
	public String getMessage() {

		return super.getStackTrace()[0].toString() + " ; " + msgExc.toString();
	}

	private static final long serialVersionUID = 1L;
}
