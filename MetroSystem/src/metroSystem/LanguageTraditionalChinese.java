package metroSystem;

public class LanguageTraditionalChinese implements Language {

    private static LanguageTraditionalChinese instance = new LanguageTraditionalChinese();

    private LanguageTraditionalChinese() {
    }

    public static LanguageTraditionalChinese getInstance() {
        return instance;
    }
}
