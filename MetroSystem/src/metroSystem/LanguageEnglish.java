package MetroSystem.src.metroSystem;

public class LanguageEnglish implements Language {

    private static LanguageEnglish instance = new LanguageEnglish();

    private LanguageEnglish() {
    }

    public static LanguageEnglish getInstance() {
        return instance;
    }
}
