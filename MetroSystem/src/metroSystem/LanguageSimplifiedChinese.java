package MetroSystem.src.metroSystem;

public class LanguageSimplifiedChinese implements Language {

    private static LanguageSimplifiedChinese instance = new LanguageSimplifiedChinese();

    private LanguageSimplifiedChinese() {
    }

    public static LanguageSimplifiedChinese getInstance() {
        return instance;
    }
}
