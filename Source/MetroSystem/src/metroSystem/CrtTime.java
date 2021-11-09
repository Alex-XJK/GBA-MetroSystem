package metroSystem;

/**
 * Extend {@link metroSystem.Criteria}.
 * <br>
 * Designed for the special criteria that focus on finding a route with the shortest travelling time that need to spend.
 * @version 1.0
 */
public class CrtTime extends Criteria{
    public CrtTime() {
        super("Minimal Travelling Time");
        super.algorithm = new AlgDijkstra();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected DataList createDataList() {
        return new DLTime();
    }
}
