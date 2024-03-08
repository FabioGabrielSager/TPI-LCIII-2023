package ar.edu.utn.frc.tup.lciii.controladores.Requests;

import ar.edu.utn.frc.tup.lciii.modelos.Jugador;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class PartidaRequest {
    @JsonProperty("nombreJugadorBlanco")
    private String nombreJugadorBlanco;
    @JsonProperty("nombreJugadorNegro")
    private String nombreJugadorNegro;

    // Getters y setters
}
