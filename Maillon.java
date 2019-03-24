import java.io.Serializable;

@SuppressWarnings("serial")
class Maillon<T> implements Serializable {

	// ATTRIBUTS ////////////////////////////////////////////////

	private T valeur;
	private Maillon<T> suivant;

	// CONSTRUCTEURS ////////////////////////////////////////////

	// Constructeur permettant de cr�er un Maillon isol�
	public Maillon(T x) {
		valeur = x;
		// Quand on le cr�e, le Maillon est isol�, donc il n'a pas de "suivant".
		suivant = null;
	}

	// Constructeur par recopie (copie r�cursive d'un maillon et de ses suivants)
	// Attention :
	// - m doit �tre non-nul, sinon cr�e un maillon avec valeur nulle !
	// - la structure cha�n�e ne doit pas comporter de cycle.
	public Maillon(Maillon<T> m) {
		if (m == null) { // utile pour �viter une NullPointerException si m est nul
			valeur = null;
			suivant = null;
		} else {
			// 1) On commence par recopier la valeur du maillon :
			valeur = m.valeur; // recopie physique de la valeur (ici type �l�mentaire)
			// 2) On recopie r�cursivement le reste de la structure cha�n�e en affectant
			// null �
			// l'attribut suivant ssi m n'a pas de suivant, et sinon une COPIE du maillon
			// m.suivant via un appel r�cursif au constructeur par recopie.
			suivant = (m.suivant == null) ? null : new Maillon<T>(m.suivant);
		}
	}

	// METHODES /////////////////////////////////////////////////

	// Retourne la valeur contenue dans le maillon.
	public T renvoyerValeur() {
		return valeur;
	}

	// Retourne une r�f�rence vers le Maillon suivant de la cha�ne.
	public Maillon<T> renvoyerSuivant() {
		return suivant;
	}

	// "Cha�ne" le Maillon en stockant la r�f�rence vers le Maillon suivant
	public void lierAvecSuivant(Maillon<T> leSuivant) {
		suivant = leSuivant;
	}

}