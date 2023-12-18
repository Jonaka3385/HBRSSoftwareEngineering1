package solutions.uebung8.portal;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import solutions.uebung8.reiseanbieter.*;

@SuppressWarnings("unused")
public class ReiseAnbieterAdapter implements ExternalHotelSucheInterface {
	private ReiseAnbieter system; // = new ... hier ausgelassen

	@Override
	public SuchErgebnis suche(SuchAuftrag auftrag) {
		QueryObject queryObject = this.transformIN(auftrag);
		QueryResult queryResult = system.executeQuery(queryObject);
        return this.transformOUT(queryResult);
	}
	
	@Contract(pure = true)
	private @Nullable QueryObject transformIN(SuchAuftrag auftrag) {
		// Transformation SuchAuftrag --> QueryObject
		return null;
	}
	
	@Contract(pure = true)
	private @Nullable SuchErgebnis transformOUT(QueryResult result) {
		// Transformation QueryResult --> SuchErgebnis
		return null;
	}
}
