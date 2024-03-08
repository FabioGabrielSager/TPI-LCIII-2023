package ar.edu.utn.frc.tup.lciii.servicios;

import ar.edu.utn.frc.tup.lciii.modelos.Jugador;

import java.util.List;

public interface JugadorService {
    List<Jugador> mostrarJugadores();
    Jugador crearJugador(String nombre);
    Jugador guardarJugador(Long id,String nombre);
}
