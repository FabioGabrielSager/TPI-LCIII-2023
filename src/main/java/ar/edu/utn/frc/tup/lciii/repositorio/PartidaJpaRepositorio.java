package ar.edu.utn.frc.tup.lciii.repositorio;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadPartida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaJpaRepositorio extends JpaRepository<EntidadPartida, Long> {
}

