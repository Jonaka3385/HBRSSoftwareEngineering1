package uebung1.control;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Helper {
    @Contract(value = " -> new", pure = true)
    public static @NotNull GermanTranslator getGermanTranslator() {
        return new GermanTranslator();
    }
}
