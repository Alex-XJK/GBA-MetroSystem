package metroSystem;

/**
 * The specified Hong Kong MTR administrator.
 * Implements {@link metroSystem.Administrator} super class.
 * @since Sept. 21, 2021
 * @version 1
 */
public class AdministratorHK implements Administrator {
    private static AdministratorHK instance = new AdministratorHK();

    private AdministratorHK() {
    }

    /**
     * The Singleton Method to get {@code AdministratorHK} instance.
     * @return The unique instance
     */
    public static AdministratorHK getInstance() {
        return instance;
    }
}
