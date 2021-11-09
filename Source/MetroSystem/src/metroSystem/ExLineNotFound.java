package metroSystem;

/**
 * Exception class.
 * Throws when the desired subway line cannot be found
 */
public class ExLineNotFound extends Exception{
    private static final long serialVersionUID = 1L;

    public ExLineNotFound() { super("The line you look for does not exist."); }
    public ExLineNotFound(String message) { super(message); }
}
