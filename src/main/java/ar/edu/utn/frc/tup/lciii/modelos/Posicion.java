package ar.edu.utn.frc.tup.lciii.modelos;

import ar.edu.utn.frc.tup.lciii.modelos.Fichas.Ficha;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor

public class Posicion {
      private int x;
      private int y;
      private Long id;
    

    public Posicion(int x, int y){
        this.x=x;
        this.y=y;
    }
    public Posicion(Long id, int posicionX, int posicionY){
        this.id=id;
        this.x=posicionX;
        this.y=posicionY;

    }

      @Override
      public boolean equals(Object obj) {
          if(!(obj instanceof Posicion)) {
              return false;
          }
          Posicion posicion = (Posicion)obj;
          return this.x == posicion.x && this.y==posicion.y;
      }

}
