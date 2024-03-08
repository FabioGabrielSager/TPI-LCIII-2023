package ar.edu.utn.frc.tup.lciii.modelos.Fichas;

import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Torre extends Ficha {

    public Torre(ColorFicha color, Posicion posicion) {
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
                {-1, 0}, {1, 0},
                {0, -1}, {0, 1}
        };

        for (int[] desplazamiento : desplazamientos) {
            int desplazamiento_y = desplazamiento[0];
            int desplazamiento_x = desplazamiento[1];

            int sig_pos_y = origen.getY() + desplazamiento_y;
            int sig_pos_x = origen.getX() + desplazamiento_x;

            while (sig_pos_y >= 0 && sig_pos_x >= 0 && sig_pos_y < Ficha.tablero && sig_pos_x < Ficha.tablero) {
                Posicion nueva = new Posicion(sig_pos_x, sig_pos_y);

                if(!aliados.contains(nueva) && !enemigos.contains(nueva)){
                    movimientos.add(nueva);
                }else {
                    break;
                }

                sig_pos_y += desplazamiento_y;
                sig_pos_x += desplazamiento_x;
            }
        }
        return movimientos;
    }

    @Override
    public ArrayList<Posicion> ataquesPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        Posicion origen = getPosicion();
        ArrayList<Posicion> ataques = new ArrayList<Posicion>();

        int[][] desplazamientos = {
                {-1, 0}, {1, 0},
                {0, -1}, {0, 1}
        };

        for (int[] desplazamiento : desplazamientos) {
            int desplazamiento_y = desplazamiento[0];
            int desplazamiento_x = desplazamiento[1];

            int sig_pos_y = origen.getY() + desplazamiento_y;
            int sig_pos_x = origen.getX() + desplazamiento_x;

            while (sig_pos_y >= 0 && sig_pos_x >= 0 && sig_pos_y < Ficha.tablero && sig_pos_x < Ficha.tablero) {
                Posicion nueva = new Posicion(sig_pos_x, sig_pos_y);

                if(aliados.contains(nueva)){
                    break;
                }
                if(enemigos.contains(nueva)){
                    ataques.add(nueva);
                }

                sig_pos_y += desplazamiento_y;
                sig_pos_x += desplazamiento_x;
            }
        }
        return ataques;
    }

}
