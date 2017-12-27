/**
 * MalformedFractionException.java - User-defined checked exception
 * Begun 11/21/17
 * @author Andrew Eissen
 */

package binarysearchtreeproject3;

class MalformedFractionException extends Exception {

    /**
     * Default constructor
     */
    public MalformedFractionException() {
        super();
    }

    /**
     * Parameterized constructor
     * @param message
     */
    public MalformedFractionException(String message) {
       super(message);
    }

    /**
     * Parameterized constructor
     * @param message
     * @param cause
     */
    public MalformedFractionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Parameterized constructor
     * @param cause
     */
    public MalformedFractionException(Throwable cause) {
        super(cause);
    }
}