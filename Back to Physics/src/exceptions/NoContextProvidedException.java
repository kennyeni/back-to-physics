package exceptions;

/**
 *  Esta clase decrlara las excepciones que existan 
 * @author vero
 *
 */
public class NoContextProvidedException extends Exception {
	
	
	/**
	 * Excepci—n cuando la pantalla esta mal definida, sin par‡metros
	 */
	
	public NoContextProvidedException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Excepci—n cuando la pantalla esta mal definida, muestra un mensaje
	 * @param detailMessage
	 */
	public NoContextProvidedException(String detailMessage) {
		super(detailMessage);
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * Excepci—n cuando la pantalla esta mal definida, con la probabilidad de lanzar una excepci—n 
	 * @param throwable
	 */
	public NoContextProvidedException(Throwable throwable) {
		super(throwable);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Excepci—n cuando la pantalla esta mal definida, manda un mensaje y tiene la probabilidad de lanzar una excepci—n
	 * @param detailMessage
	 * @param throwable
	 */
	public NoContextProvidedException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
		// TODO Auto-generated constructor stub
	}

}
