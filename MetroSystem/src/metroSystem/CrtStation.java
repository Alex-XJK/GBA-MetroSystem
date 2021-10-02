package MetroSystem.src.metroSystem;

public class CrtStation extends Criteria{

    public CrtStation() {
        super("Minimal Station Number");
        super.algorithm = new AlgBFS();
    }

    @Override
    public void createAdjList() {
        // Todo: Create it!
    }
}
