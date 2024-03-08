package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.modelos.Fichas.ColorFicha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.Ficha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.FichaFactory;
import ar.edu.utn.frc.tup.lciii.modelos.Partida;
import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PartidaTest {
    static Partida p;

    @BeforeEach
    public void setUp() {
        p = new Partida("Palo", "Teemo");
    }

    @AfterEach
    public void limpiaPartida() {
        p = null;
    }

    @Tag("PruebasPartida")
    @Test
    public void test1preguntarFicha() {
        boolean resultado = p.preguntarFicha(FichaFactory.crearFicha('C', ColorFicha.BLANCO, 6, 7)
                , new Posicion(5, 5)
                , new ArrayList<>()
                , new ArrayList<>());
        assertEquals(true, resultado);
    }
    @Tag("PruebasPartida")
    @Test
    public void test1Mover() {
        String resultado = "";
        try {
            boolean result = p.moverFicha(FichaFactory.crearFicha('C', ColorFicha.BLANCO, 6, 7)
                    , new Posicion(5, 5));
        }catch (Exception ex){
            resultado = ex.getMessage();
        }
        assertEquals("Error al ingresar la ficha (compruebe el tipo o la posicion de ficha a mover)", resultado);
    }

//    @Tag("PruebasPartida")
//    @Test
//    public void test1Estado() {
//        EstadoPartidaDTO resultado = p.mostrarSituacion();
////        String resultado = p.mostrarFicha(FichaFactory.crearFicha('P',ColorFicha.WHITE,0,7));
//        assertEquals("Estado de Partida: En curso\n" +
//                "Turno actual: Blanco\n" +
//                "Fichas del jugador Palo(Blanco) :\n" +
//                "-En tablero: Ta1 Cb1 Ac1 Dd1 Re1 Af1 Cg1 Th1 Pa2 Pb2 Pc2 Pd2 Pe2 Pf2 Pg2 Ph2 \n" +
//                "-Eliminadas: Ninguna\n" +
//                "Fichas del jugador Teemo(Negro) :\n" +
//                "-En tablero: Cb8 Ta8 Ac8 Dd8 Re8 Af8 Cg8 Th8 Pa7 Pb7 Pc7 Pd7 Pe7 Pf7 Pg7 Ph7 \n" +
//                "-Eliminadas: Ninguna\n", resultado);
//    }

    @Tag("PruebasPartida")
    @Test
    public void test1MostrarFicha() {
        String resultado = p.mostrarFicha(FichaFactory.crearFicha('P',ColorFicha.BLANCO,0,7));
        assertEquals("Pa8", resultado);
    }

    @Tag("PruebasPartida")
    @Test
    public void test1MostrarListaDeFichas() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = Partida.class.getDeclaredMethod("mostrarListaDeFichas",
                ColorFicha.class, boolean.class);
        m.setAccessible(true);

        assertEquals("Ninguna", m.invoke(p,new Object[]{ColorFicha.BLANCO,false}));
    }

    @Tag("PruebasPartida")
    @Test
    public void test1ComprobarEstadoJaque() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList lista = new ArrayList<Ficha>();
        lista.add(FichaFactory.crearFicha('R',ColorFicha.BLANCO,0,0));
        Method m = Partida.class.getDeclaredMethod("comprobarEstadoJaque",
                List.class, List.class, List.class, List.class);
        m.setAccessible(true);

        assertEquals(false, m.invoke(p,new Object[]{lista,new ArrayList<Ficha>(),new ArrayList<Posicion>(),new ArrayList<Posicion>()}));
    }

    @Tag("PruebasPartida")
    @Test
    public void test1ComprobarEstadoJaqueMate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        ArrayList lista = new ArrayList<Ficha>();
        lista.add(FichaFactory.crearFicha('R',ColorFicha.BLANCO,0,0));
        Method m = Partida.class.getDeclaredMethod("comprobarJaqueMate",
                List.class, List.class, List.class, List.class);
        m.setAccessible(true);

        assertEquals(false, m.invoke(p,new Object[]{lista,new ArrayList<Ficha>(),new ArrayList<Posicion>(),new ArrayList<Posicion>()}));
    }

}