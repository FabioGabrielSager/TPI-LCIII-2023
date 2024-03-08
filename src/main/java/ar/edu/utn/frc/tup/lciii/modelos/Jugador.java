package ar.edu.utn.frc.tup.lciii.modelos;

import ar.edu.utn.frc.tup.lciii.modelos.Fichas.ColorFicha;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class Jugador {
    private Long id;
    private String nombre;
    private ColorFicha color;

    public Jugador(String nombre, ColorFicha color){
        this.nombre=nombre;
        this.color=color;
    }
}
