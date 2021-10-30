package MetroSystem.src.metroSystem;

/**
 * Exception class.
 * Throws when the desired station cannot be found
 */
public class ExStationNotFound extends Exception{
    private static final long serialVersionUID = 1L;

    public ExStationNotFound() { super("Station you look for does not exist."); }
    public ExStationNotFound(String message) { super(message); }
}
