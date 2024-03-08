package ar.edu.utn.frc.tup.lciii.repositorios.jpa;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadFicha;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadJugador;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadPosicion;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.Alfil;
import ar.edu.utn.frc.tup.lciii.repositorio.FichaJpaRepositorio;
import ar.edu.utn.frc.tup.lciii.repositorio.JugadorJpaRepositorio;
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
public class JugadorJpaRepositorioTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JugadorJpaRepositorio jugadorJpaRepositorio;

    @Test
    public void findEntidadFichaByColorAndTipoFichaAndPosicionTes1(){
        EntidadJugador ej = new EntidadJugador();
        ej.setNombre("PruebaRepoJugador");

        entityManager.persist(ej);
        entityManager.flush();

        Optional<EntidadJugador> result = jugadorJpaRepositorio.findEntidadJugadorByNombre("PruebaRepoJugador");

        assertTrue(result.isPresent());
    }

    @Test
    public void findEntidadFichaByColorAndTipoFichaAndPosicionTest2(){
        EntidadJugador ej = new EntidadJugador();
        ej.setNombre("PruebaRepoJugador");

        EntidadJugador ej1 = new EntidadJugador();
        ej1.setNombre("PruebaRepoJugador2");


        entityManager.persist(ej);
        entityManager.persist(ej1);
        entityManager.flush();

        Optional<EntidadJugador> result = jugadorJpaRepositorio.findEntidadJugadorByNombre("PruebaRepoJugador");

        assertTrue(result.isPresent());
        assertEquals("PruebaRepoJugador", result.get().getNombre());
    }
}
