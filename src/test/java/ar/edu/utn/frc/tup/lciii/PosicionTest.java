package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.modelos.Fichas.ColorFicha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.FichaFactory;
import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PosicionTest {
    @Test
    public void Test1Peon(){
        Posicion p1 = new Posicion(1L,1,1);
        Posicion p2 = new Posicion(2L,1,1);
        boolean resultadoEsperado = true;
        boolean resultado = p1.equals(p2);

        assertEquals(resultadoEsperado, resultado);
    }
}
