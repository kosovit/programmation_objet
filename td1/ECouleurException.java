package jeupersonnagesv1.outils.exceptions;

/**
 * 
 * @author michaelrichard
 *
 */
public class ECouleurException extends Exception {

	private String msgExc;
	
	public ECouleurException(){
		super();
	}
	
	public ECouleurException(String msg){
		super();
		msgExc = msg;
	}

	@Override
	public String getMessage() {
		
		return  super.getStackTrace()[0].toString() +  " ; "
				+ msgExc.toString();
	}

	private static final long serialVersionUID = 1L;
}
