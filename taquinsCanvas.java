
import java.awt.*;

import java.awt.event.*;

@SuppressWarnings("serial")

public class taquinsCanvas extends Canvas implements MouseListener {

	// ATTRIBUTS

	Color couleurFond = Color.white;
	Color couleurCarre1 = Color.black;
	Color couleurCarre2 = new Color(50, 158, 187);
	Color couleurRectangle1 = new Color(251, 60, 111);
	Color couleurRectangle2 = new Color(255, 209, 6);
	

	int w = getSize().width;
	int h = getSize().height;

	int horiz = 4;
	int verti = 5;

	boolean dejaUnClick = false;
	P pieceAbouger;
	int premierX; // =j*50 
	int premierY; // =i*50
	
	// CONSTRUCTEUR
	public taquinsCanvas() {
		super();
		addMouseListener(this);
		// TODO Auto-generated constructor stub
	}

	public void paint(Graphics g) {
		System.out.println("début du dessin");
		// récupération des dimensions de l'application :
		//int w = getSize().width;
		//int h = getSize().height;

		// dessin des cases
		for (int i = 0; i < verti; i++) {
			for (int j = 0; j < horiz; j++) {
				int x = j * 50;
				int y = i * 50;
				switch (taquins.conf[i][j]) {
				case C1:
					g.setColor(couleurCarre1);
					g.fillRect(x, y, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x, y, x + 50, y);
					g.drawLine(x, y, x, y + 50);
					break;
				case C2:
					g.setColor(couleurCarre1);
					g.fillRect(x, y, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x, y, x + 50, y);
					g.drawLine(x + 50, y, x + 50, y + 50);
					break;
				case C3:
					g.setColor(couleurCarre1);
					g.fillRect(x, y, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x, y, x, y + 50);
					g.drawLine(x, y + 50, x + 50, y + 50);
					break;
				case C4:
					g.setColor(couleurCarre1);
					g.fillRect(x, y, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x + 50, y, x + 50, y + 50);
					g.drawLine(x, y + 50, x + 50, y + 50);
					break;
				case R1haut:
					g.setColor(couleurRectangle1);
					g.fillRect(x, y, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x, y, x, y + 50);
					g.drawLine(x + 50, y, x + 50, y + 50);
					g.drawLine(x, y, x + 50, y);
					break;
				case R1bas:
					g.setColor(couleurRectangle1);
					g.fillRect(j * 50, i * 50, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x, y, x, y + 50);
					g.drawLine(x + 50, y, x + 50, y + 50);
					g.drawLine(x, y + 50, x + 50, y + 50);
					break;
				case R2g:
					g.setColor(couleurRectangle2);
					g.fillRect(x, y, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x, y, x, y + 50); // gauche
					g.drawLine(x, y, x + 50, y); // haut
					g.drawLine(x, y + 50, x + 50, y + 50); // bas
					break;
				case R2d:
					g.setColor(couleurRectangle2);
					g.fillRect(j * 50, i * 50, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x + 50, y, x + 50, y + 50); // droite
					g.drawLine(x, y, x + 50, y); // haut
					g.drawLine(x, y + 50, x + 50, y + 50); // bas
					break;
				case C:
					g.setColor(couleurCarre2);
					g.fillRect(x, y, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x, y, x, y + 50); // gauche
					g.drawLine(x + 50, y, x + 50, y + 50); // droite
					g.drawLine(x, y, x + 50, y); // haut
					g.drawLine(x, y + 50, x + 50, y + 50); // bas
					break;
				case vide:
					g.setColor(Color.white);
					g.fillRect(x, y, 50, 50);
					g.setColor(Color.white);
					g.drawLine(x, y, x, y + 50); // gauche
					g.drawLine(x + 50, y, x + 50, y + 50); // droite
					g.drawLine(x, y, x + 50, y); // haut
					g.drawLine(x, y + 50, x + 50, y + 50); // bas
					break;
				default:
					g.setColor(couleurFond);
					g.drawRect(j * 50, i * 50, 50, 50);
					break;
				}
			}
		}
		// this.setBackground(couleurFond);

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void mouseClicked(MouseEvent evt) {
		
		
		System.out.println("on a cliqué");

		int x = evt.getX();
		int y = evt.getY();
		int w = 250;
		int h = 250;
		
				
		P[][] grille = taquins.conf.clone();
				
				if (dejaUnClick) {
					// on regarde où on se situe par rapport au premier click
					int i = premierY / 50 ;
					int j = premierX / 50 ;
					
					if (pieceAbouger.equals(P.C) ) {
						//dessus
						if ( x <= premierX+50 && x >= premierX && y<= premierY  && y >= premierY-50 ) {
							grille[i][j] = P.vide;
							grille[i - 1][j] = P.C;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						
						//sous
						if ( x <= premierX+50 && x>= premierX && y>= premierY+50 && y <= premierY+100) {
							grille[i][j] = P.vide;
							grille[i + 1][j] = P.C;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						
						//gauche
						if ( x <= premierX && x >= premierX-50 && y>= premierY && y <= premierY+50 ) {
							grille[i][j] = P.vide;
							grille[i][j -1] = P.C;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						
						//droite
						if ( x >= premierX+50 && x <= premierX +100 && y>= premierY && y <= premierY +50 )
						{
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.C;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						
					
					
					}
					
					
					if (pieceAbouger.equals(P.R2d) ) {
						//a gauche ou à droite
						// gauche
						if ( x >= premierX-100  && x <= premierX-50 && y>= premierY && y <= premierY +50 ){
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.R2d;
							grille[i][j -2] = P.R2g;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						
						//droite
						if ( x >= premierX+50 && x <= premierX +50 && y>= premierY && y <= premierY +50 )
						{
							grille[i][j] = P.R2g;
							grille[i][j-1] = P.vide;
							grille[i][j + 1] = P.R2d;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
					}
					
					if (pieceAbouger.equals(P.R2g) ) {
						//a gauche ou à droite
						// gauche
						if ( x >= premierX-50 && x <= premierX && y>= premierY && y <= premierY +50 ){
							grille[i][j] = P.R2d;
							grille[i][j - 1] = P.R2g;
							grille[i][j + 1] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						
						//droite
						if ( x >= premierX+100 && x <= premierX +150 && y>= premierY && y <= premierY +50 )
						{
							grille[i][j] = P.vide;
							grille[i][j+1] = P.R2g;
							grille[i][j +2] = P.R2d;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
					}
						
					if (pieceAbouger.equals(P.R1haut) ){
						//dessus
						if ( x >= premierX && x <= premierX+50 && y>= premierY-50  && y <= premierY ) {
							grille[i][j] = P.R1bas;
							grille[i - 1][j] = P.R1haut;
							grille[i + 1][j] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						
						//sous
						if ( x <= premierX+50 && x>= premierX && y>= premierY+100 && y <= premierY+150) {
							grille[i][j] = P.vide;
							grille[i + 1][j] = P.R1haut;
							grille[i + 2][j] = P.R1bas;
							taquins.compteur++;
							taquins.record.empiler(grille);
							
						}
						
					}
					
					if (pieceAbouger.equals(P.R1bas)) {
						//dessus
						if ( x >= premierX && x <= premierX+50 && y<= premierY-50  && y >= premierY-100 ) {
							grille[i][j] = P.vide;
							grille[i - 1][j] = P.R1bas;
							grille[i -2][j] = P.R1haut;
							taquins.compteur++;
							taquins.record.empiler(grille);
							
						}
						//sous
						if ( x <= premierX+50 && x>= premierX && y>= premierY+50 && y <= premierY+100) {
							grille[i][j] = P.R1haut;
							grille[i - 1][j] = P.vide;
							grille[i + 1][j] = P.R1bas;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						
					}
					
					dejaUnClick = false ;
					
					
				}
				

				else if (x <= w && y <= h) {
					int i = (int) y / 50;
					int j = (int) x / 50;

					
					P piece = taquins.conf[i][j];
					
					
					
					switch (piece) {
					case C1:
						if (taquins.getVoisinVide(i, j)[0] == "dessus" && taquins.getVoisinVide(i, j + 1)[0] == "dessus") {
							grille[i][j] = P.C3;
							grille[i][j + 1] = P.C4;
							grille[i + 1][j] = P.vide;
							grille[i + 1][j + 1] = P.vide;
							grille[i - 1][j] = P.C1;
							grille[i - 1][j + 1] = P.C2;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i + 1, j)[0] == "sous" && taquins.getVoisinVide(i + 1, j + 1)[0] == "sous") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.vide;
							grille[i + 1][j] = P.C1;
							grille[i + 1][j + 1] = P.C2;
							grille[i + 2][j] = P.C3;
							grille[i + 2][j + 1] = P.C4;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "gauche" && taquins.getVoisinVide(i - 1, j)[0] == "gauche") {
							grille[i][j] = P.C2;
							grille[i][j + 1] = P.vide;
							grille[i + 1][j] = P.C4;
							grille[i + 1][j + 1] = P.vide;
							grille[i][j - 1] = P.C1;
							grille[i + 1][j - 1] = P.C3;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j + 1)[0] == "droite" && taquins.getVoisinVide(i - 1, j + 1)[0] == "droite") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.C1;
							grille[i + 1][j] = P.vide;
							grille[i + 1][j + 1] = P.C3;
							grille[i][j + 2] = P.C2;
							grille[i + 1][j + 2] = P.C4;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						break;

					case C2:
						if (taquins.getVoisinVide(i, j)[0] == "dessus" && taquins.getVoisinVide(i, j - 1)[0] == "dessus")  {
							grille[i][j] = P.C4;
							grille[i][j - 1] = P.C3;
							grille[i + 1][j] = P.vide;
							grille[i + 1][j - 1] = P.vide;
							grille[i - 1][j] = P.C2;
							grille[i - 1][j - 1] = P.C1;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}

						if (taquins.getVoisinVide(i + 1, j)[0] == "sous" && taquins.getVoisinVide(i + 1, j - 1)[0] == "sous") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.vide;
							grille[i + 1][j] = P.C2;
							grille[i + 1][j - 1] = P.C1;
							grille[i + 2][j] = P.C4;
							grille[i + 2][j - 1] = P.C3;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j - 1)[0] == "gauche" && taquins.getVoisinVide(i - 1, j - 1)[0] == "gauche") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.C2;
							grille[i + 1][j] = P.vide;
							grille[i + 1][j - 1] = P.C4;
							grille[i][j - 2] = P.C1;
							grille[i + 1][j - 2] = P.C3;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "droite" && taquins.getVoisinVide(i + 1, j)[0] == "droite") {
							grille[i][j] = P.C1;
							grille[i][j + 1] = P.C2;
							grille[i + 1][j] = P.C3;
							grille[i + 1][j + 1] = P.C4;
							grille[i][j - 1] = P.vide;
							grille[i + 1][j - 1] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						break;

					case C3:
						if (taquins.getVoisinVide(i - 1, j)[0] == "dessus" && taquins.getVoisinVide(i - 1, j + 1)[0] == "dessus") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.vide;
							grille[i - 1][j] = P.C3;
							grille[i - 1][j + 1] = P.C4;
							grille[i - 2][j + 1] = P.C2;
							grille[i - 2][j] = P.C1;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}

						if (taquins.getVoisinVide(i, j)[0] == "sous" && taquins.getVoisinVide(i, j + 1)[0] == "sous") {
							grille[i][j] = P.C1;
							grille[i][j + 1] = P.C2;
							grille[i + 1][j] = P.C3;
							grille[i + 1][j + 1] = P.C4;
							grille[i - 1][j] = P.vide;
							grille[i - 1][j + 1] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);

						}
						if (taquins.getVoisinVide(i, j)[0] == "gauche" && taquins.getVoisinVide(i - 1, j)[0] == "gauche") {
							grille[i][j] = P.C4;
							grille[i][j - 1] = P.C3;
							grille[i - 1][j] = P.C2;
							grille[i - 1][j - 1] = P.C1;
							grille[i][j + 1] = P.vide;
							grille[i - 1][j + 1] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j + 1)[0] == "droite" && taquins.getVoisinVide(i - 1, j + 1)[0] == "droite") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.C3;
							grille[i - 1][j] = P.vide;
							grille[i - 1][j + 1] = P.C1;
							grille[i][j + 2] = P.C4;
							grille[i - 1][j + 2] = P.C2;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						break;

					case C4:
						if (taquins.getVoisinVide(i - 1, j)[0] == "dessus" && taquins.getVoisinVide(i - 1, j - 1)[0] == "dessus") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.vide;
							grille[i - 1][j] = P.C4;
							grille[i - 1][j - 1] = P.C3;
							grille[i - 2][j] = P.C2;
							grille[i - 2][j - 1] = P.C1;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}

						if (taquins.getVoisinVide(i, j)[0] == "sous" && taquins.getVoisinVide(i, j - 1)[0] == "sous") {
							grille[i][j] = P.C2;
							grille[i][j - 1] = P.C1;
							grille[i + 1][j] = P.C4;
							grille[i + 1][j - 1] = P.C3;
							grille[i - 1][j] = P.vide;
							grille[i - 1][j - 1] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j - 1)[0] == "gauche" && taquins.getVoisinVide(i - 1, j - 1)[0] == "gauche") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.C4;
							grille[i - 1][j] = P.vide;
							grille[i - 1][j - 1] = P.C2;
							grille[i - 1][j - 2] = P.C1;
							grille[i][j - 2] = P.C3;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "droite" && taquins.getVoisinVide(i - 1, j)[0] == "droite") {
							grille[i][j] = P.C3;
							grille[i][j + 1] = P.C4;
							grille[i - 1][j] = P.C1;
							grille[i - 1][j + 1] = P.C2;
							grille[i][j - 2] = P.vide;
							grille[i - 1][j - 2] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						break;

					case R1haut:
						if(taquins.getVoisinVide(i, j)[0] == "dessus"  && taquins.getVoisinVide(i+1, j)[0] == "sous") {
							pieceAbouger = P.R1haut;
							premierX = j*50;
							premierY = i*50;
							dejaUnClick=true;
							taquins.etat.setText("Cliquez sur la case vide où bouger le rectangle");
						}
						else if (taquins.getVoisinVide(i, j)[0] == "dessus" || taquins.getVoisinVide(i, j)[1] == "dessus") {
							grille[i][j] = P.R1bas;
							grille[i - 1][j] = P.R1haut;
							grille[i + 1][j] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						else if (taquins.getVoisinVide(i + 1, j)[0] == "sous" || taquins.getVoisinVide(i+1, j)[1] == "sous") {
							grille[i][j] = P.vide;
							grille[i + 1][j] = P.R1haut;
							grille[i + 2][j] = P.R1bas;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "gauche" && taquins.getVoisinVide(i + 1, j)[0] == "gauche") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.R1haut;
							grille[i + 1][j] = P.vide;
							grille[i + 1][j - 1] = P.R1bas;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "droite" && taquins.getVoisinVide(i + 1, j)[0] == "droite") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.R1haut;
							grille[i + 1][j] = P.vide;
							grille[i + 1][j + 1] = P.R1bas;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						break;

					case R1bas:
						if(taquins.getVoisinVide(i, j)[0] == "sous"  && taquins.getVoisinVide(i-1, j)[0] == "dessus") {
							pieceAbouger = P.R1bas;
							premierX = j*50;
							premierY = i*50;
							dejaUnClick=true;
							taquins.etat.setText("Cliquez sur la case vide où bouger le rectangle");
						}
						else if (taquins.getVoisinVide(i - 1, j)[0] == "dessus" || taquins.getVoisinVide(i - 1, j)[1] == "dessus") {
							grille[i][j] = P.vide;
							grille[i - 1][j] = P.R1bas;
							grille[i - 2][j] = P.R1haut;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						else if (taquins.getVoisinVide(i, j)[0] == "sous" || taquins.getVoisinVide(i, j)[1] == "sous") {
							grille[i][j] = P.R1haut;
							grille[i + 1][j] = P.R1bas;
							grille[i - 1][j] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "gauche" && taquins.getVoisinVide(i - 1, j)[0] == "gauche") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.R1bas;
							grille[i - 1][j] = P.vide;
							grille[i - 1][j - 1] = P.R1haut;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "droite" && taquins.getVoisinVide(i - 1, j)[0] == "droite") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.R1bas;
							grille[i - 1][j] = P.vide;
							grille[i - 1][j + 1] = P.R1haut;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						break;

					case R2g:
						if(taquins.getVoisinVide(i, j)[0] == "gauche"  && taquins.getVoisinVide(i, j+1)[0] == "droite") {
							pieceAbouger = P.R2g;
							premierX = j*50;
							premierY = i*50;
							dejaUnClick=true;
							taquins.etat.setText("Cliquez sur la case vide où bouger le rectangle");
						}
						else if (taquins.getVoisinVide(i, j)[0] == "dessus" && taquins.getVoisinVide(i, j + 1)[0] == "dessus") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.vide;
							grille[i - 1][j] = P.R2g;
							grille[i - 1][j + 1] = P.R2d;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						else if (taquins.getVoisinVide(i, j)[0] == "sous" && taquins.getVoisinVide(i, j + 1)[0] == "sous") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.vide;
							grille[i + 1][j] = P.R2g;
							grille[i + 1][j + 1] = P.R2d;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						else if (taquins.getVoisinVide(i, j)[0] == "gauche" || taquins.getVoisinVide(i, j)[1] == "gauche") {
							grille[i][j] = P.R2d;
							grille[i][j - 1] = P.R2g;
							grille[i][j + 1] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						else if (taquins.getVoisinVide(i, j + 1)[0] == "droite" || taquins.getVoisinVide(i, j + 1)[1] == "droite") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.R2g;
							grille[i][j + 2] = P.R2d;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						break;

					case R2d:
					
					if ((taquins.getVoisinVide(i,j)[0] == "droite") && (taquins.getVoisinVide(i,j-1)[0] == "gauche") ) {
						pieceAbouger= P.R2d;
						premierX = j*50;
						premierY = i*50;
						dejaUnClick=true;
						taquins.etat.setText("Cliquez sur la case vide où mettre le rectangle");
					}
					else if (taquins.getVoisinVide(i, j)[0] == "dessus" && taquins.getVoisinVide(i, j - 1)[0] == "dessus") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.vide;
							grille[i - 1][j] = P.R2d;
							grille[i - 1][j - 1] = P.R2g;
							taquins.compteur++;
							taquins.record.empiler(grille);
					}

					else if (taquins.getVoisinVide(i, j)[0] == "sous" && taquins.getVoisinVide(i, j - 1)[0] == "sous") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.vide;
							grille[i + 1][j] = P.R2d;
							grille[i + 1][j - 1] = P.R2g;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
					else if (taquins.getVoisinVide(i, j - 1)[0] == "gauche" || taquins.getVoisinVide(i, j - 1)[1] == "gauche") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.R2d;
							grille[i][j - 2] = P.R2g;
							taquins.compteur++;
							taquins.record.empiler(grille);
					}
					else if (taquins.getVoisinVide(i, j)[0] == "droite" || taquins.getVoisinVide(i, j - 1)[1] == "droite") {
							grille[i][j] = P.R2g;
							grille[i][j + 1] = P.R2d;
							grille[i][j - 1] = P.vide;
							taquins.compteur++;
							taquins.record.empiler(grille);
					}
					
						break;

					case C:
					
					if (!(taquins.getVoisinVide(i,j)[1] == null)) {
						pieceAbouger = P.C;
						premierX = j*50;
						premierY = i*50;
						dejaUnClick=true;
						taquins.etat.setText("Cliquez sur la case vide où mettre le carré");
					}
					else {
					
						if (taquins.getVoisinVide(i, j)[0] == "dessus") {
							grille[i][j] = P.vide;
							grille[i - 1][j] = P.C;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}

						if (taquins.getVoisinVide(i, j)[0] == "sous") {
							grille[i][j] = P.vide;
							grille[i + 1][j] = P.C;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "gauche") {
							grille[i][j] = P.vide;
							grille[i][j - 1] = P.C;
							taquins.compteur++;
							taquins.record.empiler(grille);
						}
						if (taquins.getVoisinVide(i, j)[0] == "droite") {
							grille[i][j] = P.vide;
							grille[i][j + 1] = P.C;
							taquins.compteur++;
							taquins.record.empiler(grille);
							System.out.println(taquins.record.renvoyerHauteur());
						}
						
					}
						break;
						default:
						
					}

					taquins.conf = grille;
					taquins.affichageCompteur.setText("Nombre de coups : " + taquins.compteur);
				}
				this.repaint();
				taquins.verifier();

		}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
