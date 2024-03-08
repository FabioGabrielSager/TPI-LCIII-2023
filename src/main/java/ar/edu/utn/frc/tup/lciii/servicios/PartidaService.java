package ar.edu.utn.frc.tup.lciii.servicios;

import ar.edu.utn.frc.tup.lciii.dtos.EstadoPartidaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.MovimientoDTO;

import java.util.List;

public interface PartidaService {
    EstadoPartidaDTO crearPartida(String nombreJ1, String nombreJ2);
    List<EstadoPartidaDTO> mostrarPartidasGuardas();
    EstadoPartidaDTO mostrarPartida(Long id);
    MovimientoDTO moverFicha(Long id, String mov);
}