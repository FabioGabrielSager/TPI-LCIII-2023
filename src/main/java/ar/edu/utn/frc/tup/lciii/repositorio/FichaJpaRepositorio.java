package ar.edu.utn.frc.tup.lciii.repositorio;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadFicha;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadPosicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FichaJpaRepositorio extends JpaRepository<EntidadFicha, Long> {
    Optional<EntidadFicha> findFirstEntidadFichaByColorAndTipoFichaAndPosicion(String color, String TipoFicha, EntidadPosicion posicion);
}