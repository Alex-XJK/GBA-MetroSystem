package MetroSystem.src.metroSystem;

public class AdministratorHK implements Administrator {
    private static AdministratorHK instance = new AdministratorHK();

    private AdministratorHK() {
    }

    public static AdministratorHK getInstance() {
        return instance;
    }
}
