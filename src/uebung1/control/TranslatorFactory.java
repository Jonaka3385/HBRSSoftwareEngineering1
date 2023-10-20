package uebung1.control;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class TranslatorFactory implements Factory {
    @Contract(value = " -> new", pure = true)
    public static @NotNull Translator createGermanTranslator() {
        return new GermanTranslator();
    }
}
