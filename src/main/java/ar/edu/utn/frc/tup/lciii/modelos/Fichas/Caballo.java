package ar.edu.utn.frc.tup.lciii.modelos.Fichas;

import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Caballo extends Ficha {
    public Caballo(ColorFicha color, Posicion posicion) {
        setPosicion(posicion);
        setColor(color);
        this.setTipoFicha(this.getClass().getSimpleName());
    }

    @Override
    public ArrayList<Posicion> movimientosPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        Posicion origen = getPosicion();
        ArrayList<Posicion> movimientos = new ArrayList<Posicion>();
        //prox casilleros posibles
        int[][] desplazamientos = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };
        for (int[] desplazamiento : desplazamientos) {
            int sig_pos_y = origen.getY() + desplazamiento[0];
            int sig_pos_x = origen.getX() + desplazamiento[1];

            if (sig_pos_y >= 0 && sig_pos_x >= 0 && sig_pos_y < Ficha.tablero && sig_pos_x < Ficha.tablero) {
                Posicion nueva = new Posicion(sig_pos_x, sig_pos_y);
                if (!aliados.contains(nueva) && !enemigos.contains(nueva)) {
                    movimientos.add(nueva);
                }
            }
        }
        return movimientos;
    }

    @Override
    public  ArrayList<Posicion> ataquesPosibles(List<Posicion> aliados, List<Posicion> enemigos){
        Posicion origen = getPosicion();
        ArrayList<Posicion> ataques = new ArrayList<Posicion>();

        int[][] desplazamientos = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
        };

        for (int[] desplazamiento : desplazamientos) {
            int sig_pos_y = origen.getY() + desplazamiento[0];
            int sig_pos_x = origen.getX() + desplazamiento[1];

            if (sig_pos_y >= 0 && sig_pos_x >= 0 && sig_pos_y < Ficha.tablero && sig_pos_x < Ficha.tablero) {
                Posicion nueva = new Posicion(sig_pos_x, sig_pos_y);
                if (enemigos.contains(nueva)) {
                    ataques.add(nueva);
                }
            }
        }
        return ataques;
    }
}
