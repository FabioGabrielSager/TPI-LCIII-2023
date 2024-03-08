package ar.edu.utn.frc.tup.lciii.modelos;

import ar.edu.utn.frc.tup.lciii.modelos.Fichas.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Partida {

    private Long id;
    private String formatoH = "abcdefgh";
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;
    private List<Ficha> fichas;
    private ColorFicha turno;
    private Boolean terminada;
    private LocalDateTime creadaEn;
    public ColorFicha getTurno() {
        return turno;
    }

    public Partida(String nombreJugador1, String nombreJugador2){
        jugadorBlanco =new Jugador(nombreJugador1, ColorFicha.BLANCO);
        jugadorNegro =new Jugador(nombreJugador2, ColorFicha.NEGRO);
        fichas=new ArrayList<Ficha>() ;
        turno = ColorFicha.BLANCO;
        terminada = false;
        creadaEn = LocalDateTime.now();
        crearFichas();
    }

    void crearFichas(){
        fichas.add(FichaFactory.crearFicha('C',ColorFicha.NEGRO,1,7));
        fichas.add(FichaFactory.crearFicha('T',ColorFicha.NEGRO,0,7));
        fichas.add(FichaFactory.crearFicha('A',ColorFicha.NEGRO,2,7));
        fichas.add(FichaFactory.crearFicha('D',ColorFicha.NEGRO,3,7));
        fichas.add(FichaFactory.crearFicha('R',ColorFicha.NEGRO,4,7));
        fichas.add(FichaFactory.crearFicha('A',ColorFicha.NEGRO,5,7));
        fichas.add(FichaFactory.crearFicha('C',ColorFicha.NEGRO,6,7));
        fichas.add(FichaFactory.crearFicha('T',ColorFicha.NEGRO,7,7));
        for(int i=0; i<Ficha.tablero;i++){
            fichas.add(FichaFactory.crearFicha('P',ColorFicha.NEGRO,i,6));
        }

        fichas.add(FichaFactory.crearFicha('T',ColorFicha.BLANCO,0,0));
        fichas.add(FichaFactory.crearFicha('C',ColorFicha.BLANCO,1,0));
        fichas.add(FichaFactory.crearFicha('A',ColorFicha.BLANCO,2,0));
        fichas.add(FichaFactory.crearFicha('D',ColorFicha.BLANCO,3,0));
        fichas.add(FichaFactory.crearFicha('R',ColorFicha.BLANCO,4,0));
        fichas.add(FichaFactory.crearFicha('A',ColorFicha.BLANCO,5,0));
        fichas.add(FichaFactory.crearFicha('C',ColorFicha.BLANCO,6,0));
        fichas.add(FichaFactory.crearFicha('T',ColorFicha.BLANCO,7,0));
        for(int i=0; i<Ficha.tablero;i++){
            fichas.add(FichaFactory.crearFicha('P',ColorFicha.BLANCO,i,1));
        }
    }
    public Boolean moverFicha(Ficha fichaAMover, Posicion posicion) {
        if(terminada){
            return terminada;
        }
        List<Ficha> fichasCopia = new ArrayList<>(fichas);
        List<Ficha> fichasPropias = new ArrayList<>();
        List<Ficha> fichasEnemigas = new ArrayList<>();
        List<Posicion> posicionesPropias = new ArrayList<>();
        List<Posicion> posicionesEnemigas = new ArrayList<>();
        for (Ficha f : fichas) {
            if (f.isViva()) {
                if (f.getColor() == turno) {
                    fichasPropias.add(f);
                } else {
                    fichasEnemigas.add(f);
                }
            }
        }

        //1=Esa ficha es incorrecta (error de tipo de pieza, color, posicion)
        if (!fichasPropias.contains(fichaAMover))
            throw new ExepcionesPartida("Error al ingresar la ficha (compruebe el tipo o la posicion de ficha a mover)");
        int indexFichaAMover=fichasPropias.indexOf(fichaAMover);
        int indexFichaAMoverReal=fichasCopia.indexOf(fichaAMover);
        //2=No puede mover la pieza a su misma posicion
        if (fichaAMover.getPosicion().equals(posicion))
            throw new ExepcionesPartida("No puede mover la ficha a su misma posicion");
        //3=No puede mover la pieza a una posicion aliada
        for (Ficha f : fichasPropias) {
            posicionesPropias.add(f.getPosicion());
            if(posicion.equals(f.getPosicion()))
                throw new ExepcionesPartida("No puede mover la ficha a una posicion aliada");
        }

        int indexEnemigoMarcado = -1;
        int indexEnemigoMarcadoReal = -1;
        for (Ficha f : fichasEnemigas) {
            posicionesEnemigas.add(f.getPosicion());
            if(posicion.equals(f.getPosicion())) {
                indexEnemigoMarcado=fichasEnemigas.indexOf(f);
                indexEnemigoMarcadoReal=fichasCopia.indexOf(f);
            }
        }

        //4=El movimiento indicado es invalido
        //0=Correcto
        if(preguntarFicha(fichaAMover, posicion,posicionesPropias, posicionesEnemigas)){
            if(indexEnemigoMarcado!=-1){
                fichasEnemigas.remove(indexEnemigoMarcado);
                posicionesEnemigas.remove(indexEnemigoMarcado);
                fichasCopia.remove(indexEnemigoMarcadoReal);
            }
            fichasPropias.get(indexFichaAMover).setPosicion(posicion);
            posicionesPropias.set(indexFichaAMover,posicion);
            fichasCopia.get(indexFichaAMoverReal).setPosicion(posicion);

        }else throw new ExepcionesPartida("El movimiento indicado para la ficha es invalido");

        if(!comprobarEstadoJaque(fichasPropias, fichasEnemigas, posicionesPropias, posicionesEnemigas)){
            fichas = fichasCopia;
            if(comprobarJaqueMate(fichasEnemigas, fichasPropias, posicionesEnemigas,posicionesPropias)){
                terminada = true;
                return terminada;
            }else {
                turno = (turno==ColorFicha.NEGRO)?ColorFicha.BLANCO :ColorFicha.NEGRO;
                return terminada;
            }
        }else throw new ExepcionesPartida("Estas en estado de jaque");
    }
    public boolean preguntarFicha(Ficha ficha, Posicion posicion, List<Posicion> posicionesPropias, List<Posicion>  posicionesEnemigas){
        List<Posicion> p = ficha.movimientosPosibles(posicionesPropias,posicionesEnemigas);
        if(p.contains(posicion)){
            return true;
        } else if (ficha.ataquesPosibles(posicionesPropias,posicionesEnemigas).contains(posicion)) {
            return true;
        }else return false;
    }

    public String mostrarFicha(Ficha f){
        return f.getClass().getSimpleName().substring(0,1)
                + formatoH.charAt(f.getPosicion().getX())
                + (f.getPosicion().getY()+1);
    }
    public String mostrarListaDeFichas(ColorFicha colorFicha, boolean viva){
        List<Ficha> fichasMostrar = new ArrayList<>();
        for (Ficha f : fichas) {
            if (viva == f.isViva() && colorFicha == f.getColor()) {
                fichasMostrar.add(f);
            }
        }
        StringBuilder salida = new StringBuilder();
        if(!fichasMostrar.isEmpty()){
            for (Ficha f : fichasMostrar) {
                salida.append(mostrarFicha(f)+" ");
            }
        }
        else salida.append("Ninguna");
        return salida.toString();
    }

    public boolean comprobarEstadoJaque(List<Ficha> fichasPropias, List<Ficha> fichasEnemigas,
                                        List<Posicion> posicionesPropias, List<Posicion> posicionesEnemigas) {
        Rey rey = null;


        for (Ficha fichaPropia : fichasPropias) {
            boolean t = fichaPropia.getTipoFicha().equals("Rey");
            if (t ) {
                rey = (Rey) fichaPropia;
                break;
            }
        }
        for (Ficha fichaEnemiga : fichasEnemigas) {
            ArrayList<Posicion> ataquesPosiblesPorFicha = fichaEnemiga.ataquesPosibles(posicionesEnemigas, posicionesPropias);
            for (Posicion ataque : ataquesPosiblesPorFicha) {
                if (ataque.equals(rey.getPosicion())) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean comprobarJaqueMate(List<Ficha> fichasPropias, List<Ficha> fichasEnemigas,
                                      List<Posicion> posicionesPropias, List<Posicion> posicionesEnemigas) {
        if (comprobarEstadoJaque(fichasPropias, fichasEnemigas, posicionesPropias, posicionesEnemigas)) {
            for (Ficha fichaPropia : fichasPropias) {
                List<Posicion> movimientosPosibles = fichaPropia.movimientosPosibles(posicionesPropias, posicionesEnemigas);

                for (Posicion movimiento : movimientosPosibles) {
                    // Hacer una copia temporal de las listas para simular el movimiento
                    List<Ficha> fichasPropiasTemp = new ArrayList<>(fichasPropias);
                    List<Posicion> posicionesPropiasTemp = new ArrayList<>(posicionesPropias);

                    // Actualizar la posición de la ficha
                    int indiceFicha = fichasPropiasTemp.indexOf(fichaPropia);
                    posicionesPropiasTemp.set(indiceFicha, movimiento);

                    // Comprobar si el movimiento evita el jaque
                    if (!comprobarEstadoJaque(fichasPropiasTemp, fichasEnemigas, posicionesPropiasTemp, posicionesEnemigas)) {
                        return false; // No es jaque mate
                    }
                }
            }

            return true; // Es jaque mate
        }

        return false; // No es jaque mate
    }
}