package ar.edu.utn.frc.tup.lciii.controladores;

import ar.edu.utn.frc.tup.lciii.controladores.Requests.PartidaRequest;
import ar.edu.utn.frc.tup.lciii.dtos.EstadoPartidaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.MovimientoDTO;
import ar.edu.utn.frc.tup.lciii.modelos.Partida;
import ar.edu.utn.frc.tup.lciii.servicios.imps.PartidaServiceImp;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@Tag(name = "Partida")
public class PartidaController {
    @Autowired
    PartidaServiceImp ajedrezServiceImp;

    @PostMapping("/partida/crear")
    public ResponseEntity<EstadoPartidaDTO> crearPartida(@RequestBody PartidaRequest request){
        String nombreBlanco = request.getNombreJugadorBlanco();
        String nombreNegro = request.getNombreJugadorNegro();
        if(Objects.equals(nombreBlanco, nombreNegro)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Los nombre de usuario de cada jugador no pueden ser iguales");
        }
        if (Objects.isNull(nombreBlanco) || Objects.isNull(nombreNegro) ||
                nombreBlanco.isEmpty() || nombreNegro.isEmpty() ||
                nombreBlanco.isBlank() || nombreNegro.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Debe ingresar dos nombres de usuario validos");
        }
        EstadoPartidaDTO partidaCreada = ajedrezServiceImp.crearPartida(nombreBlanco, nombreNegro);
        return ResponseEntity.ok(partidaCreada);
    }

    @GetMapping("/partida/mostrar_partidas")
    public ResponseEntity<List<EstadoPartidaDTO>> mostrarPartidas() {
        return ResponseEntity.ok(ajedrezServiceImp.mostrarPartidasGuardas()); }


    @GetMapping("/partida/mostrar_partida/{id}")
    public ResponseEntity<EstadoPartidaDTO> mostrarPartida(@PathVariable Long id){
        EstadoPartidaDTO estado = ajedrezServiceImp.mostrarPartida(id);
        if (Objects.isNull(estado)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Partida no encontrada");
        }
        else
            return ResponseEntity.ok(estado);
    }

    @PutMapping("/partida/mover_ficha")
    public ResponseEntity<MovimientoDTO> moverFicha(@RequestParam Long id, @RequestParam String mov){
        MovimientoDTO movimientoInfo = ajedrezServiceImp.moverFicha(id,mov);
        if(!movimientoInfo.getOk()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,movimientoInfo.getMensaje());
        }
        else return  ResponseEntity.ok(movimientoInfo);
    }

}