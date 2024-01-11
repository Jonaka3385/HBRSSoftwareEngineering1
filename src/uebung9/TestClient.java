package uebung9;

public class TestClient {
    public static void main(String[] args) {
        ComplexDocument testDoc = new ComplexDocument();
        TextDocument text = new TextDocument("Hallo", Encoding.UTF8);
        testDoc.addDocument(text);
        GraphicDocument graphic = new GraphicDocument("localhost:8080");
        testDoc.addDocument(graphic);

        int textSize = text.getSize();
        int testSize = testDoc.getSize();

        System.out.println("Text: " + textSize + "\nTest (alles): " + testSize);
    }
}
