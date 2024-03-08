package ar.edu.utn.frc.tup.lciii.modelos.Fichas;

import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class Dama extends Ficha{

    public Dama(ColorFicha color, Posicion posicion) {
        setPosicion(posicion);
        setColor(color);
        this.setTipoFicha(this.getClass().getSimpleName());
    }

    @Override
    public ArrayList<Posicion> movimientosPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        Posicion origen = getPosicion();
        ArrayList<Posicion> movimientos = new ArrayList<>();

        for (int i=-1; i<=1;i++) {
            for (int j=-1; j<=1;j++) {
                if (i==0 && j==0){
                    continue;
                }
                int sig_pos_y = origen.getY() + i;
                int sig_pos_x = origen.getX() + j;
                while(sig_pos_y>=0 && sig_pos_x>=0 && sig_pos_y<Ficha.tablero && sig_pos_x<Ficha.tablero){
                    Posicion nueva = new Posicion(sig_pos_x,sig_pos_y);
                    if(!aliados.contains(nueva) && !enemigos.contains(nueva)){
                        movimientos.add(nueva);
                    }else {
                        break;
                    }
                    sig_pos_y += i;
                    sig_pos_x += j;
                }
            }
        }
        return movimientos;
    }

    @Override
    public ArrayList<Posicion> ataquesPosibles(List<Posicion> aliados, List<Posicion> enemigos) {
        Posicion origen = getPosicion();
        ArrayList<Posicion> movimientos = new ArrayList<>();

        for (int i=-1; i<=1;i++) {
            for (int j=-1; j<=1;j++) {
                if (i==0 && j==0){
                    continue;
                }
                int sig_pos_x = origen.getX() + j;
                int sig_pos_y = origen.getY() + i;
                while(sig_pos_y>=0 && sig_pos_x>=0 && sig_pos_y<Ficha.tablero && sig_pos_x<Ficha.tablero){
                    Posicion nueva = new Posicion(sig_pos_x,sig_pos_y);
                    if(aliados.contains(nueva)){
                        break;
                    }
                    if(enemigos.contains(nueva)){
                        movimientos.add(nueva);
                    }
                    sig_pos_y += i;
                    sig_pos_x += j;
                }
            }
        }
        return movimientos;
    }
}
