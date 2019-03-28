import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")

public class taquins extends Frame implements ActionListener, ItemListener {

	// attributs

	private static taquinsCanvas dessin;
	public static int compteur;
	public static Label affichageCompteur;
	private static Label etat;
	private Button retour;
	private Button rejouer;
	private Button nouvpartie;
	public static P[][] conf;
	public static Pile<P[][]> record;
	public static P[][] confIni;

	/*
	 * public enum P { C1, C2, C3, C4, C, R1haut, R1bas, R2g, R2d, vide; }
	 */

	// P pour Pièces

	public static P aneRouge1[][] = { { P.R1haut, P.C1, P.C2, P.R1haut }, { P.R1bas, P.C3, P.C4, P.R1bas },
			{ P.R1haut, P.R2g, P.R2d, P.R1haut }, { P.R1bas, P.vide, P.vide, P.R1bas }, { P.C, P.C, P.C, P.C } };

	public static P aneRouge2[][] = { { P.R1haut, P.C1, P.C2, P.R1haut }, { P.R1bas, P.C3, P.C4, P.R1bas },
			{ P.C, P.R1haut, P.R1haut, P.C }, { P.C, P.R1bas, P.R1bas, P.C }, { P.vide, P.R2g, P.R2d, P.vide } };

	public static P aneRouge3[][] = { { P.R1haut, P.C1, P.C2, P.R1haut }, { P.R1bas, P.C3, P.C4, P.R1bas },
			{ P.R1haut, P.R2g, P.R2d, P.R1haut }, { P.R1bas, P.C, P.C, P.R1bas }, { P.C, P.vide, P.vide, P.C } };

	public static P aneRouge4[][] = { { P.R1haut, P.C1, P.C2, P.R1haut }, { P.R1bas, P.C3, P.C4, P.R1bas },
			{ P.R1haut, P.C, P.C, P.R1haut }, { P.R1bas, P.R2g, P.R2d, P.R1bas }, { P.C, P.vide, P.vide, P.C } };

	public static P aneRouge5[][] = { { P.R1haut, P.C1, P.C2, P.R1haut }, { P.R1bas, P.C3, P.C4, P.R1bas },
			{ P.C, P.R2g, P.R2d, P.C }, { P.R1haut, P.C, P.C, P.R1haut }, { P.R1bas, P.vide, P.vide, P.R1bas } };

	public static P aneRouge6[][] = { { P.C, P.C1, P.C2, P.C }, { P.R1haut, P.C3, P.C4, P.R1haut },
			{ P.R1bas, P.C, P.C, P.R1bas }, { P.R1haut, P.R2g, P.R2d, P.R1haut },
			{ P.R1bas, P.vide, P.vide, P.R1haut } };

	public static P aneRouge7[][] = { { P.R1haut, P.C1, P.C2, P.R1haut }, { P.R1bas, P.C3, P.C4, P.R1bas },
			{ P.C, P.C, P.C, P.C }, { P.R1haut, P.R2g, P.R2d, P.R1haut }, { P.R1bas, P.vide, P.vide, P.R1bas } };

	public static P aneRouge8[][] = { { P.R1haut, P.C1, P.C2, P.R1haut }, { P.R1bas, P.C3, P.C4, P.R1bas },
			{ P.C, P.R2g, P.R2d, P.C }, { P.C, P.R1haut, P.R1haut, P.C }, { P.vide, P.R1bas, P.R1bas, P.vide } };

	public static P aneRouge9[][] = { { P.R2g, P.R2d, P.R2g, P.R2d }, { P.R1haut, P.C1, P.C2, P.R1haut },
			{ P.R1bas, P.C3, P.C4, P.R1bas }, { P.C, P.R2g, P.R2g, P.C }, { P.C, P.vide, P.vide, P.C } };

	// C1,C2,C3,C4 = carré 2x2 (chacune de ses cases)
	// R1haut, R1bas = rectangle 2x1
	// R2g, R2d = rectangle 1x2
	// C = carré 1x1
	// null = case vide

	public P[][][] configurations = { aneRouge1, aneRouge2, aneRouge3, aneRouge4, aneRouge5, aneRouge6, aneRouge7,
			aneRouge8, aneRouge9 };

	// constructeur

	public taquins() {

		super("Taquins"); // paramètre = titre de la fenêtre

		dessin = new taquinsCanvas();
		dessin.setSize(250, 250);

		compteur = 0;
		conf = choixConfigAleat();
		confIni = conf.clone();
		record = new Pile<P[][]>();
		record.empiler(conf);

		retour = new Button("UNDO");
		rejouer = new Button("Rejouer");
		nouvpartie = new Button("Nouveau Jeu");
		affichageCompteur = new Label("nombre de coups= 0");
		etat = new Label("Partie en cours");

		// enregistrement de l'application elle-meme comme ecouteur des événements de
		// type Action :
		retour.addActionListener(this);
		rejouer.addActionListener(this);
		nouvpartie.addActionListener(this);
		// dessin.addMouseListener(this);

		// ECOUTE DES EVENEMENTS DE TYPE FENETRE (pour quitter en cas de fermeture) :
		addWindowListener(new EcouteurPourFermetureFenetre());
		// REGROUPEMENT ET POSITIONNEMENT DES TOUCHES

		setLayout(new FlowLayout());

		Panel commandes = new Panel();
		commandes.setLayout(new GridLayout(5, 1));
		commandes.add(affichageCompteur);
		commandes.add(retour);
		commandes.add(rejouer);
		commandes.add(nouvpartie);
		commandes.add(etat);

		add(dessin, BorderLayout.CENTER);
		add(commandes);

	}

	// écouteurs

	public void actionPerformed(ActionEvent evt) {

		if (evt.getSource() == rejouer) {
			// remettre la configuration de base
			record.vider();
			record.empiler(confIni);
			conf = confIni;
			dessin.repaint();
		}

		if (evt.getSource() == retour) {
			// mettre la configuration précédente
			dessin.repaint();
		}

		if (evt.getSource() == nouvpartie) {
			// choisir une nouvelle configuration
			conf = choixConfigAleat();
			record.vider();
			record.afficher();
			record.empiler(conf);
			confIni=conf.clone();
			compteur = 0;
			System.out.println("on clique sur recommencer");
			dessin.paint(dessin.getGraphics());
			//dessin.repaint();
		}
	}

	// méthodes

	public P[][] choixConfigAleat() {

		int n = configurations.length;
		double r = Math.random();
		int i = (int) r * n;
		return configurations[i];

	}

	public static void verifier() {
		// 0 <= i <= 4
		// 0 <= j <= 3
		if (conf[3][1].equals(P.C1) && conf[3][2].equals(P.C2) && conf[4][1].equals(P.C3) && conf[4][2].equals(P.C4)) {
			etat.setText("Vous avez gagné ! bravo !");
		}
	}

	public static String[] getVoisinVide(int i, int j) {
		//String resultat = "null";
		int hauteur = 5;
		int largeur = 4;
		String tab[] = new String[2] ;
		// on fait un tableau des voisins vides car il peut y en avoir plusieurs
		int k = 0 ;
		// nombre de voisins vides

		if (i != 0 && i != hauteur - 1 && j != largeur - 1 && j != 0) {
			if (conf[i - 1][j].equals(P.vide)) {
				tab[k] = "dessus";
				k++;
			}
			if (conf[i + 1][j].equals(P.vide)) {
				tab[k] = "sous";
				k++;
				}
			if (conf[i][j - 1].equals(P.vide)) {
				tab[k] = "gauche";
				k++;
				}
			if (conf[i][j + 1].equals(P.vide)) {
				tab[k] = "droite";
				k++;
				}
		}

		if (i == 0) {
			if (j == 0) {
				if (conf[i + 1][j].equals(P.vide)) {
					tab[k] = "sous";
					k++;
					}
				if (conf[i][j + 1].equals(P.vide)) {
					tab[k] = "droite";
					k++;
					}
			}

			if (j == largeur - 1) {
				if (conf[i][j - 1].equals(P.vide)) {
					tab[k] = "gauche";
					k++;
					}
				if (conf[i + 1][j].equals(P.vide)) {
					tab[k] = "sous";
					k++;
					
				}
			}

			if (j != 0 && j != largeur - 1) {
				if (conf[i + 1][j].equals(P.vide)) {
					tab[k] = "sous";
					k++;
					}

				if (conf[i][j - 1].equals(P.vide)) {
					tab[k] = "gauche";
					k++;
					
				}
				if (conf[i][j + 1].equals(P.vide)) {
					tab[k] = "droite";
					k++;
					}
			}
		}

		if (j == 0) {
			if (i == hauteur - 1) {
				if (conf[i - 1][j].equals(P.vide)) {
					tab[k] = "dessus";
					k++;
					}
				if (conf[i][j + 1].equals(P.vide)) {
					tab[k] = "droite";
					k++;
					}
				}

			if (i != hauteur - 1 && i != 0) {
				if (conf[i + 1][j].equals(P.vide)) {
					tab[k] = "sous";
					k++;
					}
				if (conf[i - 1][j].equals(P.vide)) {
					tab[k] = "dessus";
					k++;
				}
				if (conf[i][j + 1].equals(P.vide)) {
					tab[k] = "droite";
					k++;
					
				}
			}
		}

		if (i == hauteur - 1) {
			if (j == largeur - 1) {
				if (conf[i - 1][j].equals(P.vide)) {
					tab[k] = "dessus";
					k++;
					}
				if (conf[i][j - 1].equals(P.vide)) {
					tab[k] = "gauche";
					k++;
					}
			}

			if (j != largeur - 1 && j != 0) {
				if (conf[i - 1][j].equals(P.vide)) {
					tab[k] = "dessus";
					k++;
					}
				if (conf[i][j - 1].equals(P.vide)) {
					tab[k] = "gauche";
					k++;
				}
				if (conf[i][j + 1].equals(P.vide)) {
					tab[k] = "droite";
					k++;
					}
			}
		}

		if (j == largeur - 1) {
			if (i != hauteur - 1 && i != 0) {
				if (conf[i - 1][j].equals(P.vide)) {
					tab[k] = "dessus";
					k++;
					}
				if (conf[i + 1][j].equals(P.vide)) {
					tab[k] = "sous";
					k++;
				
				}
				if (conf[i][j - 1].equals(P.vide)) {
					tab[k] = "gauche";
					k++;
					}
			}
		}

		return tab;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]) {
		taquins appli = new taquins();
		appli.setLocation(200, 250);
		appli.setSize(500, 500);
		appli.pack();
		appli.setVisible(true);
	}

}
