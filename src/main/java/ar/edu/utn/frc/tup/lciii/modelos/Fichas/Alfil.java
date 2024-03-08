package ar.edu.utn.frc.tup.lciii.modelos.Fichas;
import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Alfil extends Ficha {
    

    public Alfil(ColorFicha color, Posicion posicion) {
        setPosicion(posicion);
        setColor(color);
        this.setTipoFicha(this.getClass().getSimpleName());
    }

    @Override
    public ArrayList<Posicion> movimientosPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        Posicion origen = getPosicion();
        ArrayList<Posicion> movimientos = new ArrayList<>();

        int[] dx = {-1, 1, -1, 1}; // Desplazamiento en las diagonales
        int[] dy = {-1, -1, 1, 1};

        for (int i = 0; i < 4; i++) {
            int sig_pos_x = origen.getX() + dx[i];
            int sig_pos_y = origen.getY() + dy[i];

            while (sig_pos_y >= 0 && sig_pos_x >= 0 && sig_pos_y < Ficha.tablero && sig_pos_x < Ficha.tablero) {
                Posicion nueva = new Posicion(sig_pos_x, sig_pos_y);
                if (!aliados.contains(nueva) && !enemigos.contains(nueva)) {
                    movimientos.add(nueva);
                } else {
                    break;
                }
                sig_pos_x += dx[i];
                sig_pos_y += dy[i];
            }
        }

        return movimientos;
    }

    @Override
    public ArrayList<Posicion> ataquesPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        Posicion origen = getPosicion();
        ArrayList<Posicion> ataques = new ArrayList<>();

        int[] dx = {-1, 1, -1, 1}; // Desplazamiento en las diagonales
        int[] dy = {-1, -1, 1, 1};

        for (int i = 0; i < 4; i++) {
            int sig_pos_x = origen.getX() + dx[i];
            int sig_pos_y = origen.getY() + dy[i];

            while (sig_pos_y >= 0 && sig_pos_x >= 0 && sig_pos_y < Ficha.tablero && sig_pos_x < Ficha.tablero) {
                Posicion nueva = new Posicion(sig_pos_x, sig_pos_y);
                if (aliados.contains(nueva)) {
                    break;
                }
                if (enemigos.contains(nueva)) {
                    ataques.add(nueva);
                }
                sig_pos_x += dx[i];
                sig_pos_y += dy[i];
            }
        }

        return ataques;
    }
}


