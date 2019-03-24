import java.awt.event.*;


/** Classe con�ue pour �couter les �v�nements "fermeture fen�tre" afin
    d'arr�ter proprement l'application lorsqu'on ferme la fen�tre. */

public class EcouteurPourFermetureFenetre extends WindowAdapter {
  // Remarque : faire h�riter cette classe de WindowAdapter permet d'implanter
  //            l'interface WindowListener sans avoir � red�finir TOUTES 
  //            les m�thodes de l'interface.
    
  public void windowClosing(WindowEvent w) {
    w.getWindow().dispose() ;  // Lib�ration des ressources associ�es � la Frame
    System.exit(0);  // Sortie du programme  avec code d'erreur 0 (pas d'erreur)
  }
  
  
}
