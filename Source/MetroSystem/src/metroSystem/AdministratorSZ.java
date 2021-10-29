package MetroSystem.src.metroSystem;

public class AdministratorSZ implements Administrator {
    private static AdministratorSZ instance = new AdministratorSZ();

    private AdministratorSZ() {
    }

    public static AdministratorSZ getInstance() {
        return instance;
    }
}
