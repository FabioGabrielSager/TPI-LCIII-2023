package ar.edu.utn.frc.tup.lciii.modelos.Fichas;

import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;
@AllArgsConstructor
public class Peon extends Ficha {

    public Peon(ColorFicha color, Posicion posicion) {
        setPosicion(posicion);
        setColor(color);
        this.setTipoFicha(this.getClass().getSimpleName());
    }

    @Override
    public ArrayList<Posicion> movimientosPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        Posicion origen = getPosicion();
        ArrayList<Posicion> movimientos = new ArrayList<>();

        int direccion = (getColor() == ColorFicha.BLANCO) ? 1 : -1; // Dirección del movimiento según el color del peón

        // Movimiento hacia adelante
        int sig_pos_x = origen.getX();
        int sig_pos_y = origen.getY() + direccion;
        Posicion nueva = new Posicion(sig_pos_x, sig_pos_y);
        if (!aliados.contains(nueva) && !enemigos.contains(nueva)) {
            movimientos.add(nueva);

            // Movimiento inicial de dos casillas para el peón
            if ((getColor() == ColorFicha.BLANCO && origen.getY() == 1) || (getColor() == ColorFicha.NEGRO && origen.getY() == 6)) {
                sig_pos_y += direccion;
                nueva = new Posicion(sig_pos_x, sig_pos_y);
                if (!aliados.contains(nueva) && !enemigos.contains(nueva)) {
                    movimientos.add(nueva);
                }
            }
        }

        // Ataques en diagonal
        int[] dx = {-1, 1}; // Desplazamiento en las diagonales
        for (int i = 0; i < 2; i++) {
            sig_pos_x = origen.getX() + dx[i];
            sig_pos_y = origen.getY() + direccion;
            nueva = new Posicion(sig_pos_x, sig_pos_y);
            if (enemigos.contains(nueva)) {
                movimientos.add(nueva);
            }
        }

        return movimientos;
    }

    @Override
    public ArrayList<Posicion> ataquesPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        // En el caso del peón, los ataques posibles son los mismos que los movimientos posibles
        return movimientosPosibles(aliados, enemigos);
    }
}


