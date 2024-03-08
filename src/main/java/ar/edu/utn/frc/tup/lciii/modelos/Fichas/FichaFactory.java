package ar.edu.utn.frc.tup.lciii.modelos.Fichas;

import ar.edu.utn.frc.tup.lciii.modelos.Posicion;

public class FichaFactory {
    public static Ficha crearFicha(String tipoFicha) {
        switch (tipoFicha) {
            case "Rey":
                return new Rey();
            case "Peon":
                return new Peon();
            case "Dama":
                return new Dama();
            case "Caballo":
                return new Caballo();
            case "Torre":
                return new Torre();
            case "Alfil":
                return new Alfil();
            // Agrega más casos para las demás clases derivadas de FichaModel
            default:
                throw new IllegalArgumentException("Tipo de ficha no válido: " + tipoFicha);
        }
    }

    public static Ficha crearFicha(Character tipoNombre, ColorFicha color, int pocisionX, int pocisionY) {
        switch (tipoNombre) {
            case 'R':
                return new Rey(color, new Posicion(pocisionX,pocisionY));
             case 'P':
             return new Peon(color, new Posicion(pocisionX,pocisionY));
            case 'D':
                return new Dama(color, new Posicion(pocisionX, pocisionY));
            case 'C':
                return new Caballo(color, new Posicion(pocisionX,pocisionY));
            case 'T':
                return new Torre(color, new Posicion(pocisionX,pocisionY));
             case 'A':
             return new Alfil(color, new Posicion(pocisionX,pocisionY));
             //Agrega más casos para las demás clases derivadas de FichaModel
            default:
                throw new IllegalArgumentException("Tipo de ficha no válido: " + tipoNombre);
        }
    }
}
