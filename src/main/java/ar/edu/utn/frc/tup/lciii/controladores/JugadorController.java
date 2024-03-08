package ar.edu.utn.frc.tup.lciii.controladores;

import ar.edu.utn.frc.tup.lciii.modelos.Jugador;
import ar.edu.utn.frc.tup.lciii.modelos.Partida;
import ar.edu.utn.frc.tup.lciii.servicios.JugadorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "Jugador")
public class JugadorController {
    @Autowired
    JugadorService jugadorService;
    @GetMapping("/jugador/mostrar_jugadores")
    public ResponseEntity<List<Jugador>> mostrarJugadores(){
        return ResponseEntity.ok(jugadorService.mostrarJugadores());
    }
    @PostMapping("/jugador/crear")
    public ResponseEntity<Jugador> crearJugador(@RequestParam String nombre){
        if (Objects.isNull(nombre) || nombre.isEmpty() || nombre.isBlank() ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Debe ingresar un nombre de usuario validos");
        }
        Jugador jugadorCreado = jugadorService.crearJugador(nombre);
        if (Objects.isNull(jugadorCreado)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Debe ingresar un nombre de usuario no usado ya");
        }
        else
            return ResponseEntity.ok(jugadorCreado);
    }
    @PutMapping("/jugador/guardar")
    public ResponseEntity<Jugador> guardarJugador(@RequestParam Long id, @RequestParam String nombre){
        Jugador jugadorGuardado = jugadorService.guardarJugador(id,nombre);
        if (Objects.isNull(jugadorGuardado)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Debe ingresar un nombre de usuario valido");
        }
        else
            return ResponseEntity.ok(jugadorGuardado);
    }
}
