package uebung4;

import uebung4.persistence.*;

public class Main {
    public static void main(String[] args) {
        /*
         * Grad nicht benötigt
         * von hier
        // Referenz auf das Container-Objekt holen
        Container container = Container.getInstance();

        // Strategie für Stream-Strategy erzeugen und in den Container einsetzen
        container.setPersistenceStrategie(new PersistenceStrategyStream<>());

        // Client zur Ein- und Ausgabe starten
        Client client = new Client();
        client.startClient();
         * nach hier
         */

        UserStoriesManager.start();
    }
}
