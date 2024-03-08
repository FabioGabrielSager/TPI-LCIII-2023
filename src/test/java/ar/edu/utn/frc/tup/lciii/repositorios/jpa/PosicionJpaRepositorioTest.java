package ar.edu.utn.frc.tup.lciii.repositorios.jpa;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadPosicion;
import ar.edu.utn.frc.tup.lciii.repositorio.PosicionJpaRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class PosicionJpaRepositorioTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PosicionJpaRepositorio posicionJpaRepositorio;

    @Test
    public void findEntidadFichaByColorAndTipoFichaAndPosicionTest1(){
        EntidadPosicion ep = new EntidadPosicion();
        ep.setX(0);
        ep.setY(0);

        entityManager.persist(ep);
        entityManager.flush();

        Optional<EntidadPosicion> result = posicionJpaRepositorio.findFirstEntidadPosicionByXAndY(0, 0);

        assertTrue(result.isPresent());
    }

    @Test
    public void findEntidadFichaByColorAndTipoFichaAndPosicionTest2(){
        EntidadPosicion ep1 = new EntidadPosicion();
        ep1.setX(0);
        ep1.setY(0);

        EntidadPosicion ep2 = new EntidadPosicion();
        ep2.setX(1);
        ep2.setY(1);

        entityManager.persist(ep1);
        entityManager.persist(ep2);
        entityManager.flush();

        Optional<EntidadPosicion> result = posicionJpaRepositorio.findFirstEntidadPosicionByXAndY(0, 0);

        assertTrue(result.isPresent());
        assertEquals(0, result.get().getY());

        assertEquals(0, result.get().getX());
    }
}