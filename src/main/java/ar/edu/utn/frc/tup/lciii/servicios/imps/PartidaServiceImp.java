package ar.edu.utn.frc.tup.lciii.servicios.imps;

import ar.edu.utn.frc.tup.lciii.dtos.EstadoPartidaDTO;
import ar.edu.utn.frc.tup.lciii.dtos.MovimientoDTO;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadFicha;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadJugador;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadPartida;
import ar.edu.utn.frc.tup.lciii.entidades.EntidadPosicion;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.ColorFicha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.Ficha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.FichaFactory;
import ar.edu.utn.frc.tup.lciii.modelos.Jugador;
import ar.edu.utn.frc.tup.lciii.modelos.Partida;
import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import ar.edu.utn.frc.tup.lciii.repositorio.FichaJpaRepositorio;
import ar.edu.utn.frc.tup.lciii.repositorio.JugadorJpaRepositorio;
import ar.edu.utn.frc.tup.lciii.repositorio.PartidaJpaRepositorio;
import ar.edu.utn.frc.tup.lciii.repositorio.PosicionJpaRepositorio;
import ar.edu.utn.frc.tup.lciii.servicios.PartidaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PartidaServiceImp implements PartidaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PartidaJpaRepositorio partidaJpaRepositorio;

    @Autowired
    private JugadorJpaRepositorio jugadorJpaRepositorio;

    @Autowired
    private PosicionJpaRepositorio posicionJpaRepositorio;

    @Autowired
    private FichaJpaRepositorio fichaRepository;

    @Override
    public EstadoPartidaDTO crearPartida(String nombreJ1, String nombreJ2) {
        Partida partidaNueva = new Partida(nombreJ1, nombreJ2);
        guardarPartida(partidaNueva, true);
        return mostrarEstado(partidaNueva);
    }

    private Partida guardarPartida(Partida partida, boolean nueva) {
        if (!Objects.isNull(partida)) {

            Jugador jugador1 = partida.getJugadorBlanco();
            Jugador jugador2 = partida.getJugadorNegro();
            EntidadPartida entidadPartida = modelMapper.map(partida, EntidadPartida.class);
            if (nueva) {
                // Consulto si el jugador de la partida actual ya existe en la base de datos. Si es así, no guardo un nuevo
                // jugador en la BD y uso el recuperado.
                Optional<EntidadJugador> j1Optional = jugadorJpaRepositorio.findEntidadJugadorByNombre(jugador1.getNombre());
                Optional<EntidadJugador> j2Optional = jugadorJpaRepositorio.findEntidadJugadorByNombre(jugador2.getNombre());

                EntidadJugador entidadJugador1 =
                        j1Optional.orElseGet(() ->
                                jugadorJpaRepositorio.save(modelMapper.map(jugador1, EntidadJugador.class)));
                EntidadJugador entidadJugador2 =
                        j2Optional.orElseGet(() ->
                                jugadorJpaRepositorio.save(modelMapper.map(jugador2, EntidadJugador.class)));

                entidadPartida.setJugadorBlanco(entidadJugador1);
                entidadPartida.setJugadorNegro(entidadJugador2);


                for (int i = 0; i < entidadPartida.getFichas().size(); i++) {
                    EntidadFicha f = entidadPartida.getFichas().get(i);
                    EntidadPosicion p = f.getPosicion();
                    Optional<EntidadPosicion> ePOptional = posicionJpaRepositorio.findFirstEntidadPosicionByXAndY(p.getX(),
                            p.getY());

                    if (ePOptional.isPresent())
                        p = ePOptional.get();
                    else
                        p = posicionJpaRepositorio.save(p);

                    Optional<EntidadFicha> eFOptional =
                            fichaRepository.findFirstEntidadFichaByColorAndTipoFichaAndPosicion(f.getColor(), f.getTipoFicha(), p);

                    if (eFOptional.isPresent())
                        f = eFOptional.get();
                    else
                        f = fichaRepository.save(f);

                    f.setPosicion(p);


                    entidadPartida.getFichas().set(i, f);
                }
            }
            entidadPartida = partidaJpaRepositorio.save(entidadPartida);
            Partida partidaGuardada = modelMapper.map(entidadPartida, Partida.class);
            partidaGuardada.getJugadorBlanco().setColor(ColorFicha.BLANCO);
            partidaGuardada.getJugadorNegro().setColor(ColorFicha.NEGRO);
            return partidaGuardada;
        } else {
            return null;
        }
    }

    @Override
    public List<EstadoPartidaDTO> mostrarPartidasGuardas() {
        List<EstadoPartidaDTO> partidas = new ArrayList<>();
        List<EntidadPartida> entidadesPartida = partidaJpaRepositorio.findAll();
        for (EntidadPartida ep : entidadesPartida) {
            Partida partida = modelMapper.map(ep, Partida.class);
            partida.getJugadorBlanco().setColor(ColorFicha.BLANCO);
            partida.getJugadorNegro().setColor(ColorFicha.NEGRO);
            partidas.add(mostrarEstado(partida));
        }
        return partidas;
    }


    @Override
    public EstadoPartidaDTO mostrarPartida(Long id) {
        Partida partida = cargarPartida(id);
        if (!Objects.isNull(partida))
            return mostrarEstado(partida);
        else
            return null;
    }

    private EstadoPartidaDTO mostrarEstado(Partida partida) {
        EstadoPartidaDTO estado = new EstadoPartidaDTO();
        estado.setId(partida.getId());
        estado.setTerminada(partida.getTerminada());
        estado.setJugadorBlanco(partida.getJugadorBlanco());
        estado.setJugadorNegro(partida.getJugadorNegro());
        estado.setTurnoActual(partida.getTurno());
        estado.setCreadaEn(partida.getCreadaEn().format(DateTimeFormatter.ISO_DATE_TIME));
        estado.setFichasEnTableroJugadorBlanco(partida.mostrarListaDeFichas(ColorFicha.BLANCO, true));
        estado.setFichasEnTableroJugadorNegro(partida.mostrarListaDeFichas(ColorFicha.NEGRO, true));
        estado.setFichasEliminadasJugadorBlanco(partida.mostrarListaDeFichas(ColorFicha.BLANCO, false));
        estado.setFichasEliminadasJugadorNegro(partida.mostrarListaDeFichas(ColorFicha.NEGRO, false));
        return estado;
    }

    @Override
    public MovimientoDTO moverFicha(Long id, String mov) {
        String regex = "^[RDPCTA][a-hA-H][1-8]\\s[a-hA-H][1-8]$";
        Partida partida = cargarPartida(id);
        if (Objects.isNull(partida))
            return new MovimientoDTO(false, "Partida no encontrada");
        if (mov.matches(regex)) {
            ColorFicha color = partida.getTurno();
            int posX = partida.getFormatoH().indexOf(Character.toLowerCase(mov.charAt(1)));
            int posY = Character.getNumericValue(mov.charAt(2)) - 1;
            int posXnva = partida.getFormatoH().indexOf(Character.toLowerCase(mov.charAt(4)));
            int posYnva = Character.getNumericValue(mov.charAt(5)) - 1;
            Ficha ficha = FichaFactory.crearFicha(mov.charAt(0), color, posX, posY);
            Posicion posNva = new Posicion(posXnva, posYnva);

            try {
                boolean res = partida.moverFicha(ficha, posNva);
                guardarPartida(partida, false);
                if(res)
                    return new MovimientoDTO(true,"Gano el jugador: "+partida.getTurno());
                else
                    return new MovimientoDTO(true,"Correcto");
            }catch (Exception ex) {
                return new MovimientoDTO(false,ex.getMessage());
            }
        } else
            return new MovimientoDTO(false, "Formato no valido, debe ser : (R,D,P,C,T,A)+(A-H)+(1-8)+(espacio)+(A-H)+(1-8)");
    }
    private Partida cargarPartida(Long id) {
        EntidadPartida entidadPartida;
        try {
            entidadPartida = partidaJpaRepositorio.getReferenceById(id);
            Partida partida = modelMapper.map(entidadPartida, Partida.class);
            partida.getJugadorBlanco().setColor(ColorFicha.BLANCO);
            partida.getJugadorNegro().setColor(ColorFicha.NEGRO);
            return partida;
        } catch (Exception ex) {
            return null;
        }

    }
}