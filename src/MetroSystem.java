package src;
import java.util.*;

public class MetroSystem
{
	private static MetroSystem instance = new MetroSystem();
    

    private MetroSystem() {
        
    }

    public static MetroSystem getInstance() {
        return instance;
    }
}