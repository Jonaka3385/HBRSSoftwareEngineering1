package solutions.uebung8.portal;

@SuppressWarnings("unused")
public class HotelSuche {
    public void mainMethod() {
        SuchAuftrag suchAuftrag = new SuchAuftrag();
        ExternalHotelSucheInterface service = new ReiseAnbieterAdapter();
        SuchErgebnis suchErgebnis = service.suche(suchAuftrag);
    }
}
