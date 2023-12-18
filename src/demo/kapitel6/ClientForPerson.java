package demo.kapitel6;

public class ClientForPerson {
    public static void main(String[] args) {
        PersonDTO personDTO = new PersonDTO("Alda", 2);
        System.out.println("Name der Person: " + personDTO.name());
        System.out.println(personDTO.toString());
    }
}
