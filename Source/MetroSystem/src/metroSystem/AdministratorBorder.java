package metroSystem;

/**
 * The specified cross-border administrator between different cities.
 * Implements {@link metroSystem.Administrator} super class.
 * @since Sept. 30, 2021
 * @version 1
 */
public class AdministratorBorder implements Administrator {
    private static AdministratorBorder instance = new AdministratorBorder();

    private AdministratorBorder() {
    }

    /**
     * The Singleton Method to get {@code AdministratorBorder} instance.
     * @return The unique instance
     */
    public static AdministratorBorder getInstance() {
        return instance;
    }
}
