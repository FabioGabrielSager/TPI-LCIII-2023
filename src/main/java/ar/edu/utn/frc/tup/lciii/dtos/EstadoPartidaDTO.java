package ar.edu.utn.frc.tup.lciii.dtos;

import ar.edu.utn.frc.tup.lciii.modelos.Fichas.ColorFicha;
import ar.edu.utn.frc.tup.lciii.modelos.Jugador;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class EstadoPartidaDTO {
    Long id;
    boolean terminada;
    ColorFicha turnoActual;
    Jugador jugadorNegro;
    Jugador jugadorBlanco;
    String creadaEn;
    String fichasEnTableroJugadorNegro;
    String fichasEliminadasJugadorNegro;
    String fichasEnTableroJugadorBlanco;
    String fichasEliminadasJugadorBlanco;
}
