@SuppressWarnings("serial")

public class PileVideException extends Exception {
	PileVideException(String s) {
		super("Erreur -> pile vide lors d'un : " + s);
	}
}
