import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Classe de tests pour MoteurPhysique.
 * 
 * @author �quipe Bolduc
 */
public class MoteurPhysiqueTest {
  private List<Corps> corps1, corps2, corps3, corps4;
  private MoteurPhysique moteur1, moteur2;
  private Vaisseau vaisseau1, vaisseau2, vaisseau3;
  private Tete tete1;

  @Before
  public void creerCorps() {
    moteur1 = new MoteurPhysique();

    corps1 = new ArrayList<>();
    vaisseau1 = new Vaisseau(100, new Vecteur(0, 130), new Vecteur(10, 0));
    corps1.add(vaisseau1);
    corps1.add(new Planete(15707.96327, new Vecteur(0, 0), 100, 0, Color.WHITE));
    corps1.add(null);

    corps2 = new ArrayList<>();
    vaisseau2 = new Vaisseau(100, new Vecteur(65, 10), new Vecteur(10, 0));
    corps2.add(vaisseau2);
    corps2.add(new Planete(100, new Vecteur(210, 10), 100, 0, Color.WHITE));

    corps3 = new ArrayList<>();
    vaisseau3 = new Vaisseau(100, new Vecteur(65, 10), new Vecteur(10, 0));
    corps3.add(new Planete(100, new Vecteur(210, 10), 100, 0, Color.WHITE));
    corps3.add(vaisseau3);

    moteur2 = new MoteurPhysique();

    corps4 = new ArrayList<>();
    tete1 = new Tete(100, 20, new Vecteur(550, 500), new Vecteur(50, 50));
    corps4.add(tete1);
    corps4.add(null);
  }

  @Test
  public void testUpdate() {
    moteur1.update(corps1, 0.5);
    assertEquals(vaisseau1.reqPositionX(), 5.0, 0.001);
    moteur1.update(corps1, 0.5);
    assertEquals(vaisseau1.reqPositionX(), 10.0, 0.001);
    moteur1.update(corps1, 0);
    assertEquals(vaisseau1.reqPositionX(), 10.0, 0.001);
    moteur1.update(null, 1);
    assertEquals(vaisseau1.reqPositionX(), 10.0, 0.001);
    moteur1.update(null, 0);
    assertEquals(vaisseau1.reqPositionX(), 10.0, 0.001);

    assertEquals(vaisseau2.reqVitesse().getX(), 10, 0.001);
    moteur1.update(corps2, 2);
    assertEquals(vaisseau2.reqVitesse().getX(), 10, 0.001);
    moteur1.update(corps2, 0.5);
    assertEquals(vaisseau2.reqVitesse().getX(), 0, 0.001);

    assertEquals(vaisseau3.reqVitesse().getX(), 10, 0.001);
    moteur1.update(corps3, 2);
    assertEquals(vaisseau3.reqVitesse().getX(), 10, 0.001);
    moteur1.update(corps3, 0.5);
    assertEquals(vaisseau3.reqVitesse().getX(), 0, 0.001);

    moteur2.update(corps4, 1.0);
    assertEquals(tete1.reqVitesse().getX(), -50, 0.001);
    moteur2.update(corps4, 1.0);
    assertEquals(tete1.reqVitesse().getY(), -50, 0.001);
    moteur2.update(corps4, 30.0);
    assertEquals(tete1.reqVitesse().getX(), 50, 0.001);
    moteur2.update(corps4, 1.0);
    assertEquals(tete1.reqVitesse().getY(), 50, 0.001);
  }

  @Test
  public void testGetTailleEcranX() {
    assertEquals(moteur1.getTailleEcranX() == 600, true);
  }

  @Test
  public void testGetTailleEcranY() {
    assertEquals(moteur1.getTailleEcranY() == 600, true);
  }

  @Test
  public void setTailleEcranX() {
    assertEquals(moteur1.getTailleEcranX() == 600, true);
    moteur1.setTailleEcranX(150);
    assertEquals(moteur1.getTailleEcranX() == 150, true);
    moteur1.setTailleEcranX(-100);
    assertEquals(moteur1.getTailleEcranX() == 150, true);
  }

  @Test
  public void setTailleEcranY() {
    assertEquals(moteur1.getTailleEcranY() == 600, true);
    moteur1.setTailleEcranY(150);
    assertEquals(moteur1.getTailleEcranY() == 150, true);
    moteur1.setTailleEcranY(-100);
    assertEquals(moteur1.getTailleEcranY() == 150, true);
  }
}
