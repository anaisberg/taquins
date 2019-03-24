
public class Pile<T> {
// ATTRIBUTS 
  
  private Maillon<T> sommet;
  // NOTA-BENE : les maillons de la pile seront chaînés dans le sens :
  //   sommet ~ 1er maillon --> 2e maillon --> ... dernier maillon
  // afin que empiler() et depiler() soient toutes deux de complexité O(1)
  
  private int hauteur;
  
// CONSTRUCTEURS
  public Pile() {  
    sommet = null;
    hauteur = 0;
  }
// Constructeur par recopie (p doit être non-nul)
   public Pile(Pile<T> p) {
    
    hauteur = p.hauteur;
    sommet = new Maillon<T>(p.sommet);
  }
// METHODES 

public T renvoyerSommet() throws PileVideException {
    // Tenter d'accéder au sommet d'une pile vide lance PileVideException :
    if (estVide()) {
      throw new PileVideException("Accès sommet");
    }
    return sommet.renvoyerValeur();
  }

  public int renvoyerHauteur() {
    return hauteur;
  }
  public boolean estVide() {
    return sommet == null;
  }
  public void empiler(T x) {
    Maillon<T> m = new Maillon<> (x);
    m.lierAvecSuivant(sommet);
    sommet = m;
    hauteur++;
  }
  public void depiler() throws PileVideException {
    // Tenter de dépiler sur une pile vide lance PileVideException :
    if (estVide()) {
      throw new PileVideException("dépilier()");
    }
    sommet = sommet.renvoyerSuivant();
    hauteur--;
  }

  public void afficher() {
    // NOTA-BENE : cette méthode affiche le contenu de la pile SANS LA MODIFIER
    for (Maillon<T> m = sommet ; m != null ; m = m.renvoyerSuivant()) {
      System.out.print(m.renvoyerValeur() + "  ");
    }
    System.out.printf("(le sommet est à gauche, ht=%d)\n", hauteur);
  }

  public void vider() {
    while (! estVide()) {
      try {
        depiler();
      } catch (PileVideException e) {
     }
    }
  }

// FONCTION MAIN, contient de quoi TESTER que l'ensemble maillon + pile fonctionne :
  public static void main(String[] args) throws PileVideException {
    Pile<Double> p = new Pile<>();
    p.empiler(10.);
    p.empiler(20.);
    p.empiler(30.);
    System.out.print("p --> "); 
    p.afficher();
    p.depiler();
    System.out.print("p > p.depiler() --> ");
    p.afficher();
    System.out.println("   Le sommet de p est : " + p.renvoyerSommet());
    System.out.println("   La hauuteur de p est : " + p.renvoyerHauteur());
    Pile<Double> p2 = new Pile<>(p);
    System.out.print("p2 --> ");
    p2.afficher();
    p2.depiler();
    System.out.print("p > p2.depiler() --> ");
    p.afficher();
    System.out.print("p2 > p2.depiler() --> ");
    p2.afficher();
    p.vider();
    System.out.print("p > p.vider() --> ");
    p.afficher();
    System.out.print("p2 > p.vider() --> ");
    p2.afficher();
    p2.depiler();
    System.out.print("p2 > p.depiler() --> ");
    p2.afficher();
    p2.depiler();
    System.out.print("p2 > p.depiler() --> ");
    p2.afficher();
  }
}
