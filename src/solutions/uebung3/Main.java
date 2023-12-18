package solutions.uebung3;

import solutions.uebung3.persistence.*;

public class Main {

    public static void main(String[] args) {
        // Referenz auf das Container-Objekt holen
        Container container = Container.getInstance();

        // Strategie für Stream-Strategy erzeugen und in den Container einsetzen
        container.setPersistenceStrategie( new PersistenceStrategyStream() );

        // Client zur Ein- und Ausgabe starten
        Client client = new Client();
        client.startClient();
    }
}
