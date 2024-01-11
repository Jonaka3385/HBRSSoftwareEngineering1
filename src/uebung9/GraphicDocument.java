package uebung9;

public class GraphicDocument extends CoreDocument {
    private String url;

    public GraphicDocument(String url) {
        this.url = url;
    }

    public int getSize() {
        return 1200;
    }
}
