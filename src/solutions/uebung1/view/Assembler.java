package solutions.uebung1.view;

import solutions.uebung1.control.GermanTranslator;

public class Assembler {

    public Assembler() {
        GermanTranslator germanTranslator = new GermanTranslator();
        ClientDI client = new ClientDI( germanTranslator );

        client.display(1);

        // client.setTranslator( new EnglishTranslator());

        client.display(2);
    }

    public static void main(String[] args) {
        Assembler assembler = new Assembler();
    }

}
