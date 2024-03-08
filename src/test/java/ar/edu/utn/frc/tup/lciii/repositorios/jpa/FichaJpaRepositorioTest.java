package ar.edu.utn.frc.tup.lciii.repositorios.jpa;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadFicha;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadPosicion;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.Alfil;
import ar.edu.utn.frc.tup.lciii.repositorio.FichaJpaRepositorio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class FichaJpaRepositorioTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private FichaJpaRepositorio fichaJpaRepository;

    @Test
    public void findEntidadFichaByColorAndTipoFichaAndPosicionTest(){
        EntidadFicha ef = new EntidadFicha();
        ef.setId(0L);
        ef.setTipoFicha(Alfil.class.getSimpleName());
        ef.setColor("BLANCO");
        EntidadPosicion posicion = entityManager.merge(new EntidadPosicion(0L, 0, 0));
        ef.setPosicion(posicion);
        ef.setViva(true);

        entityManager.merge(ef.getPosicion());
        entityManager.merge(ef);
        entityManager.flush();

        Optional<EntidadFicha> result = fichaJpaRepository.findFirstEntidadFichaByColorAndTipoFichaAndPosicion(
                "BLANCO", "Alfil", posicion
        );

        assertTrue(result.isPresent());
    }
}