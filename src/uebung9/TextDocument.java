package uebung9;

public class TextDocument extends CoreDocument {
    private String text;
    private Encoding encoding;

    public TextDocument(String input, Encoding encoding) {
        text = input;
        this.encoding = encoding;
    }

    public int getSize() {
        return text.getBytes().length;
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        text = newText;
    }

    public Encoding getEncoding() {
        return encoding;
    }

    public void setEncoding(Encoding newEncoding) {
        encoding = newEncoding;
    }
}
