package ar.edu.utn.frc.tup.lciii;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.ColorFicha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.Ficha;
import ar.edu.utn.frc.tup.lciii.modelos.Fichas.FichaFactory;
import ar.edu.utn.frc.tup.lciii.modelos.Posicion;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FichasTest {
  Ficha f;

  @AfterEach
  public void limpiaFicha(){
    f = null;
  }

  @Tag("PruebasRey")
  @Test
  public void test1Rey(){
    f = FichaFactory.crearFicha('R',ColorFicha.BLANCO,7,0);
    List<Posicion> movs = f.movimientosPosibles(new ArrayList<Posicion>(),new ArrayList<Posicion>());
    String resultadoEsperado = "(6;0),(6;1),(7;1)";
    StringBuilder resultado = new StringBuilder();

    for (int i = 0; i < movs.size(); i++) {
      Posicion p = movs.get(i);
      resultado.append("(").append(p.getX()).append(";").append(p.getY()).append(")");
      if(i != movs.size()-1)
        resultado.append(",");
    }
    assertEquals(resultadoEsperado, resultado.toString());
  }
  @Tag("PruebasRey")
  @Test
  public void test2Rey(){
    f = FichaFactory.crearFicha('R',ColorFicha.BLANCO,7,0);
    ArrayList enemigos = new ArrayList<Posicion>();
    enemigos.add(new Posicion(6,0));
    List<Posicion> movs = f.ataquesPosibles(new ArrayList<Posicion>(),enemigos);
    String resultadoEsperado = "(6;0)";
    StringBuilder resultado = new StringBuilder();

    for (int i = 0; i < movs.size(); i++) {
      Posicion p = movs.get(i);
      resultado.append("(").append(p.getX()).append(";").append(p.getY()).append(")");
      if(i != movs.size()-1)
        resultado.append(",");
    }
    assertEquals(resultadoEsperado, resultado.toString());
  }


  @Tag("PruebasDama")
  @Test
  public void test1Dama(){
    f = FichaFactory.crearFicha('D',ColorFicha.BLANCO,7,0);
    List<Posicion> movs = f.movimientosPosibles(new ArrayList<Posicion>(),new ArrayList<Posicion>());
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(6,0));

    assertEquals(resultadoEsperado, resultado);
  }
  @Tag("PruebasDama")
  @Test
  public void test2Dama(){
    f = FichaFactory.crearFicha('D',ColorFicha.BLANCO,7,0);
    ArrayList enemigos = new ArrayList<Posicion>();
    enemigos.add(new Posicion(6,0));
    List<Posicion> movs = f.ataquesPosibles(new ArrayList<Posicion>(),enemigos);
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(6,0));


    assertEquals(resultadoEsperado, resultado);
  }
  @Tag("PruebaAlfil")
  @Test
  public void test1Alfil(){
    f = FichaFactory.crearFicha('A',ColorFicha.BLANCO,3,0);
    List<Posicion> movs = f.movimientosPosibles(new ArrayList<Posicion>(),new ArrayList<Posicion>());
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(4,1));

    assertEquals(resultadoEsperado, resultado);
  }
  @Tag("PruebaAlfil")
  @Test
  public void test2Alfil(){
    f = FichaFactory.crearFicha('A',ColorFicha.BLANCO,3,0);
    ArrayList enemigos =new ArrayList<Posicion>();
    enemigos.add(new Posicion(4,1));
    List<Posicion> movs = f.ataquesPosibles(new ArrayList<Posicion>(),enemigos);
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(4,1));

    assertEquals(resultadoEsperado, resultado);
  }
  @Tag("PruebaCaballo")
  @Test
  public void Test1Caballo(){
    f = FichaFactory.crearFicha('C',ColorFicha.BLANCO,1,0);
    List<Posicion> movs = f.movimientosPosibles(new ArrayList<Posicion>(),new ArrayList<Posicion>());
    boolean resultadoEsperado = false;
    boolean resultado = movs.contains(new Posicion(3,2));

    assertEquals(resultadoEsperado, resultado);
  }
  @Tag("PruebaCaballo")
  @Test
  public void test2Caballo(){
    f = FichaFactory.crearFicha('A',ColorFicha.BLANCO,1,0);
    ArrayList enemigos =new ArrayList<Posicion>();
    enemigos.add(new Posicion(3,2));
    List<Posicion> movs = f.ataquesPosibles(new ArrayList<Posicion>(),enemigos);
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(3,2));

    assertEquals(resultadoEsperado, resultado);
  }
  @Tag("PruebaTorre")
  @Test
  public  void test1Torre(){
    f = FichaFactory.crearFicha('T',ColorFicha.BLANCO,0,0);
    List<Posicion> movs = f.movimientosPosibles(new ArrayList<Posicion>(),new ArrayList<Posicion>());
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(0,3));

    assertEquals(resultadoEsperado, resultado);

  }
  @Tag("PruebaTorre")
  @Test
  public  void test2Torre(){
    f = FichaFactory.crearFicha('T',ColorFicha.BLANCO,3,3);
    ArrayList enemigos =new ArrayList<Posicion>();
    enemigos.add(new Posicion(3,2));
    List<Posicion> movs = f.ataquesPosibles(new ArrayList<Posicion>(),enemigos);
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(3,2));

    assertEquals(resultadoEsperado, resultado);
  }
  @Test
  @Tag("PruebaPeon")
  public void Test1Peon(){
    f = FichaFactory.crearFicha('P',ColorFicha.BLANCO,1,4);
    List<Posicion> movs = f.movimientosPosibles(new ArrayList<Posicion>(),new ArrayList<Posicion>());
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(1,5));

    assertEquals(resultadoEsperado, resultado);
  }
  @Test
  @Tag("PruebaPeon")
  public void Test2Peon(){
    f = FichaFactory.crearFicha('P',ColorFicha.BLANCO,1,4);
    ArrayList enemigos =new ArrayList<Posicion>();
    enemigos.add(new Posicion(2,5));
    List<Posicion> movs = f.ataquesPosibles(new ArrayList<Posicion>(),enemigos);
    boolean resultadoEsperado = true;
    boolean resultado = movs.contains(new Posicion(2,5));
  }


}
