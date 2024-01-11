package uebung9;

public enum Encoding {
    UTF8,
    UTF16,
    UTF32;

    Encoding() {
        System.out.println("Encoding: " + this);
    }
}
