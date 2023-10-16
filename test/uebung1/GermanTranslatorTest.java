package uebung1;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import uebung1.control.GermanTranslator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {
    GermanTranslator translator;

    @BeforeEach
    void setUp() {
        translator = new GermanTranslator();
    }

    @AfterEach
    void tearDown() {
        translator = null;
    }

    @Test
    void aPositiveTest() {
        String value = translator.translateNumber(1);
        assertEquals("eins", value);
    }

    @Test
    void aNegativeTest() {
        String value = translator.translateNumber(0);   //Unter Wertebereich
        assertEquals("Uebersetzung der Zahl " + 0 + " nicht möglich " + 1.0, value);
        value = translator.translateNumber(11);         //Über Wertebereich
        assertEquals("Uebersetzung der Zahl " + 11 + " nicht möglich " + 1.0, value);
    }
}
