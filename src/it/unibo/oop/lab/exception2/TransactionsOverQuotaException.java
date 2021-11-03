package it.unibo.oop.lab.exception2;

public class TransactionsOverQuotaException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public TransactionsOverQuotaException(String message) {
	super(message);
    }

    public TransactionsOverQuotaException(Throwable cause) {
	super(cause);
    }

}
