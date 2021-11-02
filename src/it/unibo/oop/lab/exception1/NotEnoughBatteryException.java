package it.unibo.oop.lab.exception1;

public class NotEnoughBatteryException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NotEnoughBatteryException(double battery) {
	this("Battery was " + battery);
    }

    public NotEnoughBatteryException(String message) {
	super(message);
	// TODO Auto-generated constructor stub
    }

    public NotEnoughBatteryException(Throwable cause) {
	super(cause);
	// TODO Auto-generated constructor stub
    }

    public NotEnoughBatteryException(String message, Throwable cause) {
	super(message, cause);
	// TODO Auto-generated constructor stub
    }

    public NotEnoughBatteryException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
	// TODO Auto-generated constructor stub
    }

}
