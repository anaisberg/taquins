import java.io.Serializable;

@SuppressWarnings("serial")
class Maillon<T> implements Serializable {

	// ATTRIBUTS ////////////////////////////////////////////////

	private T valeur;
	private Maillon<T> suivant;

	// CONSTRUCTEURS ////////////////////////////////////////////

	// Constructeur permettant de créer un Maillon isolé
	public Maillon(T x) {
		valeur = x;
		// Quand on le crée, le Maillon est isolé, donc il n'a pas de "suivant".
		suivant = null;
	}

	// Constructeur par recopie (copie récursive d'un maillon et de ses suivants)
	// Attention :
	// - m doit être non-nul, sinon crée un maillon avec valeur nulle !
	// - la structure chaînée ne doit pas comporter de cycle.
	public Maillon(Maillon<T> m) {
		if (m == null) { // utile pour éviter une NullPointerException si m est nul
			valeur = null;
			suivant = null;
		} else {
			// 1) On commence par recopier la valeur du maillon :
			valeur = m.valeur; // recopie physique de la valeur (ici type élémentaire)
			// 2) On recopie récursivement le reste de la structure chaînée en affectant
			// null à
			// l'attribut suivant ssi m n'a pas de suivant, et sinon une COPIE du maillon
			// m.suivant via un appel récursif au constructeur par recopie.
			suivant = (m.suivant == null) ? null : new Maillon<T>(m.suivant);
		}
	}

	// METHODES /////////////////////////////////////////////////

	// Retourne la valeur contenue dans le maillon.
	public T renvoyerValeur() {
		return valeur;
	}

	// Retourne une référence vers le Maillon suivant de la chaîne.
	public Maillon<T> renvoyerSuivant() {
		return suivant;
	}

	// "Chaîne" le Maillon en stockant la référence vers le Maillon suivant
	public void lierAvecSuivant(Maillon<T> leSuivant) {
		suivant = leSuivant;
	}

}