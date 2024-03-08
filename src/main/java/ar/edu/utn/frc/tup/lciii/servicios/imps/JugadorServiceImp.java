package ar.edu.utn.frc.tup.lciii.servicios.imps;

import ar.edu.utn.frc.tup.lciii.entidades.EntidadJugador;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadPartida;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.ColorFicha;
import ar.edu.utn.frc.tup.lciii.modelos.Jugador;
import ar.edu.utn.frc.tup.lciii.modelos.Partida;
import ar.edu.utn.frc.tup.lciii.repositorio.JugadorJpaRepositorio;
import ar.edu.utn.frc.tup.lciii.servicios.JugadorService;
import ar.edu.utn.frc.tup.lciii.servicios.PartidaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JugadorServiceImp implements JugadorService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JugadorJpaRepositorio jugadorJpaRepositorio;
    @Override
    public List<Jugador> mostrarJugadores(){
        try {
            List<EntidadJugador> entidadJugadores = jugadorJpaRepositorio.findAll();
            entidadJugadores.sort(Comparator.comparingInt(o -> o.getId().intValue()));
            List<Jugador> jugadores = modelMapper.map(entidadJugadores, List.class);
            return jugadores;
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public Jugador crearJugador(String nombre){
        try {
            Optional<EntidadJugador> jugadorOriginal = jugadorJpaRepositorio.findEntidadJugadorByNombre(nombre);
            if(jugadorOriginal.isPresent()) return null;
            Jugador jugador = new Jugador(nombre,null);
            EntidadJugador entidadJugador = jugadorJpaRepositorio.save(modelMapper.map(jugador, EntidadJugador.class));
            return modelMapper.map(entidadJugador, Jugador.class);
        } catch (Exception ex) {
            return null;
        }
    }
    @Override
    public Jugador guardarJugador(Long id, String nombre){
        try {
            EntidadJugador entidadJugador = jugadorJpaRepositorio.getReferenceById(id);
            if(entidadJugador==null) return null;
            Optional<EntidadJugador> jugadorOriginal = jugadorJpaRepositorio.findEntidadJugadorByNombre(nombre);
            if(jugadorOriginal.isPresent()) return null;
            entidadJugador.setNombre(nombre);
            jugadorJpaRepositorio.save(entidadJugador);
            return modelMapper.map(entidadJugador, Jugador.class);
        } catch (Exception ex) {
            return null;
        }
    }
}
