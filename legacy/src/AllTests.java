import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Classe permettant d'ex�cuter tous les tests.
 * 
 * @author EquBolduc
 * @version 1.0
 */
@RunWith(Suite.class)
@SuiteClasses({ ContPrincipalTest.class, PlaneteTest.class, TeteTest.class, VecteurTest.class,
                ObjetSpatialTest.class, VaisseauTest.class, CameraTest.class,
                MoteurPhysiqueTest.class, VaisseauJoueurTest.class, ObjectifRayonTest.class,
                NiveauTest.class })
public class AllTests {

}
