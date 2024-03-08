package ar.edu.utn.frc.tup.lciii.repositorio;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadJugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JugadorJpaRepositorio extends JpaRepository<EntidadJugador, Long> {
    Optional<EntidadJugador> findEntidadJugadorByNombre(String nombre);
}
