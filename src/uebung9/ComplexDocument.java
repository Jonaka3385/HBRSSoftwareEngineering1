package uebung9;

import java.util.List;

public class ComplexDocument extends Document {
    private List<Document> docs;

    public ComplexDocument() {}

    public int getSize() {
        int size = 0;

        for (Document doc : docs) {
            size += doc.getSize();
        }

        return size;
    }

    public Document getDoc(int id) {
        for (Document doc : docs) {
            if (doc.getID() == id) return doc;
        }
        return null;
    }

    public void AddDocument(Document newDoc) {
        docs.add(newDoc);
    }

    public void removeDoc(int id) {
        int i = 0;
        for (Document doc : docs) {
            if (doc.getID() == id) {
                docs.remove(i);
                return;
            }
            i++;
        }
    }
}
