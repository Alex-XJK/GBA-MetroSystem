package MetroSystem.src.metroSystem;

public class CrtTime extends Criteria{
    public CrtTime(String name) {
        super("Minimal Travelling Time");
        super.algorithm = new AlgDijkstra();
    }

    @Override
    protected DataList createDataList() {
        return new DLTime();
    }
}
