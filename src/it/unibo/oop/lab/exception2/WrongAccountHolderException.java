package it.unibo.oop.lab.exception2;

public class WrongAccountHolderException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public WrongAccountHolderException(String message) {
	super(message);
    }

    public WrongAccountHolderException(Throwable cause) {
	super(cause);
    }

}
