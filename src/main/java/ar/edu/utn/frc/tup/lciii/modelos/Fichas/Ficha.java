package ar.edu.utn.frc.tup.lciii.modelos.Fichas;

import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Ficha {

    public static int tablero = 8;

    private Long id;
    private String tipoFicha;
    private ColorFicha color;
    private boolean viva = true;
    private Posicion posicion;

    public  ArrayList<Posicion> movimientosPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        return new ArrayList<>();
    }
    public  ArrayList<Posicion> ataquesPosibles(List<Posicion> aliados, List<Posicion> enemigos){
        return new ArrayList<>();
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Ficha)) {
            return false;
        }
        Ficha ficha = (Ficha)obj;
        return this.color == ficha.getColor() && this.posicion.getX()==ficha.getPosicion().getX()&& this.posicion.getY()==ficha.getPosicion().getY();
    }
}
