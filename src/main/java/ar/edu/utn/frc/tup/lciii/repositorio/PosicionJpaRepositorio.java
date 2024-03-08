package ar.edu.utn.frc.tup.lciii.repositorio;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadPosicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PosicionJpaRepositorio extends JpaRepository<EntidadPosicion, Long> {
    Optional<EntidadPosicion> findFirstEntidadPosicionByXAndY(int x, int y);
}