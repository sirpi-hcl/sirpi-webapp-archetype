#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infra.exception;

public class ApplicationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ApplicationException(String message, Throwable cause) { 
		super(message, cause);
	}
	
	public ApplicationException(Throwable cause) {
		super(cause);
	}
	
}
