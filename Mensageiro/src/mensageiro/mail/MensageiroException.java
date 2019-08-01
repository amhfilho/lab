/*
 * MensageiroException.java
 *
 */

package mensageiro.mail;

/**
 * Exception genérica para toda a aplicação
 * @author antonioh
 */
public class MensageiroException extends RuntimeException {
       
    private Throwable throwable;
    
    private MensageiroException(Throwable t) {
        this.throwable = t;
    }
    
    /** Wraps another exeception in a RuntimeException. */
    public static RuntimeException wrap(Throwable t) {
        if (t instanceof RuntimeException)
            return (RuntimeException) t;
        return new MensageiroException(t);
    }
    
    public Throwable getCause() {
        return this.throwable;
    }
    
    public void printStackTrace() {
        this.throwable.printStackTrace();
    }
}
