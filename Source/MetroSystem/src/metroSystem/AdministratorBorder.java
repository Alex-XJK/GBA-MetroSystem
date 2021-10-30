package MetroSystem.src.metroSystem;

public class AdministratorBorder implements Administrator {
    private static AdministratorBorder instance = new AdministratorBorder();

    private AdministratorBorder() {
    }

    public static AdministratorBorder getInstance() {
        return instance;
    }
}
