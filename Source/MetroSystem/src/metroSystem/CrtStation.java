package metroSystem;

/**
 * Extend {@link metroSystem.Criteria}.
 * <br>
 * Designed for the special criteria that focus on finding a route with the least stations number that need to go through.
 * @version 1.0
 */
public class CrtStation extends Criteria{

    public CrtStation() {
        super("Minimal Station Number");
        super.algorithm = new AlgBFS();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DataList createDataList() {
        return new DLConnectivity();
    }
}
