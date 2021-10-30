package metroSystem;

public class CrtStation extends Criteria{

    public CrtStation() {
        super("Minimal Station Number");
        super.algorithm = new AlgBFS();
    }

    @Override
    protected DataList createDataList() {
        return new DLConnectivity();
    }
}
