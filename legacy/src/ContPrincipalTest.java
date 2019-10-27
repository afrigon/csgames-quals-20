import static org.junit.Assert.*;
import java.util.List;
import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de test pour le contr�leur principal.
 * 
 * @author EquBolduc
 * @version 1.0
 */
public class ContPrincipalTest {
  private Planete p1, p2;

  @Before
  public void testContPrincipal() {
    p1 = new Planete(5000, new Vecteur(0, 0), 100, 0, Color.WHITE);
    p2 = new Planete(10000, new Vecteur(10, 10), 100, 0, Color.WHITE);

    ContPrincipal.reqInstance().viderCorps();
  }

  @Test
  public void testAjouterCorps() {
    ContPrincipal.reqInstance().ajouterCorps(p1);
    ContPrincipal.reqInstance().ajouterCorps(p2);
    List<Corps> liste = ContPrincipal.reqInstance().getCorps();
    assertEquals(2, liste.size());

    ContPrincipal.reqInstance().ajouterCorps(null);
    assertEquals(2, liste.size());
  }

  @Test
  public void testEnleverCorps() {
    ContPrincipal.reqInstance().ajouterCorps(p1);
    ContPrincipal.reqInstance().ajouterCorps(p2);
    ContPrincipal.reqInstance().enleverCorps(p1);
    List<Corps> liste = ContPrincipal.reqInstance().getCorps();
    assertEquals(1, liste.size());

    ContPrincipal.reqInstance().enleverCorps(null);
    assertEquals(1, liste.size());
  }

  @Test
  public void testViderCorps() {
    ContPrincipal.reqInstance().ajouterCorps(p1);
    ContPrincipal.reqInstance().ajouterCorps(p2);
    ContPrincipal.reqInstance().viderCorps();
    List<Corps> liste = ContPrincipal.reqInstance().getCorps();
    assertEquals(0, liste.size());
  }

  @Test
  public void testGetInstance() {
    ContPrincipal cp1 = ContPrincipal.reqInstance();
    ContPrincipal cp2 = ContPrincipal.reqInstance();

    assertEquals(cp1, cp2);
  }

  // Les m�thodes suivantes ne sont pas test�es, car elles d�pendent
  // de FXML.
  /*
   * @Test public void testInitialiser() { fail("Not yet implemented"); }
   * 
   * @Test public void testSelectionnerControleur() {
   * ContPrincipal.getInstance().selectionnerControleur(c1); }
   * 
   * @Test public void testAfficherVue() { fail("Not yet implemented"); }
   * 
   * @Test public void testUpdate() { fail("Not yet implemented"); }
   */
}
