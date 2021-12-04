package metroSystem;

/**
 * The specified Shenzhen MTR administrator.
 * Implements {@link metroSystem.Administrator} super class.
 * @since Sept. 21, 2021
 * @version 1
 */
public class AdministratorSZ implements Administrator {
    private static AdministratorSZ instance = new AdministratorSZ();

    private AdministratorSZ() {
    }

    /**
     * The Singleton Method to get {@code AdministratorSZ} instance.
     * @return The unique instance
     */
    public static AdministratorSZ getInstance() {
        return instance;
    }
}
